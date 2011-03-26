/**
*	Penalty calculation class
*	
*	@auther mattp
*	@version 1.0 March 22, 2011
*/
public class Penalty
{
	/**
                Constructor with 2 parameters
		Initialize a penalty record
		@param overdueDates1 the number of days an item is overdue
		@param movie the movie that is overdue
	*/
	public Penalty(int numberOfDays, Movie movie)
	{
		setPenaltyPerDay(movie.getMediaType());
		overdueDates = overdueDates1;
		calculate();
	}
	/**
		Set penalty per day according to media type
		@param mediaType the media type of the item
	*/
	private void setPenaltyPerDay(char mediaType)
	{
		switch(mediaType){
			case 'd': penaltyPerDay = 1; break;
			case 'v': penaltyPerDay = 1;	break;
			case 'b': penaltyPerDay = 1; break;
			default:	penaltyPerDay = 1; break;
		}
	}
	/**
		Get overdue fee
		@return overdueFee the total fee that is overdue
	*/
	public double getOverdueFee()
	{
		return overdueFee;
	}
	/**
		Calculate total overdue fees
	*/
	private void calculate()
	{
		overdueFee = penaltyPerDay * numberOfDays;
	}
	
	private double overdueFee;
	private int numberOfDays;
	private double penaltyPerDay;
}