package familyaccounting;
import java.util.*; //will need that for comparator

// Class for managing all transactions
public class FamilyAccountingApp {
 // List of all transactions (income and expenses)
 private List<Transaction> transactions = new ArrayList<>();
 // List of family members
 private List<FamilyMember> familyMembers = new ArrayList<>();

 // Method to add an expense
 public void addExpense(double amount, String category, String memberName) {
     // Get or create a family member -- Maybe rethink this with option  in main
     // Add a new expense to the transaction list
 }

 // Method to add income
 public void addIncome(double amount, String category, String memberName) {
     // Get or create a family member
     // Add a new income to the transaction list
 }

 // Method to view transactions sorted by category (use  transactions.sort(Comparator.comparing( to getCAtegory - getter from the transation)
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
	return null; //TO-DO change this (I put it there for this non boid function to have a return) // probably need to rethink whole thing
     // Find a family member by name or create a new one if not found
	
 }
}
