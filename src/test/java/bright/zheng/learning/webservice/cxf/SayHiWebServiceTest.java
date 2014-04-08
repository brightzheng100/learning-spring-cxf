package bright.zheng.learning.webservice.cxf;

import static org.junit.Assert.assertEquals;

import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for sayHi basic web service
 * 
 * @author bright_zheng
 *
 */
public class SayHiWebServiceTest {

    private SayHiService service;
    private static final String ENDPOINT = "http://localhost:8080/sayHiService";
    
	@Before
    public void setUp() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    	factory.getServiceFactory().setDataBinding(new AegisDatabinding());
    	factory.setAddress(ENDPOINT);
    	service = (SayHiService) factory.create(SayHiService.class);  
    }
    
    @Test
    public void testSayHi() {
    	String expected = "Hello Bright!";
    	
        String actual1 = service.hi("Bright");
        System.out.println(actual1);        
        assertEquals(expected, actual1);     
    }
}
