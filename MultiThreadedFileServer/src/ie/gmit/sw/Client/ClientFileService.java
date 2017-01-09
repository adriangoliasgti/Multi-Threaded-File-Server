package ie.gmit.sw.Client;

import java.io.*;
import java.net.*;
import java.util.*;
import ie.gmit.sw.Drivers.*;
import ie.gmit.sw.Client.Configuration.Context;

public class ClientFileService implements DrivableClient{
	// Declaration of private variables
	private Socket soc;
	private String hostAddress;
	private int portNumber;
	private String downloadDirectory;
	private String clientIpAddress;
	private File[] fileList;
	
	public ClientFileService(Context ctx){
		this.hostAddress = ctx.getServerHostAddress();
		this.portNumber = ctx.getServerPortNumber();
		this.downloadDirectory = ctx.getDownloadDirectory();
	}// End of ClientFileService Constructor
	
	@Override
	public void connectToServer(){
		try{
			// Declare & instantiate a new Socket object
			soc = new Socket(hostAddress, portNumber);
			// Acquiring Client IP Address
			clientIpAddress = soc.getLocalAddress().getHostAddress();
			// Serialise/marshal a request to the server
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			// Output of serialised stream
			out.writeObject(new ConnectionDriver(clientIpAddress));
			// Flushing the stream out of memory
			out.flush();
			// Causing thread to pause for a while
			Thread.yield();
			// Server response handling
			ObjectInputStream serverResponse = new ObjectInputStream(soc.getInputStream());
			// Deserialization of object
	        String response = (String) serverResponse.readObject();
			
	        // Informing client of current connection with a detailed message
	        System.out.println(response);
		}// End of try
		
		catch(Exception e){
			e.printStackTrace();
		}// End of catch
	}// End of connectToServer

	@Override
	public void requestListOfFiles(){
		try {
			soc = new Socket(hostAddress, portNumber);
			clientIpAddress = soc.getLocalAddress().getHostAddress();
			
			//Serialise/marshal a request to the server
	        ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
	        // Output of serialised stream
	        out.writeObject(new ListDriver(clientIpAddress));
			// Flushing the stream out of memory
	        out.flush();
	        // Causing thread to pause for a while
	        Thread.yield();
	        
	        // Server response handling
	        ObjectInputStream serverResponse = new ObjectInputStream(soc.getInputStream());
	        // Deserialization of objects
	        fileList = (File[]) serverResponse.readObject();
	        // Loop to print individual objects in the array list
	        for(int i = 0; i < fileList.length; i++){
	        	// Display objects
	        	System.out.println(fileList[i].getName());
	        }// End of for
		}// End of try
		
		catch(Exception e){
			e.printStackTrace();
		}// End of catch
	}// End of requestListOfFiles

	@Override
	public void requestDownload(){
		// Declaration of local variable
		Scanner console = new Scanner(System.in);
		// Displaying user instruction
		System.out.print("\nPlease enter name of the file you wish to download: ");
		// Recording user input
		String fileName = console.nextLine();
		
		// Looping through array list of files
		for(int i = 0; i < fileList.length; i++){
			// Searching for Name match is in the list
			if(fileName.equals(fileList[i].getName())){
				try {
					soc = new Socket(hostAddress, portNumber);
					clientIpAddress = soc.getLocalAddress().getHostAddress();
					
					//Serialise/marshal a request to the server
			        ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			        // Output of serialised stream
			        out.writeObject(new DownloadDriver(clientIpAddress, fileName));
			        // Flushing the stream out of memory
			        out.flush();
			        // Causing thread to pause for a while
			        Thread.yield();
			        
			        // Server response handling
			        ObjectInputStream serverResponse = new ObjectInputStream(soc.getInputStream());
			        // Reading an byteArray object into an array list
			        byte[] byteArray = (byte[]) serverResponse.readObject();
			        
			        FileOutputStream output = new FileOutputStream(downloadDirectory + "/" + fileName);
			        // Output of stream
			        output.write(byteArray);
			        // Closure of stream
			        output.close();
				}// End of try
				
				catch(Exception e){
					e.printStackTrace();
				}// End of catch
			}// End of if
		}// End of for
	}// End of requestDownload

	@Override
	public boolean quit(){
		// Declaration & initialisation of local variables
		boolean confirmation = true;
		String userInput;
		Scanner console = new Scanner(System.in);
		
		do{
			// Displaying warning message for user
			System.out.println("\tAre you sure you wish to terminate...?[y/n]");
			userInput = console.nextLine().toLowerCase();
			
			// Check user input for 'n' value and let user return to menu
			if(userInput.equals("n")){
			}// End of if
			
			// Check user input for 'y' value and terminate the application
			else if(userInput.equals("y")){
				// Display application termination user message
				System.out.println("\tApplication is terminating...");
				// Declaring boolean value to false
				confirmation = false;
			}// End of else if
			
			// Check for invalid user input
			else{
				// Display error message when invalid input used by user
				System.out.println("\n\tUse y/Y to confirm or n/N to decline only!\n\tPlease try again");
			}// End of else
		}while(userInput.equals('y') || userInput.equals('n')); // End of do/while
		
		// Return boolean value
		return confirmation;
	}// End of quit
}// End of FileServer Class
