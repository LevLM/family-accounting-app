package familyaccounting;

public class Category {
	private String name;
	private double budget;

	public Category(String name) {
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
		this.budget = budget;
	}

	@Override
	public String toString() {
		return name;
	}
}