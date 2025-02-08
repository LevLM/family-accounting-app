package familyaccounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Abstract class for transactions (expenses and income)
public abstract class Transaction {
	// Fields for storing transaction data
	protected double amount;
	protected String categoryExpInc;
	protected LocalDate date;
	protected FamilyMember member;

	private static List<String> categoriesExpInc = new ArrayList<>();

	// Constructors for transaction
	public Transaction(double amount, String categoryExpInc, LocalDate date, FamilyMember member) {
		this.amount = amount;
		this.date = date;
		this.member = member;
		this.categoryExpInc = categoryExpInc.toLowerCase();
		addCategoryExpInc(this.categoryExpInc);
	}

	static {
		categoriesExpInc.addAll(List.of("food", "rent", "transport", "entertainment", "healthcare"));
	}

	// Method to add a new category
	public static void addCategoryExpInc(String categoryExpInc) {
		String lowerCategoryExpInc = categoryExpInc.toLowerCase();
		if (!categoriesExpInc.contains(lowerCategoryExpInc)) {
			categoriesExpInc.add(lowerCategoryExpInc);
		}
	}

	public static List<String> getCategoriesExpInc() {
		return categoriesExpInc;
	}

	// Getters and setters
	public double getAmount() {
		return amount;
	}

	public String getCategoryExpInc() {
		return categoryExpInc;
	}

	public LocalDate getDate() {
		return date;
	}

	public FamilyMember getMember() {
		return member;
	}

	// Override for toString for data output (about the transaction)
	@Override
	public String toString() {
		return "Category: " + categoryExpInc + ", Amount: " + amount + ", Date: " + date + ", Member: " + member;
	}
}