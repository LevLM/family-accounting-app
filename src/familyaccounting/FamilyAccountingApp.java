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

	public void addTransaction(double amount, String categoryExpInc, LocalDate date, FamilyMember member, boolean isIncome) {
	    // creating family member
	    FamilyMember existingMember = getOrCreateFamilyMember(member.getName());
	    // adding transaction
	    transactions.add(new Transaction(amount, categoryExpInc, date, existingMember, isIncome));
	    // adding cat if doesn not exist
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
    public void viewTransactionsSortedByCategory() {
        transactions.sort(Comparator.comparing(Transaction::getCategoryExpInc));
        transactions.forEach(System.out::println);
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
	public FamilyMember getOrCreateFamilyMember(String name) {
	    for (FamilyMember member : familyMembers) {
	        if (member.getName().equalsIgnoreCase(name)) {
	            return member;
	        }
	    }
	    FamilyMember newMember = new FamilyMember(name);
	    familyMembers.add(newMember);
	    return newMember;
	}
}

