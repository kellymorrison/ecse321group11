public class Player {
    
    private User user;
    
    public Player(User user) {
            this.user = user;
    }
    
    private int direction;
    private int prevDirection;
    private int positionX;
    private int positionY;
    private int prevPositionX;
    private int prevPositionY;
    private Boolean crashed = false;
    
    public Boolean hasCrashed(){
    	return crashed;
    }
    
    public void isCrashing(){
    	crashed = true;
    }
    
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
    
}
