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
		app.loadDataFromFile("data.txt");

		// Loop for user interaction
		while (true) {
			// Display menu options
			System.out.println("\n================ Family Accounting System ================\n"
					+ "1 - Add a new transaction\n" + "2 - View all transactions sorted by date\n"
					+ "3 - View expenses/incomes by category for a period\n"
					+ "4 - View expenses/incomes by family members for a period\n"
					+ "5 - View balance of expenses/incomes for a period\n" + "6 - Save data to file\n"
					+ "7 - Load data from file\n" + "8 - Exit\n"
					+ "========================================================\n" + "Select an option (1-8): ");
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
                app.saveDataToFile("data.txt");
                break;
            case 7:
                app.loadDataFromFile("data.txt");
                break;
            case 8:
                System.out.println("Exiting... Thank you for using Family Accounting System!");
                scanner.close();
                return;
            default:
                System.out.println("Invalid option, please try again.");
			}
		}
	}
}