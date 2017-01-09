package ie.gmit.sw.Drivers;

import java.io.*;

public class ListDriver extends RequestDriver{
	// Declaration of a private static final variables
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	private static final String query = "Listing";
	// Declaration of private variable
	private String filePath;
	
	public ListDriver(String clientIpAddress){
		super(clientIpAddress);
	}// End of ListDriver Constructor

	@Override
	public void run(){
		try {
			// Creation of new file path
			File folder = new File(filePath);
			//Creation of arrayList to take in all files in the directory
			File[] files = folder.listFiles();
		
			ObjectOutputStream output = new ObjectOutputStream(super.getSocketNumber().getOutputStream());
			// Output of stream
			output.writeObject(files);
			// Ensuring all data is sent by flushing buffers
			output.flush();
			// Closure of stream
			output.close();
		}// End of try
		
		catch (IOException e) {
			e.printStackTrace();
		}// End of catch
	}// End of run

	public String getFilePath(){
		return filePath;
	}// End of getFilePath

	public void setFilePath(String filePath){
		this.filePath = filePath;
	}// End of setFilePath
	
	@Override
	public String toString(){
		return "<" + query + "> " + " | Requested by: " + super.getClientIpAddress() + " | At: " + super.getDate().toString() + " |";
	}// End of toString
}// End of RequestDriver Class