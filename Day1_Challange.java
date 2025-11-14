package Daycodes;
import java.util.*; 	
interface Bank {
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
}
abstract class Operation implements Bank {
    private String accountHolderName;
    private double accountBalance;

    Operation(String accountHolderName, double accountBalance) {
        this.accountHolderName = accountHolderName;
        this.accountBalance = accountBalance;
    }

    public double getBalance() {
        return accountBalance;
    }

    protected void setBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println(accountHolderName + " deposited " + amount);
        checkBalance();
    }

    public void withdraw(double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println( accountHolderName + " withdrawn " + amount);
        } else {
            System.out.println(accountHolderName + " insufficient balance");
        }
        checkBalance();
    }

    public void checkBalance() {
        System.out.println(accountHolderName + " Current Balance: " + accountBalance);
    }


    abstract void calculateInterest();
}


class SavingsAccount extends Operation {
    private double interestRate = 0.05;

    SavingsAccount(String accountHolderName, double accountBalance) {
        super(accountHolderName, accountBalance);
    }

    void calculateInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
        System.out.println("Interest added: " + interest);
        checkBalance();
    }
}

class CurrentAccount extends Operation {
    CurrentAccount(String accountHolderName, double accountBalance) {
        super(accountHolderName, accountBalance);
    }

    void calculateInterest() {
        System.out.println("No interest for Current Account.");
    }
}

public class Day5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Bank System!");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Choose Account Type (1. Savings / 2. Current): ");
        int type = sc.nextInt();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        Operation account;
        if (type == 1) {
            account = new SavingsAccount(name, balance);
            System.out.println("Savings Account created for " + name);
        } else {
            account = new CurrentAccount(name, balance);
            System.out.println("Current Account created for " + name);
        }

        int choice = 0;
        while (choice != 5) {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Calculate Interest");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

           
            if (choice == 1) {
                System.out.print("Enter amount to deposit: ");
                double dep = sc.nextDouble();
                account.deposit(dep);
            } 
            else if (choice == 2) {
                System.out.print("Enter amount to withdraw: ");
                double wd = sc.nextDouble();
                account.withdraw(wd);
            } 
            else if (choice == 3) {
                account.checkBalance();
            } 
            else if (choice == 4) {
                account.calculateInterest();
            } 
            else if (choice == 5) {
                System.out.println("Thank you, " + name + "! Visit again.");
            } 
            else {
                System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
