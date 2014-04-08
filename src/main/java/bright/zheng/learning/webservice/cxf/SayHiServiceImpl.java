package bright.zheng.learning.webservice.cxf;


import javax.jws.WebService;

/**
 * The implementation of the basic web service
 * 
 * @author bright_zheng
 *
 */
@WebService(endpointInterface = "bright.zheng.learning.webservice.cxf.SayHiService")
public class SayHiServiceImpl implements SayHiService {
	
	public String hi(String name){
		return "Hello " + name + "!";
	}

}
