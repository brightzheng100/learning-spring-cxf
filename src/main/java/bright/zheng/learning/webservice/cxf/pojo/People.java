package bright.zheng.learning.webservice.cxf.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * People class to illustrate the generic list support
 * 
 * Please note that Apache CXF has issue if we're using T[] instead of List<T>.
 * I have reported this issue to community. 
 * See: https://issues.apache.org/jira/browse/CXF-5509 
 * 
 * @author bright_zheng
 *
 * @param <T>
 */
public class People<T extends Person> {
    
    public People(){}

	//It doesn't work in Apache CXF if using T[]
    //I already reported to Apache CXF, see: https://issues.apache.org/jira/browse/CXF-5509
    /**
     * Nested in org.springframework.beans.factory.BeanCreationException: 
     * ...
     * Error creating bean with name 'personService': Invocation of init method failed; 
     * nested exception is javax.xml.ws.WebServiceException: java.lang.NullPointerException:
     * java.lang.NullPointerException
     * 	at java.lang.reflect.Array.newArray(Native Method)
     * 	at java.lang.reflect.Array.newInstance(Array.java:52)
     * 	at org.apache.cxf.aegis.type.TypeUtil.getTypeRelatedClass(TypeUtil.java:259)
     * ...
     */
    /*private T[] persons;
    
    public T[] getPersons() {
		return persons;
	}

	public void setPersons(T[] persons) {
		this.persons = persons;
	}

	@SuppressWarnings("unchecked")
	public void addPerson(T person) {
		List<T> _persons = new ArrayList<T>();
		
		if(this.persons==null){			
			this.persons = (T[])Array.newInstance(person.getClass(), 1);
		}else{
			for(T _person: this.persons){
				_persons.add(_person);
			}
			this.persons = (T[])Array.newInstance(person.getClass(), this.persons.length + 1);
		}
		_persons.add(person);
		_persons.toArray(this.persons);
	}*/
    
    

	//It works well if using List<T> like below
	private List<T> persons = new ArrayList<T>();

	public List<T> getPersons() {
		return persons;
	}

	public void setPersons(List<T> persons) {
		this.persons = persons;
	}
	
	public void addPerson(T person){
		persons.add(person);
	}
    
}
