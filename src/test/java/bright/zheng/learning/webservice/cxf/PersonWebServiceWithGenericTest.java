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
import bright.zheng.learning.webservice.cxf.pojo.Girl;
import bright.zheng.learning.webservice.cxf.pojo.People;
import bright.zheng.learning.webservice.cxf.pojo.Person;
import bright.zheng.learning.webservice.cxf.pojo.Result;

/**
 * Test cases for generic-based web service
 * 
 * @author bright_zheng
 *
 */
public class PersonWebServiceWithGenericTest {

    private PersonServiceWithGeneric<Person, String> personService;
    private PersonServiceWithGeneric<Boy, String> boyService;
    private PersonServiceWithGeneric<? super Person, String> genericService;
    
    private static final String ENDPOINT = "http://localhost:8080/personServiceWithGeneric";
    
    public static final String SYSTEM_NIC = "NIC";
	public static final String SYSTEM_NIC_PW = "ABCDEFG";
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
    	Map<String, Object> props = new HashMap<String, Object>();
    	List<String> list = new ArrayList<String>();
    	list.add(People.class.getName());
    	list.add(Boy.class.getName());
    	list.add(Person.class.getName());
    	props.put("writeXsiType", Boolean.TRUE);
    	props.put("overrideTypesList", list);
    	
    	//BOY Service
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    	
    	factory.getServiceFactory().setProperties(props);
    	factory.setDataBinding(new AegisDatabinding());
    	
    	factory.setServiceClass(PersonServiceWithGeneric.class);
    	factory.setAddress(ENDPOINT);
    	boyService = (PersonServiceWithGeneric<Boy, String>) factory.create();  
    	
    	// bind the outbound interceptor to the client proxy
    	Client proxy1 = ClientProxy.getClient(boyService);
    	proxy1.getOutInterceptors().add(new SystemTokenClientInterceptor(SYSTEM_NIC, SYSTEM_NIC_PW));
    	
    	
    	//PERSON Service
    	JaxWsProxyFactoryBean factory2 = new JaxWsProxyFactoryBean();    	
    	factory2.getServiceFactory().setProperties(props);
    	factory2.getServiceFactory().setDataBinding(new AegisDatabinding());
    	
    	factory2.setAddress(ENDPOINT);
    	personService = (PersonServiceWithGeneric<Person, String>)factory2.create(PersonServiceWithGeneric.class);
    	
    	// bind the outbound interceptor to the client proxy
    	Client proxy2 = ClientProxy.getClient(personService);
    	proxy2.getOutInterceptors().add(new SystemTokenClientInterceptor(SYSTEM_NIC, SYSTEM_NIC_PW));
    	
    	
    	//Generic Service?
    	JaxWsProxyFactoryBean factory3 = new JaxWsProxyFactoryBean();    	
    	factory3.getServiceFactory().setProperties(props);
    	factory3.getServiceFactory().setDataBinding(new AegisDatabinding());
    	
    	factory3.setAddress(ENDPOINT);
    	genericService = (PersonServiceWithGeneric<? super Person, String>)factory3.create(PersonServiceWithGeneric.class);
    	
    	// bind the outbound interceptor to the client proxy
    	Client proxy3 = ClientProxy.getClient(genericService);
    	proxy3.getOutInterceptors().add(new SystemTokenClientInterceptor(SYSTEM_NIC, SYSTEM_NIC_PW));
    }
    
    @Test
    public void testSayHi() {
    	String expected = "Hello Bright!";
    	
        Result<String> actual1 = personService.sayHi("Bright");
        System.out.println(actual1);        
        assertEquals(expected, actual1.toString());
        
        Result<String> actual2 = boyService.sayHi("Bright");
        System.out.println(actual2);        
        assertEquals(expected, actual2.toString());        
    }
    
    @Test
    public void testGreetPerson() {
    	String expected = "Hello Bright(Person)!";
    	
    	Person bright = new Person("Bright(Person)");        
    	Result<String> actual = personService.greetPerson(bright);
        
        System.out.println(actual);        
        assertEquals(expected, actual.toString());
    }
    
    @Test
    public void testGreetBoy() {
    	String expected = "Hello Bright(Boy)!";
    	Boy bright = new Boy("Bright(Boy)", "Game 1");
    	Result<String> actual = boyService.greetPerson(bright);
        
        System.out.println(actual);        
        assertEquals(expected, actual.toString());
    }
    
    @Test
    public void testGreetPeople() {
    	String expected = "Hello Bright(Boy), Brian(Boy), Binh(Boy)!";    	

    	People<Boy> boys = new People<Boy>();
    	
    	Boy bright = new Boy("Bright(Boy)", "Game 1");
    	boys.addPerson(bright);
    	
    	Boy brian = new Boy("Brian(Boy)", "Game 2");
    	boys.addPerson(brian);
    	
    	Boy binh = new Boy("Binh(Boy)", "Game 3");
    	boys.addPerson(binh);
        

    	Result<String> actual = boyService.greetPeople(boys);
        
        System.out.println(actual);
        
        assertEquals(expected, actual.toString());
    }
    
    ///////////////////////////////////////////////////////////////
    
    @Test
    public void testGreetBoyWithGenericWildcardService() {
    	String expected = "Hello Bright(Boy)!";
    	Boy bright = new Boy("Bright(Boy)", "Game 1");
    	Result<String> actual = genericService.greetPerson(bright);
        
        System.out.println(actual);        
        assertEquals(expected, actual.toString());
    }
    
    @Test
    public void testGreetGirlWithGenericWildcardService() {
    	String expected = "Hello Lily(Girl)!";
    	Girl lily = new Girl("Lily(Girl)", "Rose");
    	Result<String> actual = genericService.greetPerson(lily);
        
        System.out.println(actual);        
        assertEquals(expected, actual.toString());
    }
    
    @Test
    public void testGreetPeopleWithGenericWildcardService() {
    	String expected = "Hello Bright(Boy), Brian(Boy), Binh(Boy)!";    	

    	People<Person> boys = new People<Person>();
    	
    	Boy bright = new Boy("Bright(Boy)", "Game 1");
    	boys.addPerson(bright);
    	
    	Boy brian = new Boy("Brian(Boy)", "Game 2");
    	boys.addPerson(brian);
    	
    	Boy binh = new Boy("Binh(Boy)", "Game 3");
    	boys.addPerson(binh);
        

    	Result<String> actual = genericService.greetPeople(boys);
        
        System.out.println(actual);
        
        assertEquals(expected, actual.toString());
    }
    
}
