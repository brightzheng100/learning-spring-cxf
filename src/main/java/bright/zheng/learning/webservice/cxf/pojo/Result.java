package bright.zheng.learning.webservice.cxf.pojo;

/**
 * Result class to illustrate generic support for return 
 * 
 * @author bright_zheng
 *
 * @param <T>
 */
public class Result<T>{
	
	private T result;
	
	public Result(){}
	
	public Result(T result){
		this.result = result;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	@Override
	public String toString(){
		return result.toString();
	}
	
}
