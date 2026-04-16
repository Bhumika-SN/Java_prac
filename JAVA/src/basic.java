import java.util.*;

public class basic {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 1️⃣ Take array size input
        System.out.print("Enter the number of elements: ");
        int n = scan.nextInt();

        // 2️⃣ Declare an array
        int[] numbers = new int[n];

        // 3️⃣ Take input for each element
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scan.nextInt();
        }

        // 4️⃣ Calculate sum and find max
        int sum = 0;
        int max = numbers[0];
        for (int num : numbers) {
            sum += num;
            if (num > max) {
                max = num;
            }
        }

        // 5️⃣ Print results
        System.out.println("\nSum of all numbers = " + sum);
        System.out.println("Largest number = " + max);

        // 6️⃣ Print all elements using for-each loop
        System.out.println("\nYou entered:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }

        scan.close();
    }
}