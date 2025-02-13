package familyaccounting;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.*; //will need that for comparator

import familyaccounting.Transaction;

// Class for managing all transactions
public class FamilyAccountingApp {
	Scanner scanner = new Scanner(System.in); // TO-DO figure out where to create scanner - probably should be in one
												// place, and also close it somewhere
	// List of all transactions (income and expenses)
	private List<Transaction> transactions = new ArrayList<>();
	// List of family members
	private List<FamilyMember> familyMembers = new ArrayList<>();

	private List<Category> categories = new ArrayList<>();

	public FamilyAccountingApp() {
		// Initializing predefined expense categories
		categories.addAll(List.of(new Category("food"), new Category("rent"), new Category("transport"),
				new Category("entertainment"), new Category("healthcare"), new Category("childcare")));
	}

	// Adds a transaction
	public void addTransaction(double amount, String categoryName, LocalDate date, FamilyMember member,
			boolean isIncome) {
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

//	// Method to view transactions sorted by category (use
//	  public void viewTransactionsSortedByCategory() {
//		    transactions.sort(Comparator.comparing(Transaction::getCategoryExpInc));
//		    for (Transaction transaction : transactions) {
//		        System.out.println(transaction);
//		    }
//		}
//	  DO-TO probably won't really need this. And it doesn't work, bro. Like all of this right now. 

	// Here's result of: TO-DO figure out if we need both
	// viewTransactionsSortedByDate and viewTransactionsForPeriod - probably can
	// keep one or even make on method out of both
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
			boolean withinDateRange = (startDate == null || !transaction.getDate().isBefore(startDate))
					&& (endDate == null || !transaction.getDate().isAfter(endDate));
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
		transactions.sort(Comparator.comparing(item -> item.getMember().getName())); // <list that would be
																						// compared>.sort(Comparator.camparing<static
																						// comparison, based on
																						// function>(listItem >
																						// liteItem.getMemembet().getName)
																						// - basically gget member from
																						// the trasnaction, and get the
																						// name of the member. Then SORT
																						// - sorts the list by Memmber
		transactions.forEach(System.out::println); // very short way to write forEach cycle - this way it will just go
													// through each elements.
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

	// Sets a budget for a specific category
	public void setCategoryBudget(String categoryName, double budget) {
		Category category = getOrCreateCategory(categoryName);
		category.setBudget(budget);
	}

	// Checks if a category budget is exceeded for this month
	public void checkBudgetWarning(Category category, double amount) {
		// if budget if no ) we calculate sum for the budget for this month
		if (category.getBudget() > 0) {
			// current date
			LocalDate now = LocalDate.now();

			// checking beginning and the end of this month
			LocalDate startOfMonth = now.withDayOfMonth(1); // First day of the month
			LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth()); // Last day of the month

			// Use calculateCategorySumForPeriod - for this month
			double totalSpentThisMonth = calculateCategorySumForPeriod(category, startOfMonth, endOfMonth);
			// check if limit for this month is exceeded
			if (totalSpentThisMonth + amount > category.getBudget()) {
				System.out.println("Warning: Budget for category " + category.getName() + " is exceeded this month!");
			}
			// TO-DO think if we should warn maybe before before limit exceeded.
		}
	}

	// Calculates total expenses for a category within a given date range - USE THIS
	public double calculateCategorySumForPeriod(Category category, LocalDate startDate, LocalDate endDate) {
		double total = 0.0; // Creating a varible to store total

		// Cycle through each transaction in the transactions list
		for (Transaction transaction : transactions) {
			// Check if the category matches and the transaction is not income
			if (transaction.getCategoryExpInc().equals(category) && !transaction.isIncome()) {

				// Check if the transaction within the specified date range
				boolean isWithinDateRange = (startDate == null || !transaction.getDate().isBefore(startDate))
						&& (endDate == null || !transaction.getDate().isAfter(endDate));

				// If the transaction is within the date range, add its amount to the total
				if (isWithinDateRange) {
					total += transaction.getAmount(); // Add the transaction amount to total
				}
			}
		}

		return total; // Return the total expense amount
	}

	// shows expenses per category for a selected period
	public void viewExpensesByCategoryForPeriod() {
		Scanner scanner = new Scanner(System.in);

		// Asking for the first date of the period or skipping
		System.out.print("Enter start date (YYYY-MM-DD) or press Enter to skip: ");
		String startInput = scanner.nextLine();

		// Asking for the last date of the period or skipping
		System.out.print("Enter end date (YYYY-MM-DD) or press Enter to skip: ");
		String endInput = scanner.nextLine();

		// If the user didn't provide a date, assign null to the respective variable
		LocalDate startDate = startInput.isEmpty() ? null : LocalDate.parse(startInput);
		LocalDate endDate = endInput.isEmpty() ? null : LocalDate.parse(endInput);

		// Creating a list to store pairs of "category - total expenses"
//        List<Object[]> categorySums = new ArrayList<>();

		// Iterating over all categories
//        for (Category category : categories) {
		// Calculating the total expenses for the category within the specified period
		// Adding the "category - sum" pair to the list
		// Sorting the list by the total expenses (from highest to lowest)
		// Outputting the result
		System.out.println("this is viewExpensesByCategoryForPeriod, that doesn't work ATM");
	}
	// TO-DO DOESN'T WORK

	// Calculates the difference between total income and total expenses for a
	// selected period
	public double viewIncomeVsExpensesForPeriod() {
		Scanner scanner = new Scanner(System.in);

		// Asking for the first date of the period or skipping
		System.out.print("Enter start date (YYYY-MM-DD) or press Enter to skip: ");
		String startInput = scanner.nextLine();

		// Asking for the last date of the period or skipping
		System.out.print("Enter end date (YYYY-MM-DD) or press Enter to skip: ");
		String endInput = scanner.nextLine();

		// If the user didn't provide a date, assign null to the respective variable
		LocalDate startDate = startInput.isEmpty() ? null : LocalDate.parse(startInput);
		LocalDate endDate = endInput.isEmpty() ? null : LocalDate.parse(endInput);

		// Variables to store total income and total expenses
		double totalIncome = 0;
		double totalExpenses = 0;

		// Iterate through all transactions
		for (Transaction transaction : transactions) {
			LocalDate transactionDate = transaction.getDate();

			// Check if the transaction is within the selected period
			boolean isWithinPeriod = (startDate == null || !transactionDate.isBefore(startDate))
					&& (endDate == null || !transactionDate.isAfter(endDate));

			if (isWithinPeriod) {
				if (transaction.isIncome()) {
					// Add to total income if it's an income transaction
					totalIncome += transaction.getAmount();
				} else {
					// Add to total expenses if it's an expense transaction
					totalExpenses += transaction.getAmount();
				}
			}
		}

		// Calculate the balance (income - expenses)
		double balance = totalIncome - totalExpenses;

		// Output the total income, total expenses, and balance
		System.out.println("Total Income: " + totalIncome);
		System.out.println("Total Expenses: " + totalExpenses);
		System.out.println("Balance: " + balance);

		// Return the calculated balance
		return balance;
	}
}