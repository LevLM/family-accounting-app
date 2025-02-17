package familyaccounting;

public class Category {
	private String name;
	private double budget;

	public Category(String name) {
		if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty or null");
        }
		this.name = name;
		this.budget = 0; // budget isn't set
	}

	public String getName() {
		return name;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		if (budget < 0) {
			throw new IllegalArgumentException("Budget cannot be negative");
		}
		this.budget = budget;
	}

	@Override
	public String toString() {
		return name;
	}
}