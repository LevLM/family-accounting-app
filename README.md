# Family Accounting App

The **Family Accounting System** is a simple console-based Java application designed to help families track their incomes and expenses. Users can add transactions, categorize expenses, view reports based on categories or family members, and analyze income vs expenses for a selected period.

## Features

- Add new transactions (income or expense)
- View transactions sorted by date
- Analyze expenses and incomes by category for a selected period
- Analyze expenses and incomes by family members for a selected period
- View total income vs expenses balance
- Calculate the balance of expenses and incomes
- Set budget limits for specific categories
- Receive budget warnings when limits are exceeded
- Back up data

  ## Project Structure

```
FamilyAccountingSystem/
│── src/
│   ├── familyaccounting/
│   │   ├── Main.java                # Entry point of the application
│   │   ├── FamilyAccountingApp.java # Core logic of the accounting system
│   │   ├── Transaction.java         # Class representing a financial transaction
│   │   ├── Category.java            # Class representing a spending/income category
│   │   ├── FamilyMember.java        # Class representing a family member
│── README.md                        # Project documentation
│── pom.xml (if using Maven)         # Maven configuration file
│── build.gradle (if using Gradle)   # Gradle configuration file
```

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Git (optional, for cloning repository)

### Steps to Run

1. Clone the repository (if using Git):
    
    ```
    git clone https://github.com/yourusername/FamilyAccountingSystem.git
    cd FamilyAccountingSystem
    ```
    
2. Compile and run the project:
    
    ```
    javac -d bin src/familyaccounting/*.java
    java -cp bin familyaccounting.Main
    ```
    

## Usage

Upon running the program, the following menu will be displayed:

```
================ Family Accounting System ================
1 - Add a new transaction
2 - View all transactions sorted by date
3 - View expenses/incomes by category for a period
4 - View expenses/incomes by family members for a period
5 - View balance of expenses/incomes for a period
6 - Exit
========================================================
Select an option (1-6):
```

### Example Transaction

```
Enter amount (positive number): 100.50
Enter category of Expense/Income: food
Enter date (DD.MM.YYYY or YYYY-MM-DD): 2025-02-15
Enter family member's name: John
Is this an expense? (yes/no): yes
✅ Transaction successfully added!
```

### Example Report

```
Total Income: 5000.0
Total Expenses: 3500.0
Balance: 1500.0
Balance of incomes and expenses - Positive
```

## Contributing

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Create a Pull Request
