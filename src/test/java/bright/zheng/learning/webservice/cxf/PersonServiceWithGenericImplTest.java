package bright.zheng.learning.webservice.cxf;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bright.zheng.learning.webservice.cxf.pojo.Boy;
import bright.zheng.learning.webservice.cxf.pojo.Girl;
import bright.zheng.learning.webservice.cxf.pojo.People;
import bright.zheng.learning.webservice.cxf.pojo.Person;
import bright.zheng.learning.webservice.cxf.pojo.Result;

/**
 * Test cases for the generic-based service implementation
 * 
 * @author bright_zheng
 *
 */
public class PersonServiceWithGenericImplTest {

    private PersonServiceWithGeneric<Boy, String> boyService = new PersonServiceWithGenericImpl<Boy>();
    private PersonServiceWithGeneric<Person, String> personService = new PersonServiceWithGenericImpl<Person>();
    private PersonServiceWithGeneric<? super Person, String> genericService = new PersonServiceWithGenericImpl<Person>();
    
    @Before
    public void setUp() {
        //
    }
    
    @Test
    public void testGreetPerson() {
    	String expected = "Hello Bright!";
    	
    	Person bright = new Person("Bright");        
        Result<String> actual = personService.greetPerson(bright);
        
        System.out.println(actual);        
        assertEquals(expected, actual.toString());
    }
    
    @Test
    public void testGreetBoy() {
    	String expected = "Hello Bright!";
    	
    	Boy bright = new Boy("Bright", "Game 1");        
    	Result<String> actual = boyService.greetPerson(bright);
        
        System.out.println(actual);        
        assertEquals(expected, actual.toString());
    }
    
    @Test
    public void testGreetBoys() {
    	String expected = "Hello Bright, Brian, Binh!";    	

    	People<Boy> boys = new People<Boy>();
    	
    	Boy bright = new Boy("Bright", "Game 1");
    	boys.addPerson(bright);
    	
    	Boy brian = new Boy("Brian", "Game 2");
    	boys.addPerson(brian);
    	
    	Boy binh = new Boy("Binh", "Game 3");
    	boys.addPerson(binh);
        
    	Result<String> actual = boyService.greetPeople(boys);
        
        System.out.println(actual);
        
        assertEquals(expected, actual.toString());
    }
    
    @Test
    public void testGreetPeopleWithGenericService() {
    	String expected = "Hello Bright, Brian, Binh, Lily!";    	

    	People<Person> people = new People<Person>();
    	
    	Boy bright = new Boy("Bright", "Game 1");
    	people.addPerson(bright);
    	
    	Boy brian = new Boy("Brian", "Game 2");
    	people.addPerson(brian);
    	
    	Boy binh = new Boy("Binh", "Game 3");
    	people.addPerson(binh);
    	
    	Girl lily = new Girl("Lily", "Rose");
    	people.addPerson(lily);
        
    	Result<String> actual = genericService.greetPeople(people);
        
        System.out.println(actual);
        
        assertEquals(expected, actual.toString());
    }
    
}
