
public class User {

	private int direction;
	private int prevDirection;
	private int positionX;
	private int positionY;
	private int prevPositionX;
	private int prevPositionY;
	private int roundsWon;
	private String username;
	
	public int getDirection(){
		return direction;
	}
	
	public int getPrevDirection(){
		return prevDirection;
	}
	
	public int getPositionX(){
		return positionX;
	}
	
	public int getPositionY(){
		return positionY;
	}
	
	public int getPrevPositionX(){
		return prevPositionX;
	}
	
	public int getPrevPositionY(){
		return prevPositionY;
	}
	
	public int getRoundsWon(){
		return roundsWon;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setDirection(int dir){
		direction = dir;
	}
	
	public void setPrevDirection(int dir){
		prevDirection = dir;
	}
	
	public void setPositionX(int x){
		positionX = x;
	}
	
	public void setPositionY(int y){
		positionY = y;
	}
	
	public void setPrevPositionX(int x){
		prevPositionX = x;
	}
	
	public void setPrevPositionY(int y){
		prevPositionY = y;
	}
	
	public void setRoundsWon(int rw){
		roundsWon = rw;
	}
	
	public void setUsername(String usr){
		username = usr;
	}
}
