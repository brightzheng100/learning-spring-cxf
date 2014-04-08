package bright.zheng.learning.webservice.cxf;

import javax.jws.WebService;

import bright.zheng.learning.webservice.cxf.pojo.Person;

/**
 * Using a base class as the default parameter is a very common way
 * while we're trying to support extension as a simple web service endpoint
 * 
 * This design is to illustrate the class extension support in Apache CXF
 * 
 * @author bright_zheng
 *
 */
@WebService
public interface PersonServiceWithBaseClass {

    public String greetPerson(Person person);

}
