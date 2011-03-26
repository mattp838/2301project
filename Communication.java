import java.net.*;
import java.awt.event.*;
/**
*	 Communcation pipe between client subsystem and management subsystem 
*	
*	@auther mattp
*	@version 1.0 March 22, 2011
*/
public class Communication implements Runnable
{
	/**
		Default constructor
		Initialize client and server sides
	*/
	public Communication()
	{
	}
	/**
		Run thread 
	*/
	public void run()
	{
	}
	/**
		Do services depend on message received
		@param message the message received from the client side
	*/
	public void doService(String message)
	{
	}		
	/**
		Reservation button listener
	*/	
	class ReservationListener implements ActionListener
	{
		/**
			Perform actions when reservation button is clicked
			@param event an action event
		*/
		public void actionPerformed(ActionEvent event)
		{
		}
	}
	ActionListener reservationListener = new ReservationListener();
	reservationButton.addActionListener(reservationListener);
	/**
		Search button listener
	*/	
	class SearchListener implements ActionListener
	{
		/**
			Perform actions when search button is clicked
			@param event an action event
		*/
		public void actionPerformed(ActionEvent event)
		{
		}
	}
	ActionListener searchListener = new SearchListener();
	searchButton.addActionListener(searchListener);
	/**
		Authentication(login/logout) button listener
	*/		
	class AuthenticationListener implements ActionListener
	{
		/**
			Perform actions when authentication button is clicked
			@param event an action event
		*/
		public void actionPerformed(ActionEvent event)
		{
		}
	}
	ActionListener authenticationListener = new AuthenticationListener();
	authenticationButton.addActionListener(authenticationListener);
}