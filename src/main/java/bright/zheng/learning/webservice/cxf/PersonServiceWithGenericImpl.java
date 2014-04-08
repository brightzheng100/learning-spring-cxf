package bright.zheng.learning.webservice.cxf;


import javax.jws.WebService;

import bright.zheng.learning.webservice.cxf.pojo.Boy;
import bright.zheng.learning.webservice.cxf.pojo.Girl;
import bright.zheng.learning.webservice.cxf.pojo.People;
import bright.zheng.learning.webservice.cxf.pojo.Person;
import bright.zheng.learning.webservice.cxf.pojo.Result;

/**
 * Implementation of generic web service design 
 * 
 * @author bright_zheng
 *
 * @param <IN>
 */
@WebService(endpointInterface = "bright.zheng.learning.webservice.cxf.PersonServiceWithGeneric")
public class PersonServiceWithGenericImpl<IN extends Person> 
	implements PersonServiceWithGeneric<IN, String> {
	
	public Result<String> sayHi(String name){
		return new Result<String>("Hello " + name + "!");
	}
	
	public Result<String> greetPerson(IN person){
		System.out.println("instanceof Person?:" + (person instanceof Person?"Y":"N"));
		System.out.println("instanceof Boy?:" + (person instanceof Boy?"Y":"N"));
		System.out.println("instanceof Girl?:" + (person instanceof Girl?"Y":"N"));
		return new Result<String>("Hello " + person.getName() + "!");
	}

    public Result<String> greetPeople(People<? extends IN> people) {
    	StringBuffer sb = new StringBuffer(10);
    	int i=0;
    	for(IN person: people.getPersons()){
    		if (i>0) sb.append(", ");
    		sb.append(person.getName());
    		
    		i++;
    	}
    	return new Result<String>("Hello " + sb.toString() + "!");
    }

}
