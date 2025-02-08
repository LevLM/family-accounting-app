package familyaccounting;
import java.time.LocalDate;

//class for expense
public class Expense extends Transaction {
    public Expense(double amount, String category, LocalDate date, FamilyMember member) {
        super(amount, category, date, member);
    }
}
