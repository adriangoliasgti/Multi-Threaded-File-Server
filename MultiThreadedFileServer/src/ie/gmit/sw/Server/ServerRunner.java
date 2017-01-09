package ie.gmit.sw.Server;

import java.util.Scanner;

public class ServerRunner{
	public static void main(String[] args){
		// Declaration of variables
		int portNumber;
		String filePath;
		
		
		
		//Try to assign user input to appropriate variables
		try{
			@SuppressWarnings("resource")
			Scanner console = new Scanner(System.in);
			
			System.out.println("\nPlease provide the following:" 
					+ "\nPort Number[Integer value] & File Path[String Value]");
			/**
			 * Strangely giving null pointer exception whenever file
			 */
			//portNumber = console.nextInt();
			//filePath = console.nextLine();
			portNumber = 7777;
			filePath = "./downloads";
			//
			
		}// End of try
		
		catch(Exception e) {
			//Displaying console message providing instructions for user
			System.out.println("\nSorry, system couldn't process your data..."
					+ "\nPlease provide the following:" 
					+ "\nPort Number[Integer value] & File Path[String Value]");
			return;
		}// End of catch
		
		/**
		 * Calling upon a FileServer Class &
		 * feeding it with user input 
		 */
		new FileServer(portNumber, filePath);
	}// End of main
}// End of ServerRunner Class