package familyaccounting;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.*; //will need that for comparator

import familyaccounting.Transaction;

// Class for managing all transactions
public class FamilyAccountingApp {
	Scanner scanner = new Scanner(System.in); //TO-DO figure out where to create scanner - probably should be in one place, and also close it somewhere
	// List of all transactions (income and expenses)
	private List<Transaction> transactions = new ArrayList<>();
	// List of family members
	private List<FamilyMember> familyMembers = new ArrayList<>();

	private List<String> categoriesExpInc = new ArrayList<>();

	public FamilyAccountingApp() {
		categoriesExpInc.addAll(List.of("food", "rent", "transport", "entertainment", "healthcare", "childcare"));
	}

	public void addTransaction(double amount, String categoryExpInc, LocalDate date, FamilyMember member) {
		transactions.add(new Transaction (amount, categoryExpInc, date, member));
		addCategoryExpInc(categoryExpInc);
	}
	
	// Method to add a new category
	public void addCategoryExpInc(String categoryExpInc) {
		String lowerCategoryExpInc = categoryExpInc.toLowerCase();
		if (!categoriesExpInc.contains(lowerCategoryExpInc)) {
			categoriesExpInc.add(lowerCategoryExpInc);
		}
	}

	public List<String> getCategoriesExpInc() {
		return categoriesExpInc;
	}
	
	
	// Method to view transactions sorted by category (use
	// transactions.sort(Comparator.comparing( to getCAtegory - getter from the
	// transation)
	public void viewTransactionsSortedByCategory() {
        //TO-DO sort by category
	}

	// Method to view transactions sorted by date
	public void viewTransactionsSortedByDate() {
	    transactions.sort(Comparator.comparing(Transaction::getDate));
	    for (Transaction t : transactions) {
	        System.out.println(t);
	    }
	}
	
	// Method to view transactions for a period
	public void viewTransactionsForPeriod() {
	    
	    //asking for the first date of the period //TO-DO change it to picking a month later, or having better way of choosing date then entering it by hand
	    System.out.print("Enter start date (YYYY-MM-DD): ");
	    LocalDate startDate = LocalDate.parse(scanner.nextLine()); //parsing user input into data
	    //asking for the last date of the period //TO-DO same as above
	    System.out.print("Enter end date (YYYY-MM-DD): ");
	    LocalDate endDate = LocalDate.parse(scanner.nextLine());
	    
	    transactions.stream()  // Convert the 'transactions' collection to a stream for further processing.
	    	.filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))  // Filter transactions: keep only those whose date is within the range [startDate, endDate] (inclusive).
	    	.sorted(Comparator.comparing(Transaction::getDate))  // Sort the filtered transactions by date in ascending order.
	    	.forEach(System.out::println);  // For each sorted transaction, print it to the console.
	}
	//TO-DO figure out if we need both viewTransactionsSortedByDate and viewTransactionsForPeriod - probably can keep one or even make on method out of both

	// Method to view transactions sorted by family member
	public void viewTransactionsSortedByMember() {
        transactions.sort(Comparator.comparing(exp -> exp.getMember().getName()));
        transactions.forEach(System.out::println);
	}

	// Method to get or create a family member
	private FamilyMember getOrCreateFamilyMember(String name) {
		return null; // TO-DO change this (I put it there for this non void function to have a
						// return) // probably need to rethink whole thing
		// Find a family member by name or create a new one if not found

	}
}
