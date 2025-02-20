package familyaccounting;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

public class Main {
	public static void main(String[] args) {
		// create scanner
		Scanner scanner = new Scanner(System.in);
		FamilyAccountingApp app = new FamilyAccountingApp(scanner); // creating object with our logic
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
			System.out.println("\n1 - Enter transaction of expenses or incomes\n"
					+ "2 - All transactions sorted by date\n" + "3 - Expenses/incomes by category for the period\n"
					+ "4 - Expenses/incomes by members for the period\n"
					+ "5 - Balance of Expenses/Incomes for the period\n" + "6 - Exit");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number from 1 to 6.");
				scanner.next();
				continue;
			}
			// Read user choice
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				app.addNewTransaction();
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
				 app.displayIncomeVsExpensesForPeriod();
				break;
			case 6:
				scanner.close();
				return;
			default:
				System.out.println("Invalid option, please try again.");
			}
		}
	}
}