import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Bank Teller class to represent a bank teller who is capable of doing the following:
 * 1. Withdraw money
 * 2. Deposit money
 * 3. Add customer names to list of customers they've serviced today
 * 4. Retrieve the amount of money currently in their drawer
 * 5. Retrieve the list of customers they've serviced
 *
 * @author Venzah Hamilton
 * @version 1.0
 */
public class BankTeller {
    /*
    A collection of key value pairs with the key being the customer's name and the value being the transaction they
    performed at the bank
     */
    private Dictionary<String, String> customerServiced = new Hashtable<>();
    private double tellerDrawer = 2500; // The amount of money in the teller's drawer (default is $2500)


    /**
     * Simulates withdrawing money by subtracting a specified amount from tellerDrawer.
     *
     * @param money integer number of the amount of money being withdrawn
     */
    public void withdrawMoney(double money){
        tellerDrawer -= money;
    }

    /**
     * Simulates depositing money by adding a specified amount to tellerDrawer.
     *
     * @param money integer number of the amount of money being deposited
     */
    public void depositMoney(double money){
        tellerDrawer += money;
    }

    /**
     * Adds customer's name and the transaction they performed to a dictionary
     *
     * @param customerName string of the customer's name
     * @param serviceType string of the type of the type of transaction the customer performed
     */
    public void completeTransaction(String customerName, String serviceType){
        customerServiced.put(customerName, serviceType);
    }

    /**
     * Returns the amount of money the teller has in their drawer
     *
     * @return int the amount of money in the teller's drawer
     */
    public double getTellerDrawer(){
        return tellerDrawer;
    }

    /**
     * Returns the full list of customers seen by the teller
     *
     * @return Dictionary<String, String> a collection of key value pairs of the customer and their transaction
     */
    public Dictionary<String, String> getCustomersServiced(){
        return customerServiced;
    }
}
