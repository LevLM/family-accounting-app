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