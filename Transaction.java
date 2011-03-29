/*
todos/decisions
- when will transactionID be set? when payment is made or when transaction is completed?
or should it be set in the constructor? what if a transaction is canceled in the middle after setting the transactionID in constructor
- same with transactionDate, when should it be set, after payment or in constructor?
- when should tax amount be calculated? after item is added or after payment is made or whenever getTax() method is called?
- Maybe the methods checkOutBy*() should be in another class and the method setPaymentMethod() should be a method in this class
- printReciept() not done: what should be displayed? This sounds like a method for the main program because most of the info for a reciept can be gotten from methods in the transaction class (getDate(), getTotal(), etc).
- should transactionID be an int?
*/

/*
todo: update class diagram for transaction to version2
- remove printReciept() method
- add precondition to printReciept(), where ever it goes, it can only print if the transaction has been paid

- add methods so a printReciept method can be done in the main program
- add method isPaid() : boolean
- add method getCustomerFirstName()
- add method getCustomerLastName()
- add method getCustomerID()
- add method 

- add method markPaid(PaymentMethod payment)


- maybe add another class PaymentMethod with attributes: ammountInCents and type and methods: int getAmountInCents() and String getType()
- maybe add anouther class CreditCardMachine with methods: public boolean verify(int CCnumber, int amountInCents, int pin)
- maybe add another class DebitMachine in similar fashion as CreditCardMachine
- CrediCardMachine and DebitMachine could be subclass of PaymentMachine class with methods: boolean verify(int CCnumber, int amountInCents, int pin)
*/

/**
	Marks the transaction as finalized/paid.
	@param payment the payment method and ammount.
	<dt><b>Precondition:</b><dd>
	payment.getAmount() >= this.getSubTotal() + this.getTax()
	payment has been verified
	- for cash, the employee should visually inspect it.
	- for credit, the dummy credit card machine class should verify it.
	- for debit, the dummy debit machine class should verify it.
	<dt><b>Postcondition:</b><dd>
	The payment method and ammount is recorded in the transaction/invoice




*/
import java.util.ArrayList;
import java.util.Date;
/**
	A point of sales transaction.
	@see "class diagram from SRS+SDD from teamMitch"
*/

public class Transaction
{
	private int transactionID; // transactionID is set when payment is made and is the next transactionID
	// TODO: or should transactinID be set in constructor? what if a transaction is created and not paid (transaction is canceled)
	private Date transactionDate; // date is set when payment is made
	private ArrayList<TransactionItem> items;
	private String paymentMethod;
	private int subtotalInCents; // this will be modified whenever addTransaction() is called
	private int taxInCents; // this will be set when payment method (checkOut() ) is called
	private boolean paid; // set to true if the transaction is finished and paid for. 
	private CustomerAccount account; // the customer account being worked on 
	
	/**
		Constructs an empty transaction for the given customer.
		@param account the customer account.
		@param transactionID the id number of the transaction (aka invoice number).
		<dt><b>Precondition:</b><dd>
		The customer account exists and is able to rent movies (account.getStatus() == true).
		The transactionID should be a positive number and be the smallest id that does not yet exist in the database.
		<dt><b>Postcondition:</b><dd>
		The transaction/invoice is associated with the customer
	*/
	//status: not done
	public Transaction(CustomerAccount account, int transactionID)
	{
		this.transactionID = transactionID;
		this.account = account;
		paid = false;
		paymentMethod = "";
		items = new ArrayList<TransactionItem>();
	}
	
	



// Note: maybe this method doesnt belong in this class.
//	/**
//	 * Get the next transactinID.
//	 * @return the next transactionID that is not used.
//	// */
//	private long int getNextTransactionID()
//	{
//	    return 100000;
//	}

	/**
		Adds a transaction item to the current transaction.
		@param line the transaction item to add
		<dt><b>Precondition:</b><dd>
		Payment for the transaction has not been made.
	*/
	//status: done
	public void addTransactionItem(TransactionItem item)
	{
		items.add(item);	
	}
	
	/**
		Removes a transaction item from the transaction.
		@param lineNumber the item you want to remove from the transaction.
		@retrun returns true if the transaction item was removed.
	*/
	//status: done
	public boolean removeTransactionItem(int lineNumber)
	{
		return items.remove(lineNumber);
	}
	
	/**
	 * Removes the last transaction item added.
	 * @return returns true if the last transaction was successfuly removed.
	 */
	//status: done, double check this method works with empty items list.
	public boolean removeLastTransactionItem()
	{
		return items.remove(items.size());
	}

	/**
		Gets the date of the transaction. The date of the transaction is set after payment is made and the transaction is finished.
		@return the date the transaction was completed.
	*/
	//status: done
	public Date getDate()
	{
		return transactionDate;
	}
	
	/**
		Gets the total tax ammount for the transaction in cents.
		@return the total tax ammount for the transaction in cents.
	*/
	//status: done
	public int getTax()
	{
		return taxInCents;
	}
	
	/**
		Gets the sub total for the transaction in cents.
		@return the sub total for the transaction in cents.
	*/
	//status: done
	public int getSubTotal()
	{
		return subtotalInCents;
	}
	
	
	/**
		Gets the total for the transaction in cents.
		@return the total for the transaction cents.
	*/
	//status: done
	public int getTotal()
	{
		return subtotalInCents + taxInCents;
	}
	
	/**
		Make a payment for the transaction by cash. After a transaction is paid for, items should not be allowed to be added to the transaction anymore.
		@param ammount the ammount of cash used to pay for the transaction if the payment method is cash.
		<dt><b>Postcondition:</b><dd>
		addTransactionItem() method will not add anymore items
	*/
	public boolean checkoutByCash(int ammount)
	{
	}
	
	/**
		Make a payment for the transaction by credit card.
		@return true if the payment was successful.
		<dt><b>Postcondition:</b><dd>
		addTransactionItem() method will not add anymore items
	*/
	public boolean checkoutByCredit()
	{
	}
	
	/**
		Make a payment for the transaction by debit.
		@param ammount the ammount of cash used to pay for the transaction if the payment method is cash.
		<dt><b>Postcondition:</b><dd>
		addTransactionItem() method will not add anymore items
	*/
	public boolean checkoutByDebit()
	{
	}
	
	/**
		Prints a reciept.
		<dt><b>Precondition:</b><dd>
		The transaction has been paid for.
	*/
	public void printReciept()
	{
	}
	
	/**
		Returns the contents of the reciept as a string.
		<dt><b>Precondition:</b><dd>
		The transaction has been paid for.
	*/
	public String printRecieptString()
	{
	}
	
	/**
		Returns a String object representing this Transaction.
		@return a string representatin of this transaction.
	*/
	public String toString()
	{
		return "not done, fill this in if it is needed for testing";
	}
	
	
	
	
}
