package ie.gmit.sw.Client.Configuration;

public class Context{
	public static final String CONF_FILE = "Conf.xml";
	private String username;
	private String serverHostAddress;
	private int serverPortNumber;
	private String downloadDirectory;
	
	public Context(){
		super();
	}// End of Context
	
	public String getUsername(){
		return username;
	}// End of getUsername
	
	public void setUsername(String username){
		this.username = username;
	}// End of setUsername
	
	public String getServerHostAddress(){
		return serverHostAddress;
	}// End of getServerHost
	
	public void setServerHostAddress(String serverHost){
		this.serverHostAddress = serverHost;
	}// End of setServerHost
	
	public int getServerPortNumber(){
		return serverPortNumber;
	}// End of getServerPortNumber
	
	public void setServerPortNumber(int serverPort){
		this.serverPortNumber = serverPort;
	}// End of setServerPortNumber
	
	public String getDownloadDirectory(){
		return downloadDirectory;
	}// End of getDownloadDirectory
	
	public void setDownloadDirectory(String downloadDirectory){
		this.downloadDirectory = downloadDirectory;
	}// End of setDownloadDirectory
	
	@Override
	public String toString(){
		return "Context [Username: " + username 
				+ "| Server Host Address: " + serverHostAddress 
				+ "| Server Port Number: " + serverPortNumber
				+ "| Download Directory: " + downloadDirectory + "]";
	}// End of toString
}// End of Context Class