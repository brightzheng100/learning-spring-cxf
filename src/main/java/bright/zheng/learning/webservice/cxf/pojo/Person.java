package bright.zheng.learning.webservice.cxf.pojo;

/**
 * The base class 
 * 
 * @author bright_zheng
 *
 */
public class Person {

    private String name;
    
    public Person(){}
    
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
