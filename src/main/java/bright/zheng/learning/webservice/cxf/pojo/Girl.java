package bright.zheng.learning.webservice.cxf.pojo;

/**
 * Girl class extends from Person to illustrate the extension support
 * 
 * @author bright_zheng
 *
 */
public class Girl extends Person{
	private String favoriteFlower;
	
	public Girl(){super();}
	
	public Girl(String name){
		super(name);
	}
	
	public Girl(String name, String favoriteFlower){
		super(name);
		this.favoriteFlower = favoriteFlower;
	}

	public String getFavoriteFlower() {
		return favoriteFlower;
	}

	public void setFavoriteFlower(String favoriteFlower) {
		this.favoriteFlower = favoriteFlower;
	}
	
}
