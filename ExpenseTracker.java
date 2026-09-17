import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {

    public static class Expense {
        public int id;
        public String title;
        public String category;
        public double amount;
        public String date;

        public Expense(int id, String title, String category,
                       double amount, String date) {
            this.id = id;
            this.title = title;
            this.category = category;
            this.amount = amount;
            this.date = date;
        }

        public void display() {
            System.out.printf("%-5d %-20s %-15s %-10.2f %-15s%n",
                    id, title, category, amount, date);
        }
    }

    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Expense> expenses = new ArrayList<>();
    public static int nextId = 1;
    public static double monthlyBudget = 0;

    public static void addExpense() {
        System.out.println("\n--- ADD EXPENSE ---");

        System.out.print("Enter expense title: ");
        String title = sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        Expense expense = new Expense(
                nextId,
                title,
                category,
                amount,
                date
        );

        expenses.add(expense);
        nextId++;

        System.out.println("Expense added successfully.");
    }

    public static void viewExpenses() {
        System.out.println("\n--- ALL EXPENSES ---");

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-10s %-15s%n",
                "ID", "Title", "Category", "Amount", "Date");

        System.out.println("----------------------------------------------------------------");

        for (Expense expense : expenses) {
            expense.display();
        }

        System.out.println("----------------------------------------------------------------");
        System.out.printf("Total Expenses: %.2f%n", calculateTotal());
    }

    public static double calculateTotal() {
        double total = 0;

        for (Expense expense : expenses) {
            total = total + expense.amount;
        }

        return total;
    }

    public static void searchByCategory() {
        System.out.print("\nEnter category: ");
        String category = sc.nextLine();

        boolean found = false;

        System.out.println("\n--- SEARCH RESULT ---");

        for (Expense expense : expenses) {

            if (expense.category.equalsIgnoreCase(category)) {

                if (!found) {
                    System.out.printf("%-5s %-20s %-15s %-10s %-15s%n",
                            "ID", "Title", "Category", "Amount", "Date");

                    System.out.println(
                            "----------------------------------------------------------------");
                }

                expense.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expense found in this category.");
        }
    }

    public static void findHighestExpense() {
        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses available.");
            return;
        }

        Expense highest = expenses.get(0);

        for (Expense expense : expenses) {
            if (expense.amount > highest.amount) {
                highest = expense;
            }
        }

        System.out.println("\n--- HIGHEST EXPENSE ---");
        System.out.println("ID       : " + highest.id);
        System.out.println("Title    : " + highest.title);
        System.out.println("Category : " + highest.category);
        System.out.printf("Amount   : %.2f%n", highest.amount);
        System.out.println("Date     : " + highest.date);
    }

    public static void setBudget() {
        System.out.print("\nEnter monthly budget: ");
        monthlyBudget = sc.nextDouble();
        sc.nextLine();

        System.out.println("Monthly budget set successfully.");
    }

    public static void checkBudget() {
        if (monthlyBudget == 0) {
            System.out.println("\nPlease set your monthly budget first.");
            return;
        }

        double total = calculateTotal();
        double remaining = monthlyBudget - total;

        System.out.println("\n--- BUDGET STATUS ---");
        System.out.printf("Monthly Budget : %.2f%n", monthlyBudget);
        System.out.printf("Total Spent    : %.2f%n", total);

        if (remaining > 0) {
            System.out.printf("Remaining      : %.2f%n", remaining);
        } else if (remaining == 0) {
            System.out.println("Budget completely used.");
        } else {
            System.out.printf("Budget Exceeded By: %.2f%n",
                    Math.abs(remaining));
        }
    }

    public static void deleteExpense() {
        System.out.print("\nEnter expense ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean found = false;

        for (int i = 0; i < expenses.size(); i++) {

            if (expenses.get(i).id == id) {
                expenses.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Expense deleted successfully.");
        } else {
            System.out.println("Expense ID not found.");
        }
    }

    public static void showSummary() {
        double total = calculateTotal();

        System.out.println("\n========== EXPENSE SUMMARY ==========");

        System.out.println("Number of Expenses : " + expenses.size());

        System.out.printf("Total Expenses     : %.2f%n", total);

        if (expenses.size() > 0) {
            double average = total / expenses.size();

            System.out.printf("Average Expense    : %.2f%n", average);
        } else {
            System.out.println("Average Expense    : 0.00");
        }

        if (monthlyBudget > 0) {
            System.out.printf("Monthly Budget     : %.2f%n",
                    monthlyBudget);

            System.out.printf("Remaining Budget   : %.2f%n",
                    monthlyBudget - total);
        }

        System.out.println("======================================");
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       SMART EXPENSE TRACKER");
            System.out.println("====================================");

            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Search by Category");
            System.out.println("4. Calculate Total Expenses");
            System.out.println("5. Find Highest Expense");
            System.out.println("6. Set Monthly Budget");
            System.out.println("7. Check Budget Status");
            System.out.println("8. Delete Expense");
            System.out.println("9. Show Expense Summary");
            System.out.println("10. Exit");

            System.out.println("====================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    viewExpenses();
                    break;

                case 3:
                    searchByCategory();
                    break;

                case 4:
                    System.out.printf(
                            "\nTotal Expenses: %.2f%n",
                            calculateTotal()
                    );
                    break;

                case 5:
                    findHighestExpense();
                    break;

                case 6:
                    setBudget();
                    break;

                case 7:
                    checkBudget();
                    break;

                case 8:
                    deleteExpense();
                    break;

                case 9:
                    showSummary();
                    break;

                case 10:
                    System.out.println(
                            "\nThank you for using Smart Expense Tracker."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }

        } while (choice != 10);

        sc.close();
    }
}