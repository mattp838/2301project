/**
*	 Transaction Report class
*	
*	@auther mattp
*	@version 1.0 March 22, 2011
*/
public class TransactionReport implements Report
{
    /**
		Default Constructor 
	*/
    public TransactionReport()
	{
		importTransaction();
	}
    /**
		Import transactions from the database
        */
	private void importTransaction()
	{
	}
	/**
		Create report
	*/
	public void createReport()
	{
	}
	/**
		Print report with a specific format
	*/
	public void printReport()
	{
	}
	
	private Transaction transaction[];
}