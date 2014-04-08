package bright.zheng.learning.webservice.cxf.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * System Token client-side CXF Interceptor implementation
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
public class SystemTokenClientInterceptor extends AbstractSoapInterceptor {

	private final static String NAMESPACE_URI = "http://bright.zheng.learning.webservice.cxf";

	private static final String ELEMENT_TOKEN = "AuthenticationToken";
	private static final String ELEMENT_SYSTEM_ID = "SYSTEM_ID";
	private static final String ELEMENT_SYSTEM_PW = "SYSTEM_PW";
	
	private String systemId = null; 
    private String systemPw = null; 

	public SystemTokenClientInterceptor(String systemId, String systemPw) {
		super(Phase.WRITE);
		
		this.systemId = systemId;
		this.systemPw = systemPw;
	}

	public void handleMessage(SoapMessage message) throws Fault {
		Document doc = DOMUtils.createDocument();
		
		Element SYSTEM_ID = doc.createElementNS(NAMESPACE_URI, ELEMENT_SYSTEM_ID);
		SYSTEM_ID.setTextContent(systemId);
		
		Element SYSTEM_PW = doc.createElementNS(NAMESPACE_URI, ELEMENT_SYSTEM_PW);
		SYSTEM_PW.setTextContent(systemPw);

		Element token = doc.createElementNS(NAMESPACE_URI, ELEMENT_TOKEN);
		token.appendChild(SYSTEM_ID);
		token.appendChild(SYSTEM_PW);		
		
		SoapHeader header = new SoapHeader(new QName(NAMESPACE_URI), token); 
        List<Header> headers = message.getHeaders();  
        headers.add(header); 
	}

    public void setsystemId(String systemId) { 
        this.systemId = systemId; 
    } 

    public void setsystemPw(String systemPw) { 
        this.systemPw = systemPw; 

    } 
}