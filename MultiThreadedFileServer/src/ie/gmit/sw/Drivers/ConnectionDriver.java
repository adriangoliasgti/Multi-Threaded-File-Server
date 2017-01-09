package ie.gmit.sw.Drivers;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ConnectionDriver extends RequestDriver{
	// Declaration of a private static final variables
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	private static final String query = "Connection";
	
	public ConnectionDriver(String clientIpAddress) {
		super(clientIpAddress);
	}// End of ConnectionDriver Constructor

	@Override
	public void run() {
		try{
			// Declaration of local variables
			String message = "Connection Successful";
			ObjectOutputStream output = new ObjectOutputStream(super.getSocketNumber().getOutputStream());
			// Output of stream
			output.writeObject(message);
			// Ensuring all data is sent by flushing buffers
			output.flush();
			// Closure of stream
			output.close();
		}// End of try
		
		catch(IOException e){
			e.printStackTrace();
		}// End of catch
	}// End of run
	
	@Override
	public String toString(){
		return "<" + query + "> " + " | Requested by: " + super.getClientIpAddress() + " | At: " + super.getDate().toString() + " |";
	}// End of toString
}// End of RequestDriver Class