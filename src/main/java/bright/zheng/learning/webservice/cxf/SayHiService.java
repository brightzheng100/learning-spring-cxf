package bright.zheng.learning.webservice.cxf;

import javax.jws.WebService;

/**
 * The simplest web service to test whether the Apache CXF is working
 * 
 * @author bright_zheng
 *
 */
@WebService
public interface SayHiService {
	
	public String hi(String name);

}
