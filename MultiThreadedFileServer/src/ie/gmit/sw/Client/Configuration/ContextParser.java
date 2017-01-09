package ie.gmit.sw.Client.Configuration;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class ContextParser implements Parser{
	// Declaration of private variables
	private Context ctx;
	
	public ContextParser(Context ctx){
		super();
		this.ctx = ctx;
	}// End of ContextParser
	
	@Override
	public void parse() throws Throwable{
		/* These three lines are part of JAXP (Java API for XML Processing) and are designed to
		 * completely encapsulate how a DOM node tree is manufactured. The concrete classes that
		 * are doing the actual work are part of the Apache Xerces project. JAXP shields us from
		 * having to know and understand the complexity of Xerces through encapsulating the
		 * process.
		 */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(Context.CONF_FILE);
		
		/* The type Document in the line above is an org.w3c.dom.Document. From this
		 * point on in the method, we will only use the DOM "STANDARD" to do the
		 * processing. DOM is language neutral and completely abstract. As a result,
		 * it is completely stable! The DOM standard hasn't changed much since the
		 * year 2000...! Abstractions are highly stable and can be relied upon. 
		 */
		// Get the root of the node tree
		Element root = doc.getDocumentElement();
		// Get the child node of the root
		NodeList children = root.getChildNodes();
		
		// Find the elements...
		// Get "username" from the root Element - <Context-Client>
		ctx.setUsername(root.getAttribute("username"));
		
		// Loop over the child nodes
		for(int i = 0; i < children.getLength(); i++){
			// Get the next child
			Node next = children.item(i);
			// Check if it is an element node
			if (next instanceof Element){
				// Cast the general node to an element node
				Element e = (Element) next;
				
				// Check for element <server-host>
				if (e.getNodeName().equals("server-host")){
					ctx.setServerHostAddress(e.getFirstChild().getNodeValue());
				}// End of if
				
				// Check for element <server-port>
				else if (e.getNodeName().equals("server-port")){
					ctx.setServerPortNumber(Integer.parseInt(e.getFirstChild().getNodeValue()));
				}// End of else if
				
				// Check for element <download-dir>
				else if (e.getNodeName().equals("download-dir")){
					ctx.setDownloadDirectory(e.getFirstChild().getNodeValue());
				}// End of else if
			}// End of if
		}// End of for
	}// End of Parse
	
	public Context getContext() {
		return ctx;
	}// End of getConfiguration

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}// End of setConfiguration
}// End of ContextParser Class