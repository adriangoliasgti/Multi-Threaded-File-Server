package ie.gmit.sw.Server;

import java.io.*;
import java.net.*;
import ie.gmit.sw.Drivers.*;
import java.util.concurrent.*;

public class FileServer{
	// Declaration of static constant
	private static final int SIZE = 6;
	// Declaration of private variables
	private ServerSocket socket;
	private int portNumber;
	private String filePath;
	/**
	 * The boolean value keepRunning is used to control the while loop
	 * in the inner class called Listener. The volatile keyword tells  
	 * the JVM not to cache the value of keepRunning during Optimization,
	 * but to check it's value in memory before using it.
	 */
	private volatile boolean running = true;
	// Initialisation of Blocking queue
	BlockingQueue<RequestDriver> queue = new ArrayBlockingQueue<>(SIZE);
	
	public FileServer(int portNumber, String filePath){
		/**Try the following. If anything goes wrong,
		 * the error will be passed to the catch block
		 */	
		try{
			// Use of acquired user input
			this.portNumber = portNumber;
			this.filePath = filePath;
			
			// Start the server socket listening on port 
			// previously specified by user
			socket = new ServerSocket(portNumber);
			
			// Launch of a server thread
			Thread server = new Thread(new Listener(), "File Server Listener Launching...");
			// Declaration of priority for thread 
			server.setPriority(Thread.MAX_PRIORITY);
			// Hollywood Principle - Don't call us, we'll call you
			server.start();
			
			// Run a new instance of LogRequest as a separate thread,
			// as this is the consumer thread.
			new Thread(new LogRequest(queue), "Log Request").start();
			
			// Output of console message to inform user that server is running
			System.out.println("Server: started successfully & is listening on port:" + portNumber);
		}// End of try
		
		catch(IOException e) {
			System.out.println("Error!: " + e.getMessage());
		}// End of catch
	}// End of FileServer Constructor
	
	/* The inner class Listener is a Runnable, i.e. a job that can be given to a Thread. The job that
	 * the class has been given is to intercept incoming client requests and farm them out to other
	 * threads. Each client request is in the form of a socket and will be handled by a separate new thread.
	 * This Listener class IS-A Runnable
	 */
	private class Listener implements Runnable{
		public void run(){
			// Declaration & Initialisation of variables
			int counter = 0;
			
			//Loop to keep "volatile" running equal to "true".
			while(running){
				try{
					// Trying a blocking method for incoming threads,
					// causing them to stop here and wait for an incoming request
					Socket soc = socket.accept();
					// Entry point for a request...
					// The following process is Deserialization or Unmarshalling
	            	ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
	            	// Deserialize the request into a Request Object
	                RequestDriver request = (RequestDriver)in.readObject();
	                
	                // Setting of file path of the directory with content to download,
	                // if the request is for listing or downloading
	                if(request instanceof ListDriver){
	                	((ListDriver) request).setFilePath(filePath);
	                }// End of if
	                
	                else if (request instanceof DownloadDriver){
	                	((DownloadDriver) request).setFilePath(filePath);
	                }// End of else
	                
	                // Run the request as its own thread
	                request.setSocketNumber(soc);
	                // Declaring number for the thread and launching it
	                new Thread(request, "RequestNo-" + counter).start();
	                
	                // Addition of the request to the queue for logging
					queue.put(request);
					// Augmentation of counter 			
					counter++;
				}// End of try
				
				catch(Exception e){
					// Output of console message to inform user about error
					System.out.println("Error occured while handling incoming request..." + e.getMessage());
				}// End of catch
			}// End of while
		}// End of run
	}// End of inner Listener Class
}// End of FileServer Class