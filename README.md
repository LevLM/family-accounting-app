# Untitled

# Family Accounting App

A simple command-line Java application for tracking family expenses and incomes. Users can add transactions, view records by date, category, and family member, and analyze the balance of incomes and expenses.

## Features

- Add transactions (expenses or incomes)
- View all transactions sorted by date
- Analyze expenses and incomes by category for a selected period
- Analyze expenses and incomes by family members for a selected period
- Calculate the balance of expenses and incomes
- Set budgets for categories and receive warnings when exceeded

## Installation

1. Clone the repository:
    
    ```
    git clone https://github.com/your-username/family-accounting.git
    
    ```
    
2. Navigate to the project directory:
    
    ```
    cd family-accounting
    
    ```
    
3. Compile the project:
    
    ```
    javac -d out src/familyaccounting/*.java
    
    ```
    
4. Run the application:
    
    ```
    java -cp out familyaccounting.Main
    
    ```
    

## Usage

Upon running the application, you will be presented with a menu:

```
1 - Enter transaction of expenses or incomes
2 - All transactions sorted by date
3 - Expenses/incomes by category for the period
4 - Expenses/incomes by members for the period
5 - Balance of Expenses/Incomes for the period
6 - Exit

```

Follow the on-screen prompts to interact with the app.

## Example

To add a new transaction:

1. Select option `1`.
2. Enter the transaction amount.
3. Provide the category of the transaction.
4. Enter the transaction date in `YYYY-MM-DD` format.
5. Specify the family member responsible.
6. Indicate whether it is an income (`true/false`).

## Project Structure

```
familyaccounting/
│── Main.java               # Entry point of the application
│── FamilyAccountingApp.java # Core logic for managing transactions
│── Transaction.java         # Abstract class for transactions
│── FamilyMember.java        # Represents a family member
│── Category.java            # Represents a transaction category

```

## To-Do

- Improve transaction filtering and reporting
- Add data persistence (saving transactions to a file or database)
- Implement a graphical user interface

## Contributing

Feel free to submit issues or contribute by creating a pull request.

## License

This project is licensed under the MIT License.
