package familyaccounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Abstract class for transactions (expenses and income)
public class Transaction {
	// Fields for storing transaction data
	protected double amount;
	protected Category categoryExpInc;
	protected LocalDate date;
	protected FamilyMember member;
	private final boolean isIncome;

	// Constructors for transaction
	public Transaction(double amount, Category categoryExpInc, LocalDate date, FamilyMember member, boolean isIncome) {
		if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative");
        }
        if (categoryExpInc == null) {
            throw new IllegalArgumentException("Transaction must have a category");
        }
        if (date == null) {
            date = LocalDate.now(); // Default to today if date is null
        }
        if (member == null) {
            throw new IllegalArgumentException("Transaction must be assigned to a family member.");
        }

		this.amount = amount;
		this.categoryExpInc = categoryExpInc;
		this.date = date;
		this.member = member;
		this.isIncome = isIncome;
	}

	public double getAmount() {
		return amount;
	}

	public Category getCategoryExpInc() {
		return categoryExpInc;
	}

	public LocalDate getDate() {
		return date;
	}

	public FamilyMember getMember() {
		return member;
	}

	public boolean isIncome() {
		return isIncome;
	}

	// Override for toString for data output (about the transaction)
	@Override
	public String toString() {
		return date + "  category: " + categoryExpInc + "    member: " + member + "    amount: " + amount;
	}
}