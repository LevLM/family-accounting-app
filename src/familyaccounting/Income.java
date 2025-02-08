package familyaccounting;
import java.time.LocalDate;

//class for income
public class Income extends Transaction {
    public Income(double amount, String category, LocalDate date, FamilyMember member) {
        super(amount, category, date, member);
    }
}

