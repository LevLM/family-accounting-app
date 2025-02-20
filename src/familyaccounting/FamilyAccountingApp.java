package familyaccounting;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*; //will need that for comparator

// Class for managing all transactions
public class FamilyAccountingApp {
	// List of all transactions (income and expenses)
	private List<Transaction> transactions = new ArrayList<>();
	// List of family members
	private List<FamilyMember> familyMembers = new ArrayList<>();
	// List of categories
	private List<Category> categories = new ArrayList<>();
	// Scanner that called in main
	private Scanner scanner;
	
	public FamilyAccountingApp(Scanner scanner) {
		this.scanner = scanner;
		// Initializing predefined expense categories
		categories.addAll(List.of(new Category("food"), new Category("rent"), new Category("transport"),
				new Category("entertainment"), new Category("healthcare"), new Category("childcare")));
	}

// 1. Methods for adding data 

	// Menu that prompts user do add new transaction
	public void addNewTransaction() {
		double amount = -1;
		while (amount <= 0) { 
			System.out.print("Enter amount (positive number): ");
			if (scanner.hasNextDouble()) {
				amount = scanner.nextDouble();
				if (amount <= 0) {
					System.out.println("Amount must be positive");
				}
			} else {
				System.out.println("Invalid input, please enter a valid number");
				scanner.next();
			}
		}
		scanner.nextLine(); // buffer clear

		System.out.print("Enter category of Expense/Income: ");
		String category;
		while ((category = scanner.nextLine().trim()).isEmpty()) {
			System.out.print("Category cannot be empty, enter again: ");
		}

		LocalDate date = null;
		while (date == null) {
			System.out.print("Enter date (YYYY-MM-DD or DD.MM.YYYY): ");
			String dateInput = scanner.nextLine().trim();
			date = parseDate(dateInput, false);
		}

		System.out.print("Enter family member's name: ");
		String memberName;
		while ((memberName = scanner.nextLine().trim()).isEmpty()) {
			System.out.print("Family member's name cannot be empty, enter again: ");
		}
		FamilyMember member = new FamilyMember(memberName);

		Boolean isIncome = null;
		System.out.print("Is this an Income? (yes/no): ");
		while (isIncome == null) {
			String incomeInput = scanner.nextLine().trim().toLowerCase();
			if (incomeInput.equals("yes") || incomeInput.equals("y")) {
				isIncome = true;
			} else if (incomeInput.equals("no") || incomeInput.equals("n")) {
				isIncome = false;
			} else {
				System.out.println("Invalid input. Enter 'yes' or 'no'.");
			}
		}

		// Add new transaction
		addTransaction(amount, category, date, member, isIncome);
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

	// Sets a budget for a specific category
	public void setCategoryBudget(String categoryName, double budget) {
		Category category = getOrCreateCategory(categoryName);
		category.setBudget(budget);
	}

// 2. Methods for getting or creating entities 

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

// 3. Transaction view and analytics

	public void viewTransactionsByDate() {

		LocalDate[] dates = getDatesFromUser(true); // True - as allow skipping data
		LocalDate startDate = dates[0];
		LocalDate endDate = dates[1];

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
		filteredTransactions.sort(Comparator.comparing(Transaction::getDate)); // Sorts the list of filtered
																				// transactions by date.

		// Printing the filtered and sorted transactions

		if (filteredTransactions.isEmpty()) {
			System.out.println("No transactions found for the selected date range");
		} else {
			for (Transaction transaction : filteredTransactions) {
				System.out.println(transaction);
			}
		}

	}

	// Method to view transactions sorted by family member
	public void viewTransactionsSortedByMember() {
		transactions.sort(Comparator.comparing(item -> item.getMember().getName()));// Sorts the list of transactions by
																					// the member's name.
		transactions.forEach(System.out::println); // very short way to write forEach cycle - this way it will just go
													// through each elements.
	}

	// shows expenses per category for a selected period
	public void viewExpensesByCategoryForPeriod() {

		LocalDate[] dates = getDatesFromUser(false); // False - as not allow skipping data
		LocalDate startDate = dates[0];
		LocalDate endDate = dates[1];

		// Store category expenses in a map
		Map<Category, Double> categorySums = new HashMap<>();

		// Calculate total expenses for each category
		for (Category category : categories) {
			double sum = calculateCategorySumForPeriod(category, startDate, endDate);
			categorySums.put(category, sum);
		}

		// Sort categories by total expenses (descending order) -using hashmap instead
		// of object (cause I actually understand what is happening) DELETE THIS
		List<Map.Entry<Category, Double>> sortedList = new ArrayList<>(categorySums.entrySet());
		sortedList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

		// Print the result
		if (sortedList.isEmpty()) {
			System.out.println("No expenses found for the selected period");
		} else {
			System.out.println("Expenses by category for the selected period:");
			for (Map.Entry<Category, Double> entry : sortedList) {
				System.out.println(entry.getKey().getName() + " / " + entry.getValue());
			}
		}
	}

	// Calculates the difference between total income and total expenses for a
	// selected period
	public double viewIncomeVsExpensesForPeriod() {

		LocalDate[] dates = getDatesFromUser(false); // false - as not allow skipping data
		LocalDate startDate = dates[0];
		LocalDate endDate = dates[1];

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
	
	//method to show if balance is positive or not. It's a separate method from viewIncomeVsExpensesForPeriod , since the latter returns the number. 
	public void displayIncomeVsExpensesForPeriod() {
	    double balance = viewIncomeVsExpensesForPeriod();
	    if (balance > 0) {
	        System.out.println("Balance of incomes and expenses - Positive");
	    } else if (balance < 0) {
	        System.out.println("Balance of incomes and expenses - Negative");
	    } else {
	        System.out.println("Balance of incomes and expenses - 0");
	    }
	}

	public void viewExpensesByFamilyMemberForPeriod() {

		LocalDate[] dates = getDatesFromUser(false); // flase - as not allow skipping data
		LocalDate startDate = dates[0];
		LocalDate endDate = dates[1];

		// Create a Map to store the total expenses for each family member
		Map<FamilyMember, Double> memberExpenses = new HashMap<>();

		// Iterate through all transactions and sum up expenses for each family member
		for (Transaction transaction : transactions) {
			// Check if the transaction is an expense and falls within the specified period
			if (!transaction.isIncome() && (startDate == null || !transaction.getDate().isBefore(startDate))
					&& (endDate == null || !transaction.getDate().isAfter(endDate))) {

				// Get the family member from the transaction
				FamilyMember member = transaction.getMember();

				// Add the transaction amount to the total expenses of the family member
				memberExpenses.put(member, memberExpenses.getOrDefault(member, 0.0) + transaction.getAmount());
			}
		}

		// Sort the family members by total expenses (in descending order)
		List<Map.Entry<FamilyMember, Double>> sortedList = new ArrayList<>(memberExpenses.entrySet());
		sortedList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

		// Print the result
		if (sortedList.isEmpty()) {
			System.out.println("No expenses found for the selected period");
		} else {
			System.out.println("Expenses by family member for the selected period:");
			for (Map.Entry<FamilyMember, Double> entry : sortedList) {
				System.out.println(entry.getKey().getName() + " / " + entry.getValue());
			}
		}

	}

// 4. Utility methods for working with dates

	// Method to validate date format
	private LocalDate parseDate(String dateInput, boolean allowSkip) {
		if (allowSkip && dateInput.isEmpty()) {
			return null; // Skip entry if allowed
		}

		// Define to data formats (creating an array that we can use to easliy flip
		// through formats, very easy to add more)
		DateTimeFormatter[] formatters = { DateTimeFormatter.ofPattern("yyyy-MM-dd"), // YYYY-MM-DD
				DateTimeFormatter.ofPattern("dd.MM.yyyy") // DD.MM.YYYY
		};

		// Try parsing the data in each format
		for (DateTimeFormatter formatter : formatters) {
			try {
				return LocalDate.parse(dateInput, formatter);
			} catch (DateTimeParseException e) {
				// Continue
			}
		}

		// Entry is not valid to any format
		System.out.println("Invalid date format. Please use YYYY-MM-DD or DD.MM.YYYY.");
		return null;
	}

	// Method for getting date ranges from Users, or letting them skip data.
	private LocalDate[] getDatesFromUser(boolean allowSkip) {
		LocalDate[] dates = new LocalDate[2];
		boolean isValid = false;

		while (!isValid) {
			// Asking for "from" date. changing text in case we allow skip data entry. Also
			// ternary operator is used for this too look readable.
			System.out.print(
					"Enter start date (YYYY-MM-DD or DD.MM.YYYY)" + (allowSkip ? " or press Enter to skip: " : ": "));
			String startInput = scanner.nextLine();
			dates[0] = parseDate(startInput, allowSkip);

			// Asking for "to" date
			System.out.print(
					"Enter end date (YYYY-MM-DD or DD.MM.YYYY)" + (allowSkip ? " or press Enter to skip: " : ": "));
			String endInput = scanner.nextLine();
			dates[1] = parseDate(endInput, allowSkip);

			// Check if both dates are valid and
			if (dates[0] != null && dates[1] != null && dates[0].isAfter(dates[1])) { // isAfter() is part of LocalDate
																						// class, you can't really use a
																						// comparator here.
				System.out.println("Error: Start date cannot be after end date. Please try again.");
			} else {
				isValid = true; // End cycle if range is valid.
			}
		}

		return dates;
	}

//5. Methods to handle the budget

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

}