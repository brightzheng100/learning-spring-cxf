package bright.zheng.learning.webservice.cxf;

import javax.jws.WebService;

import bright.zheng.learning.webservice.cxf.pojo.People;
import bright.zheng.learning.webservice.cxf.pojo.Person;
import bright.zheng.learning.webservice.cxf.pojo.Result;

/**
 * Using generic is the most useful way to design the web service
 * where we can expose a series of web services by a simple web service endpoint
 * 
 * Integrated with command design pattern, this design can support new web service release easily
 * by simply adding Action/Handler pairs
 * 
 * This design is to illustrate the generic support in Apache CXF
 * 
 * @author bright_zheng
 *
 * @param <IN>
 * @param <OUT>
 */
@WebService
public interface PersonServiceWithGeneric<IN extends Person, OUT> {
	
	public Result<OUT> sayHi(String name);

    public Result<OUT> greetPerson(IN person);

    public Result<OUT> greetPeople(People<? extends IN> people);

}
