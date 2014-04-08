package bright.zheng.learning.webservice.cxf.interceptor;

import java.util.List;

import javax.xml.soap.SOAPException;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.EndpointSelectionInterceptor;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * System Token server-side CXF Interceptor implementation
 * for Authentication of a client system access
 * 
 * A customized header would be added in all SOAP requests like below:
 * 
 * <pre>
 * {@code
 * <soap:Envelope 
 * 	xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" 
 * 	xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
 * 	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
 * <soap:Header>
 * 	<AuthenticationToken xmlns="http://bright.zheng.learning.webservice.cxf">
 * 		<SYSTEM_ID xmlns="http://bright.zheng.learning.webservice.cxf">SYSTEM_NIC</SYSTEM_ID>
 * 		<SYSTEM_PW xmlns="http://bright.zheng.learning.webservice.cxf">ABCDEFG</SYSTEM_PW>
 * 	</AuthenticationToken>
 * </soap:Header>
 * <soap:Body>
 * ...
 * }
 * </pre>
 * 
 * @author bright_zheng
 * @since 1 April 2014
 * 
 */
public class SystemTokenServerInterceptor extends AbstractSoapInterceptor {
		
		private static final String ELEMENT_TOKEN = "AuthenticationToken";
		private static final String ELEMENT_SYSTEM_ID = "SYSTEM_ID";
		private static final String ELEMENT_SYSTEM_PW = "SYSTEM_PW";
	  
	    private final static String NAMESPACE_URI = "http://bright.zheng.learning.webservice.cxf";
		 
	    public SystemTokenServerInterceptor() {
	        super(Phase.READ);
	        addAfter(ReadHeadersInterceptor.class.getName());
	        addAfter(EndpointSelectionInterceptor.class.getName());
	    }
	 
	    public void handleMessage(SoapMessage message) throws Fault {
	    	List<Header> headers = message.getHeaders();
	        
	        if(headers==null){
	        	throw new Fault(new SOAPException("Request must include authentication token.")); 
	        }
	        
	        boolean foundToken = false;
	        String systemId = "";
	        String systemPw = "";
	        for (Header header : headers) {
	        	Element element = (Element) header.getObject();
	        	
	        	if(ELEMENT_TOKEN.equalsIgnoreCase(element.getNodeName())
	        			&& NAMESPACE_URI.equalsIgnoreCase(element.getNamespaceURI())){
	        		//found
	        		foundToken = true;
	        		
	        		//SYSTEM_ID
	        		Node systemIdNode = element.getFirstChild();
	        		systemId = systemIdNode.getTextContent();
	        		
	        		//SYSTEM_PW
	        		Node systemPwNode = element.getLastChild();
	        		systemPw = systemPwNode.getTextContent();
	        		
	        		break;
	        	}else{
	        		//continue finding
	        		continue;
	        	}
	        }
	        
	        if(!foundToken){
	        	throw new Fault(new SOAPException("Request must include authentication token: " + ELEMENT_TOKEN)); 
	        }else{
	        	System.out.println("==>Received " + ELEMENT_SYSTEM_ID + "=" + systemId);
	        	System.out.println("==>Received " + ELEMENT_SYSTEM_PW + "=" + systemPw);
	        }
	    }
	}