package ie.gmit.sw.Client;

public class UserInterface{
	// Declaration of variables
	boolean running = true;
	
	public UserInterface(){
	}// End of User Interface Constructor
	
	public boolean isRunning(){
		return running;
	}// End of isRunning
	
	public void userMenu(){
		// Output of user menu in the console
		System.out.println("\nPlease select one of the options [1-4] to continue");
		System.out.println("\t[ 1 ] Connect to Server");
		System.out.println("\t[ 2 ] Print File Listing");
		System.out.println("\t[ 3 ] Download File");
		System.out.println("\t[ 4 ] Quit");
	}// End of userMenu
	
	public void quit(boolean confirmation){
		this.running = confirmation;
	}// End of quit
}// End of UserInterface Class