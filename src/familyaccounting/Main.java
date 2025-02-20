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
		
		// adding sample transactions
		app.loadSampleTransactions();
		
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