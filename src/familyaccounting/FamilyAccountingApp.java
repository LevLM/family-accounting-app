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

    private List<Category> categories = new ArrayList<>();

	public FamilyAccountingApp() {
        // Initializing predefined expense categories
        categories.addAll(List.of(
            new Category("food"),
            new Category("rent"),
            new Category("transport"),
            new Category("entertainment"),
            new Category("healthcare"),
            new Category("childcare")
        ));
	}
	
	// Adds a transaction 
	 public void addTransaction(double amount, String categoryName, LocalDate date, FamilyMember member, boolean isIncome) {
	    // creating category or using existing one
		Category category = getOrCreateCategory(categoryName);
		// creating family member or using existsing one
	    FamilyMember existingMember = getOrCreateFamilyMember(member.getName());
	    // adding transaction
	    transactions.add(new Transaction(amount, category, date, existingMember, isIncome));
	    // adding category if doesn't not exist
	    checkBudgetWarning(category, amount);
	}
	
	// Method to add a new category if it doesn't exist
	 public void addCategoryExpInc(String categoryName) {
		    // Cheking if this category already in the list
		    boolean categoryExists = false;
		    for (Category category : categories) {
		        if (category.getName().equalsIgnoreCase(categoryName)) {
		            categoryExists = true;
		            break; // Stop if match found
		        }
		    }

		    // If no such category, create and add new
		    if (!categoryExists) {
		        categories.add(new Category(categoryName));
		    }
		}

	  public List<Category> getCategories() {
	        return categories;
	    }
	
	
	// Method to view transactions sorted by category (use
	  public void viewTransactionsSortedByCategory() {
		    transactions.sort(Comparator.comparing(Transaction::getCategoryExpInc));
		    for (Transaction transaction : transactions) {
		        System.out.println(transaction);
		    }
		}

	
	//Here's result of: TO-DO figure out if we need both viewTransactionsSortedByDate and viewTransactionsForPeriod - probably can keep one or even make on method out of both
	  public void viewTransactionsByDate() {
		    Scanner scanner = new Scanner(System.in);
		    
		    // Asking for the first date of the period or skipping
		    System.out.print("Enter start date (YYYY-MM-DD) or press Enter to skip: ");
		    String startInput = scanner.nextLine();
		    
		    // Asking for the last date of the period or skipping
		    System.out.print("Enter end date (YYYY-MM-DD) or press Enter to skip: ");
		    String endInput = scanner.nextLine();

		    // Parse dates or keep them null if empty
		    LocalDate startDate = startInput.isEmpty() ? null : LocalDate.parse(startInput);
		    LocalDate endDate = endInput.isEmpty() ? null : LocalDate.parse(endInput);

		    // Filtering and sorting manually
		    List<Transaction> filteredTransactions = new ArrayList<>();
		    for (Transaction transaction : transactions) {
		        boolean withinDateRange = (startDate == null || !transaction.getDate().isBefore(startDate)) &&
		                                  (endDate == null || !transaction.getDate().isAfter(endDate));
		        if (withinDateRange) {
		            filteredTransactions.add(transaction);
		        }
		    }

		    // Sorting the filtered transactions by date
		    filteredTransactions.sort(Comparator.comparing(Transaction::getDate));

		    // Printing the filtered and sorted transactions
		    for (Transaction transaction : filteredTransactions) {
		        System.out.println(transaction);
		    }
		}


	// Method to view transactions sorted by family member
	public void viewTransactionsSortedByMember() {
        transactions.sort(Comparator.comparing(item -> item.getMember().getName())); //<list that would be compared>.sort(Comparator.camparing<static comparison, based on function>(listItem > liteItem.getMemembet().getName) - basically gget member from the trasnaction, and get the name of the member. Then SORT - sorts the list by Memmber
        transactions.forEach(System.out::println); //very short way to write forEach cycle - this way it will just go through each elements. 
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
	
	// Method to get or create a category
	public Category getOrCreateCategory(String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        Category newCategory = new Category(name);
        categories.add(newCategory);
        return newCategory;
    }
}

