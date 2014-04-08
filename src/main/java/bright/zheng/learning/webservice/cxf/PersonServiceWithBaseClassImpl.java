package bright.zheng.learning.webservice.cxf;


import javax.jws.WebService;

import bright.zheng.learning.webservice.cxf.pojo.Boy;
import bright.zheng.learning.webservice.cxf.pojo.Girl;
import bright.zheng.learning.webservice.cxf.pojo.Person;

/**
 * The implementation of extension-based web service design 
 * 
 * @author bright_zheng
 *
 */
@WebService(endpointInterface = "bright.zheng.learning.webservice.cxf.PersonServiceWithBaseClass")
public class PersonServiceWithBaseClassImpl implements PersonServiceWithBaseClass {	
	
	public String greetPerson(Person person){
		System.out.println("instanceof Person?:" + (person instanceof Person?"Y":"N"));
		System.out.println("instanceof Boy?:" + (person instanceof Boy?"Y":"N"));
		System.out.println("instanceof Girl?:" + (person instanceof Girl?"Y":"N"));
		return "Hello " + person.getName() + "!";
	}

}
