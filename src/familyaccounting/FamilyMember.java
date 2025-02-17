package familyaccounting;

// Class representing a family member
public class FamilyMember {
	// Name of the family member
	private String name;

	// Constructor to initialize the name
	public FamilyMember(String name) {
		if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Family member name cannot be empty or null");
        }
		this.name = name;
	}

	// Getter for the name
	public String getName() {
		return name;
	}

	// Override the toString method to display family member information
	@Override
	public String toString() {
		return name;
	}
}
