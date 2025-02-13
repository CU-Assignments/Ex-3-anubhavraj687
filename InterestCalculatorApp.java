import java.util.Scanner;

// Base Class
class Account {
    protected double principal;
    protected double rateOfInterest;
    protected int tenure;

    public Account(double principal, double rateOfInterest, int tenure) {
        this.principal = principal;
        this.rateOfInterest = rateOfInterest;
        this.tenure = tenure;
    }

    // Method to calculate interest (to be overridden)
    public double calculateInterest() {
        return 0; // Default implementation
    }
}

// Derived Class for Fixed Deposit
class FixedDeposit extends Account {

    public FixedDeposit(double principal, double rateOfInterest, int tenure) {
        super(principal, rateOfInterest, tenure);
    }

    @Override
    public double calculateInterest() {
        return principal * Math.pow(1 + (rateOfInterest / 100), tenure) - principal;
    }
}

// Derived Class for Recurring Deposit
class RecurringDeposit extends Account {
    private final int monthlyInstallment;

    public RecurringDeposit(int monthlyInstallment, double rateOfInterest, int tenure) {
        super(0, rateOfInterest, tenure);
        this.monthlyInstallment = monthlyInstallment;
    }

    @Override
    public double calculateInterest() {
        double maturityAmount = 0;
        for (int i = 0; i < tenure * 12; i++) {
            maturityAmount += monthlyInstallment * Math.pow(1 + (rateOfInterest / 100 / 12), (tenure * 12 - i));
        }
        return maturityAmount - (monthlyInstallment * tenure * 12);
    }
}

// Main Application Class
public class InterestCalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose Account Type to Calculate Interest:");
        System.out.println("1. Fixed Deposit (FD)");
        System.out.println("2. Recurring Deposit (RD)");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Enter Principal Amount:");
            double principal = scanner.nextDouble();
            System.out.println("Enter Rate of Interest (per annum):");
            double rate = scanner.nextDouble();
            System.out.println("Enter Tenure (in years):");
            int tenure = scanner.nextInt();

            FixedDeposit fd = new FixedDeposit(principal, rate, tenure);
            System.out.println("Interest for Fixed Deposit: " + fd.calculateInterest());

        } else if (choice == 2) {
            System.out.println("Enter Monthly Installment Amount:");
            int installment = scanner.nextInt();
            System.out.println("Enter Rate of Interest (per annum):");
            double rate = scanner.nextDouble();
            System.out.println("Enter Tenure (in years):");
            int tenure = scanner.nextInt();

            RecurringDeposit rd = new RecurringDeposit(installment, rate, tenure);
            System.out.println("Interest for Recurring Deposit: " + rd.calculateInterest());
        } else {
            System.out.println("Invalid choice! Exiting...");
        }
        scanner.close();
    }
}
