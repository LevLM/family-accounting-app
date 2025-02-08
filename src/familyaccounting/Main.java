package familyaccounting;

import java.time.LocalDate;
import java.time.Month;

public class Main {
	public static void main(String[] args) {
		// create scanner
		FamilyAccountingApp app = new FamilyAccountingApp(); // creating object with our logic
		app.addTransaction(1100, "food", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Father"));
		app.addTransaction(265, "Transport", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Father"));
		app.addTransaction(120, "food", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Mother"));
		app.addTransaction(75, "food", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Father"));
		app.addTransaction(480, "childcare", LocalDate.of(2025, Month.APRIL, 11), new FamilyMember("Father"));

		// Loop for user interaction
		while (true) {
			// Display menu options

			// Read user choice

			// Handle different options using switch-case

		}
	}
}