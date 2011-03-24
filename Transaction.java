




/**
	A point of sales transaction.
	@see "class diagram from SRS+SDD from teamMitch"
*/
public class Transaction
{
	private long int transactionID; // transactionID is set when payment is made and is the next transactionID
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
		<dt><b>Precondition:</b><dd>
		The customer account exists and is able to rent movies.
		<dt><b>Postcondition:</b><dd>
		
	*/
	public Transaction(CustomerAccount account)
	{
		//transactionID = getNextTransactionID(
		this.account = account;
		paid = false;
		paymentMethod = "";
		items = new ArrayList<TransactionItem>();
	}
	
	/**
	 * Get the next transactinID.
	 * @return the next transactionID that is not used.
	 */
	private long int getNextTransactionID()
	{
	    return 100000;
	}

	/**
		Adds a transaction line to the current transaction.
		@param line the transaction line to add
		<dt><b>Precondition:</b><dd>
		Payment for the transaction has not been made.
	*/
	//status: done
	public void addTransactionItem(TransactionItem line)
	{
		items.add(line);	
	}
	
	/**
		Removes a transaction item from the transaction.
		@param lineNumber the item you want to remove from the transaction.
		@retrun returns true if the transaction item was removed.
	*/
	public boolean removeTransactionItem(int lineNumber)
	{

	}
	
	/**
	 * Removes the last transaction item added.
	 * @return returns true if the last transaction was successfuly removed.
	 */
	public boolean removeLastTransactionItem()
	{
	}

	/**
		Gets the date of the transaction. The date of the transaction is set after payment is made and the transaction is finished.
		@return the date the transaction was completed.
	*/
	public Date getDate()
	{
	}
	
	/**
		Gets the total tax ammount for the transaction in cents.
		@return the total tax ammount for the transaction in cents.
	*/
	public int getTax()
	{
	}
	
	/**
		Gets the sub total for the transaction in cents.
		@return the sub total for the transaction in cents.
	*/
	public int getSubTotal()
	{
	}
	
	
	/**
		Gets the total for the transaction in cents.
		@return the total for the transaction cents.
	*/
	public int getTotal()
	{
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
	}
	
	
	
	
}
