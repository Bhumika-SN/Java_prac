import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

// Generic Repository
class Repository<T> {
    private List<T> data = new ArrayList<>();

    public void add(T item) {
        data.add(item);
    }

    public List<T> getAll() {
        return data;
    }
}

// Custom Exception
class InvalidDataException extends Exception {
    public InvalidDataException(String msg) {
        super(msg);
    }
}

// Model Class (OOP)
class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) throws InvalidDataException {
        if (marks < 0 || marks > 100) {
            throw new InvalidDataException("Invalid marks!");
        }
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public double getMarks() {
        return marks;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + marks;
    }
}

// Thread Task
class Task implements Runnable {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Running: " + taskName + " by " + Thread.currentThread().getName());
    }
}

public class UltimateJavaPractice {

    public static void main(String[] args) {

        try {
            // Repository + Generics
            Repository<Student> repo = new Repository<>();

            repo.add(new Student(1, "Aman", 85));
            repo.add(new Student(2, "Riya", 92));
            repo.add(new Student(3, "John", 67));

            // Streams + Lambda
            List<Student> topStudents = repo.getAll().stream()
                    .filter(s -> s.getMarks() > 80)
                    .sorted(Comparator.comparing(Student::getMarks).reversed())
                    .collect(Collectors.toList());

            System.out.println("Top Students:");
            topStudents.forEach(System.out::println);

            // Functional Interface
            Function<Student, String> grade = s -> {
                if (s.getMarks() > 90) return "A";
                else if (s.getMarks() > 75) return "B";
                else return "C";
            };

            System.out.println("\nGrades:");
            repo.getAll().forEach(s ->
                    System.out.println(s.getName() + " -> " + grade.apply(s))
            );

            // Multithreading
            ExecutorService executor = Executors.newFixedThreadPool(2);
            executor.submit(new Task("Task1"));
            executor.submit(new Task("Task2"));
            executor.shutdown();

            // File I/O
            File file = new File("students.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Student s : repo.getAll()) {
                    writer.write(s.toString());
                    writer.newLine();
                }
            }

            // Read File
            System.out.println("\nReading from file:");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.lines().forEach(System.out::println);
            }

        } catch (InvalidDataException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
