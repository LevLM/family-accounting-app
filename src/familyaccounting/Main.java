package familyaccounting;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Month;

public class Main {
	public static void main(String[] args) {
		// create scanner
		Scanner scanner = new Scanner(System.in);
		FamilyAccountingApp app = new FamilyAccountingApp(); // creating object with our logic
		app.addTransaction(1100.0, "food", LocalDate.of(2025, Month.APRIL, 01), new FamilyMember("Donald"), false);
		app.addTransaction(265.0, "Transport", LocalDate.of(2025, Month.APRIL, 01), new FamilyMember("Donald"), false);
		app.addTransaction(120.0, "food", LocalDate.of(2025, Month.MAY, 01), new FamilyMember("Miny"), false);
		app.addTransaction(75.0, "food", LocalDate.of(2025, Month.APRIL, 01), new FamilyMember("Donald"), false);
		app.addTransaction(480.0, "childcare", LocalDate.of(2025, Month.MAY, 01), new FamilyMember("Miny"), false);
		app.addTransaction(3500.0, "salary", LocalDate.of(2025, Month.APRIL, 01), new FamilyMember("Miny"), true);
		app.addTransaction(7155.0, "salary", LocalDate.of(2025, Month.APRIL, 01), new FamilyMember("Donald"), true);
		app.addTransaction(3200.0, "salary", LocalDate.of(2025, Month.MAY, 01), new FamilyMember("Miny"), true);
		app.addTransaction(7155.0, "salary", LocalDate.of(2025, Month.MAY, 01), new FamilyMember("Donald"), true);
		app.addTransaction(2700.0, "salary", LocalDate.of(2025, Month.JUNE, 01), new FamilyMember("Miny"), true);
		app.addTransaction(8350.0, "salary", LocalDate.of(2025, Month.JUNE, 01), new FamilyMember("Donald"), true);
		app.addTransaction(170.0, "childcare", LocalDate.of(2025, Month.JUNE, 01), new FamilyMember("Miny"), false);
		app.addTransaction(300.0, "entertainment", LocalDate.of(2025, Month.MAY, 01), new FamilyMember("Miny"), false);
		app.addTransaction(35.0, "entertainment", LocalDate.of(2025, Month.JUNE, 01), new FamilyMember("Mike"), false);
		app.addTransaction(2900.0, "rent", LocalDate.of(2025, Month.APRIL, 01), new FamilyMember("Donald"), false);
		app.addTransaction(2900.0, "rent", LocalDate.of(2025, Month.MAY, 01), new FamilyMember("Donald"), false);
		app.addTransaction(2900.0, "rent", LocalDate.of(2025, Month.JUNE, 01), new FamilyMember("Donald"), false);
		app.addTransaction(20.0, "food", LocalDate.of(2025, Month.APRIL, 01), new FamilyMember("Mike"), false);
		app.addTransaction(130.0, "childcare", LocalDate.of(2025, Month.JUNE, 01), new FamilyMember("Mike"), false);

		// Loop for user interaction
		while (true) {
			// Display menu options
			System.out.println("1 - Enter transaction of expenses or incomes," + " 2 - All transactions sorted by date,"
					+ " 3 - Expenses/incomes by category for the period,"
					+ " 4 - Expenses/incomes by members for the period,"
					+ " 5 - Balanse of Expenses/Incomes for the period," + " 6 - Exit");
			// Read user choice
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter amount: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.print("Enter category of Expense/Income: ");
				String category = scanner.nextLine();
				System.out.print("Enter date (YYYY-MM-DD): ");
				LocalDate date = LocalDate.parse(scanner.nextLine());
				System.out.print("Enter family member's name: ");
				FamilyMember member = new FamilyMember(scanner.nextLine());
				System.out.print("Is this an Income? (true/false): ");
				boolean isIncome = scanner.nextBoolean();
				app.addTransaction(amount, category, date, member, isIncome);
				break;
			case 2:
				app.viewTransactionsByDate();
				break;
			case 3:
				app.viewExpensesByCategoryForPeriod();
				break;
			case 4:
				app.viewExpensesByFamilyMemberForPeriod();
				break;
			case 5:
				double balance = app.viewIncomeVsExpensesForPeriod();
				if (balance > 0) {
					System.out.println("Balanse of incomes and expenses - Positive");
				} else if (balance < 0) {
					System.out.println("Balanse of incomes and expenses - Negative");
				} else {
					System.out.println("Balanse of incomes and expenses - 0");
				}
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}

	}
}