package familyaccounting;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Month;

public class Main {
	public static void main(String[] args) {
		// create scanner
        Scanner scanner = new Scanner(System.in);
		FamilyAccountingApp app = new FamilyAccountingApp(); // creating object with our logic
		app.addTransaction(1100.0, "food", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Father"), true);
		app.addTransaction(265.0, "Transport", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Father"), true);
		app.addTransaction(120.0, "food", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Mother"), true);
		app.addTransaction(75.0, "food", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Father"), true);
		app.addTransaction(480.0, "childcare", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Father"), true);

		// Loop for user interaction
		 while (true) {
			// Display menu options
	            System.out.println("1 - Добавить расход, 2 - Добавить доход, 3 - Просмотр (категория), 4 - Выход");
		         // Read user choice
	            int choice = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter family member's name: ");
                    FamilyMember member = new FamilyMember(scanner.nextLine());
                    System.out.print("Is this an income? (true/false): ");
                    boolean isIncome = scanner.nextBoolean();
                    app.addTransaction(amount, category, date, member, isIncome);
                    break;
                case 2:
                    app.viewTransactionsSortedByCategory();
                    break;
                case 3:
                    app.viewTransactionsByDate();
                    break;
                case 4:
                    app.viewTransactionsSortedByMember();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
	        }
			

			



		
	}
}