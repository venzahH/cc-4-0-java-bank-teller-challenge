/**
 * This is the main class to run in order to simulate a day at the bank. It will go through the list of customers that
 * day and both tellers will report who they serviced and how much money is in their drawer at the end.
 *
 * @author Venzah Hamilton
 * @version 1.0
 */

public class myMain {
	public static String[] cust_names = { // String list of the customers being serviced at the bank today
			"Jennifer Figueroa",
			"Heather Mcgee",
			"Amanda Schwartz",
			"Nicole Yoder",
			"Melissa Hoffman",
			"Beatrice Helman",
			"Louis Sanders",
			"Catherine Altman",
			"Ralph Estees",
			"Samantha Augustine",
			"Peter Fredricks",
			"David Alters",
	};

	/**
	 * Prints the list of people the bank teller serviced, the transaction the customer made, and how much money
	 * the teller has at the end of the day
	 *
	 * @param teller teller object holding the customers they serviced and the amount of money they have in their drawer.
	 */
	public static void result(BankTeller teller){
		System.out.println("People serviced: ");
		for(String customer : cust_names){
			if(teller.getCustomersServiced().get(customer) != null){
				System.out.println(customer + " " + teller.getCustomersServiced().get(customer));
			}
		}
		System.out.printf("\nFinal drawer balance: $%.2f\n", teller.getTellerDrawer());
	}

	/**
	 * Every third customer withdraws $250 from the bank teller. The customer's name and their transaction is
	 * recorded in the teller's customer dictionary.
	 *
	 * @param teller teller object hold the customers they serviced and the amount of money they have in their drawer.
	 * @param customerName String object of the customer's name
	 */
	public static void thirdCustomerTransaction(BankTeller teller, String customerName){
		teller.withdrawMoney(250);
		teller.completeTransaction(customerName, "withdrew $250.");
	}

	/**
	 * Every fifth customer deposits $475 to the bank teller. The customer's name and their transaction is recorded
	 * in the teller's customer dictionary.
	 *
	 * @param teller teller object hold the customers they serviced and the amount of money they have in their drawer.
	 * @param customerName String object of the customer's name
	 */
	public static void fifthCustomerTransaction(BankTeller teller, String customerName){
		teller.depositMoney(475);
		teller.completeTransaction(customerName, "deposited $475.");
	}

	/**
	 * All other customers who are not either the third or fifth customer serviced deposits $100 to the bank teller. The
	 * customer's name and their transaction is recorded in the teller's customer dictionary.
	 *
	 * @param teller teller object hold the customers they serviced and the amount of money they have in their drawer.
	 * @param customerName String object of the customer's name
	 */
	public static void otherCustomerTransaction(BankTeller teller, String customerName){
		teller.depositMoney(100);
		teller.completeTransaction(customerName, "deposited $100.");
	}

	/**
	 * In the case that the list of customer's is expanded to 15 and over, since every 3rd customer withdraws $250
	 * and every 5th customer deposits $475, every 15th customer does both. The customer's name and their transaction
	 * is recorded in the teller's customer dictionary.
	 *
	 * @param teller teller object hold the customers they serviced and the amount of money they have in their drawer.
	 * @param customerName String object of the customer's name
	 */
	public static void fifteenthCustomerTransaction(BankTeller teller, String customerName){
		teller.withdrawMoney(250);
		teller.depositMoney(475);
		teller.completeTransaction(customerName, "withdrew $250 and deposited $475.");
	}

	/**
	 * Runs the bqnk simulation using the list of customers and reports each teller's customers and final drawer count
	 *
	 * @param args string list of any arguments passed when running the program
	 */
	public static void main(String[] args) {


		// Initialized 2 bank tellers since there is always 2 tellers on the floor
		BankTeller tellerOne = new BankTeller();
		BankTeller tellerTwo = new BankTeller();

		// Initialized isTellerOne to be true so the first person in line always goes to the first bank teller
		boolean isTellerOne = true;

		for(int customer = 1; customer <= cust_names.length; customer++){ // Loops through all the customers in the list
			System.out.println("Now servicing: " + cust_names[customer - 1]);

			/*
			If the list is ever modified to have 15 or more customers, every 15th customer will deposit $475 and
			withdraw $250.
			 */
			if(customer % 3 == 0 && customer % 5 == 0) {
				if (isTellerOne) {
					fifteenthCustomerTransaction(tellerOne, cust_names[customer - 1]);
				} else {
					fifteenthCustomerTransaction(tellerTwo, cust_names[customer - 1]);
				}
			}
			else if(customer % 3 == 0){ // Every 3rd customer withdraws $250
				if(isTellerOne){
					thirdCustomerTransaction(tellerOne, cust_names[customer - 1]);
				}
				else{
					thirdCustomerTransaction(tellerTwo, cust_names[customer - 1]);
				}
			}
			else if(customer % 5 == 0){ // Every 5th customer deposits $475
				if(isTellerOne){
					fifthCustomerTransaction(tellerOne, cust_names[customer - 1]);
				}
				else{
					fifthCustomerTransaction(tellerTwo, cust_names[customer - 1]);
				}
			}
			else{ // All other people deposit $100
				if(isTellerOne){
					otherCustomerTransaction(tellerOne, cust_names[customer - 1]);
				}
				else{
					otherCustomerTransaction(tellerTwo, cust_names[customer - 1]);
				}
			}
			isTellerOne = !isTellerOne; // Alternates service between teller 1 and teller 2
		}

		// Displays each teller's customers and how much money is in their drawer
		System.out.println("\nAt the end of the day...");
		System.out.println("\n----- Teller 1 -----");
		result(tellerOne);

		System.out.println("\n----- Teller 2 -----");
		result(tellerTwo);
	}
}
