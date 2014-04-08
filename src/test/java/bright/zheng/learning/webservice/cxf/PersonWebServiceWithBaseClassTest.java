package bright.zheng.learning.webservice.cxf;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import bright.zheng.learning.webservice.cxf.interceptor.SystemTokenClientInterceptor;
import bright.zheng.learning.webservice.cxf.pojo.Boy;
import bright.zheng.learning.webservice.cxf.pojo.People;
import bright.zheng.learning.webservice.cxf.pojo.Person;

/**
 * Test cases for extension-based web service
 * 
 * @author bright_zheng
 *
 */
public class PersonWebServiceWithBaseClassTest {

    private PersonServiceWithBaseClass personService;
    
    private static final String ENDPOINT = "http://localhost:8080/personServiceWithBaseClass";
    
    public static final String SYSTEM_NIC = "NIC";
	public static final String SYSTEM_NIC_PW = "ABCDEFG";
    
	@Before
    public void setUp() {
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    	Map<String, Object> props = new HashMap<String, Object>();
    	
    	List<String> list = new ArrayList<String>();
    	list.add(People.class.getName());
    	list.add(Boy.class.getName());
    	list.add(Person.class.getName());
    	props.put("writeXsiType", Boolean.TRUE);
    	props.put("overrideTypesList", list);
    	
    	factory.getServiceFactory().setProperties(props);
    	factory.setDataBinding(new AegisDatabinding());
    	
    	factory.setAddress(ENDPOINT);
    	personService = (PersonServiceWithBaseClass)factory.create(PersonServiceWithBaseClass.class);
    	
    	// bind the outbound interceptor to the client proxy
    	Client proxy = ClientProxy.getClient(personService);
    	proxy.getOutInterceptors().add(new SystemTokenClientInterceptor(SYSTEM_NIC, SYSTEM_NIC_PW));  
    }
    
    @Test
    public void testGreetPerson() {
    	String expected = "Hello Bright(Person)!";
    	
    	Person bright = new Person("Bright(Person)");        
        String actual = personService.greetPerson(bright);
        
        System.out.println(actual);        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGreetBoy() {
    	String expected = "Hello Bright(Boy)!";
    	
    	Boy bright = new Boy("Bright(Boy)", "Game 1");        
        String actual = personService.greetPerson(bright);
        
        System.out.println(actual);        
        assertEquals(expected, actual);
    }
}
