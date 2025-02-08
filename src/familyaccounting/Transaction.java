package familyaccounting;
import java.time.LocalDate;

//Abstract class for transations (expenses and income)
public abstract class Transaction {
 // Fields for storing transaction data
 protected double amount;
 protected String category;
 protected LocalDate date;
 protected FamilyMember member;

 // Constructors for transation
 public Transaction(double amount, String category, LocalDate date, FamilyMember member) {
     this.amount = amount;
     this.category = category; //TO-DO maybe delete it from here to create different vars for expense and income
     this.date = date;
     this.member = member;
 }

 // Getters and setters
 public double getAmount() { 
     return amount; 
 }
 public String getCategory() { 
     return category; 
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
	return category; //temprorary
     // TO-DO create some way of presenting data for object
 }
}