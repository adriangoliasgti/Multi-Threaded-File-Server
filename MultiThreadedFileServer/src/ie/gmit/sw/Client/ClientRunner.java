package ie.gmit.sw.Client;

import java.util.Scanner;
import ie.gmit.sw.Client.Configuration.*;

public class ClientRunner{
	public static void main(String[] args) throws Throwable{
		// Declaration of variables
		Scanner console = new Scanner(System.in);
		int selection;
		
		// XML Configuration file parsing
		Context ctx = new Context();
		ContextParser cp = new ContextParser(ctx);
		cp.parse();
		
		// Declare & instantiate a UserInterface object
		UserInterface UI = new UserInterface();
		// Declare and instantiate a ClienFileService
		ClientFileService clientService = new ClientFileService(ctx);
		
		// Loop to keep application running until user decides to quit
		while(UI.isRunning()){
			// Display user menu
			UI.userMenu();
			// Record user input
			selection = console.nextInt();
			
			// Cause an corresponding action based on user input
			switch(selection){
				case 1:
					// Run connectToServer() method of ClientFileService instance
					clientService.connectToServer();
					// Break out of switch
					break;
				case 2:
					// Run requestListOfFiles() method of ClientFileService instance
					clientService.requestListOfFiles();
					// Break out of switch
					break;
				case 3:
					// Run requestDownload() method of ClientFileService instance
					clientService.requestDownload();
					// Break out of switch
					break;
				case 4:
					// Run quit() method of UserInterface instance with parameter from ClientFileService instance
					UI.quit(clientService.quit());
					// Break out of switch
					break;
				default:
					System.out.println("\n\tThe selected option does not exist!\n\tPlease try again");
					break;
			}// End of switch
		}// End of while
		// Console termination
		console.close();
	}// End of main
}// End of ClientRunner Class
