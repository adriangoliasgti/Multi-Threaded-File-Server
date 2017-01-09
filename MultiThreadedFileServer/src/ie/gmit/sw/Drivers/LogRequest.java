package ie.gmit.sw.Drivers;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class LogRequest implements Runnable{
	// Declaration of private variables
	private BlockingQueue<RequestDriver> queue;
	private FileWriter writer;
	private boolean running = true;
	
	public LogRequest(BlockingQueue<RequestDriver> queue) throws IOException {
		this.queue = queue;
		writer = new FileWriter(new File("log.txt"));
	}// End of LogRequest Constructor
	
	@Override
	public void run(){
		// Loop while running to keep the log of every request
		while(running){
			try{
				RequestDriver request = queue.take();
				System.out.println(request.toString());
				writer.write(request.toString() + "\n");
			}// End of try
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}// End of catch
			
			catch (IOException e) {
				e.printStackTrace();
			}// End of catch
		}// End of while
	}// End of run	
}// End of LogRequest Class