package ie.gmit.sw.Drivers;

import java.net.*;
import java.util.Date;
import java.io.Serializable;

public abstract class RequestDriver implements Serializable, Runnable{
	// Declaration of a private static variable
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	// Declaration of private variables
	private Date date;
	private Socket socketNumber;
	private String username;
	private String query;
	private String clientIpAddress;
	private String hostAddress;
	private int portNumber;
	
	public RequestDriver(String clientIpAddress){
		date = new Date();
		this.clientIpAddress = clientIpAddress;
	}// End of RequestDriver Constructor
	
	public Date getDate(){
		return date;
	}// End of getDate
	
	public void setDate(Date date){
		this.date = date;
	}// End of setDate
	
	public Socket getSocketNumber(){
		return socketNumber;
	}// End of getSocketNumber
	
	public void setSocketNumber(Socket socketNumber){
		this.socketNumber = socketNumber;
	}// End of setSocketNumber
	
	public String getUsername(){
		return username;
	}// End of getUsername
	
	public void setUsername(String username){
		this.username = username;
	}// End of setUsername
	
	public String getQuery(){
		return query;
	}// End of getQuery
	
	public void setQuery(String query){
		this.query = query;
	}// End of setQuery
	
	public String getClientIpAddress(){
		return clientIpAddress;
	}// End of getClientIpAddress
	
	public void setClientIpAddress(String clientIpAddress){
		this.clientIpAddress = clientIpAddress;
	}// End of setClientIpAddress
	
	public String getHostAddress(){
		return hostAddress;
	}// End of getHostAddress
	
	public void setHostAddress(String hostAddress){
		this.hostAddress = hostAddress;
	}// End of setHostAddress
	
	public int getPortNumber(){
		return portNumber;
	}// End of getPortNumber
	
	public void setPortNumber(int portNumber){
		this.portNumber = portNumber;
	}// End of setPortNumber
}// End of Abstract Request Class