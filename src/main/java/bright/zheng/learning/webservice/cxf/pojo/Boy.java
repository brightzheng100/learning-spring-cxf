package bright.zheng.learning.webservice.cxf.pojo;

/**
 * Boy class extends from Person to illustrate the extension support
 * 
 * @author bright_zheng
 *
 */
public class Boy extends Person{
	private String favoriteGame;
	
	public Boy(){super();}
	
	public Boy(String name){
		super(name);
	}
	
	public Boy(String name, String favoriteGame){
		super(name);
		this.favoriteGame = favoriteGame;
	}

	public String getFavoriteGame() {
		return favoriteGame;
	}

	public void setFavoriteGame(String favoriteGame) {
		this.favoriteGame = favoriteGame;
	}
	
}
