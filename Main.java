import java.util.*;
import java.io.*;

public class Main {

    static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        loadExpenses(expenses);

        while (true) {
            System.out.println("\n==== Expense Tracker ====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter expense name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter category (Food/Travel/etc): ");
                    String category = scanner.nextLine();

                    Expense e = new Expense(name, amount, category);
                    expenses.add(e);
                    saveAllExpenses(expenses);

                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                    } else {
                        double total = 0;

                        System.out.println("\n--- Your Expenses ---");

                        for (int i = 0; i < expenses.size(); i++) {
                            Expense exp = expenses.get(i);
                            System.out.println(
                                (i + 1) + ". " +
                                exp.name + " | ₹" + exp.amount +
                                " | " + exp.category
                            );
                            total += exp.amount;
                        }

                        System.out.println("----------------------");
                        System.out.println("Total Spending: ₹" + total);
                    }
                    break;

                case 3:
                    if (expenses.isEmpty()) {
                        System.out.println("Nothing to delete.");
                        break;
                    }

                    System.out.print("Enter expense number to delete: ");
                    int index = scanner.nextInt();

                    if (index > 0 && index <= expenses.size()) {
                        expenses.remove(index - 1);
                        saveAllExpenses(expenses);
                        System.out.println("Expense deleted!");
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Save ALL expenses (overwrite file)
    public static void saveAllExpenses(ArrayList<Expense> expenses) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);

            for (Expense e : expenses) {
                fw.write(e.name + "," + e.amount + "," + e.category + "\n");
            }

            fw.close();

        } catch (IOException ex) {
            System.out.println("Error saving file.");
        }
    }

    // Load expenses
    public static void loadExpenses(ArrayList<Expense> expenses) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                String name = parts[0];
                double amount = Double.parseDouble(parts[1]);
                String category = parts[2];

                expenses.add(new Expense(name, amount, category));
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
}