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
	
	//Here's result of: TO-DO figure out if we need both viewTransactionsSortedByDate and viewTransactionsForPeriod - probably can keep one or even make on method out of both
	public void viewTransactionsByDate() { 
	    Scanner scanner = new Scanner(System.in);
	    
	    // asking for the first date of the period or skip //TO-DO change it to picking a month later, or having better way of choosing date then entering it by hand
	    System.out.print("Enter start date (YYYY-MM-DD) or press Enter to skip: ");
	    String startInput = scanner.nextLine();
	    
	    // asking for the last date of the period or to skip //TO-DO same as above
	    System.out.print("Enter end date (YYYY-MM-DD) or press Enter to skip: ");
	    String endInput = scanner.nextLine();

	    // Is user etnered the data - parse it to LocalDate, else keep it null
	    LocalDate startDate = startInput.isEmpty() ? null : LocalDate.parse(startInput);
	    LocalDate endDate = endInput.isEmpty() ? null : LocalDate.parse(endInput);

	    // Filter transactions is startDate or endDate is null, filter ignores those
	    transactions.stream()
	        .filter(t -> (startDate == null || !t.getDate().isBefore(startDate)) &&  
	                     (endDate == null || !t.getDate().isAfter(endDate)))
	        // sort by date
	        .sorted(Comparator.comparing(Transaction::getDate))
	        // shot transactions
	        .forEach(System.out::println);
	    
	    //TO-DO maybe return the result rather than print it (but this method already prints stuff
	}

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
