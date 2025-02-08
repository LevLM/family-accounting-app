package familyaccounting;

import java.time.LocalDate;
import java.util.*; //will need that for comparator

import familyaccounting.Transaction;

// Class for managing all transactions
public class FamilyAccountingApp {
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
		// Sort the transaction list by category and display them
	}

	// Method to view transactions sorted by date
	public void viewTransactionsSortedByDate() {
		// Sort the transaction list by date and display them
	}

	// Method to view transactions sorted by family member
	public void viewTransactionsSortedByMember() {
		// Sort the transaction list by family member's name and display them
	}

	// Method to get or create a family member
	private FamilyMember getOrCreateFamilyMember(String name) {
		return null; // TO-DO change this (I put it there for this non boid function to have a
						// return) // probably need to rethink whole thing
		// Find a family member by name or create a new one if not found

	}
}
