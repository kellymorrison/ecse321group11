package gamePlay;

public class GameEngine {
	private User user1;
	private User user2;
	private boolean crashed = false;
	private final int DIMENSION = 600; 
	int[][] map = new int[DIMENSION][DIMENSION];

	public GameEngine(User u1, User u2){
		user1 = u1;
		user2 = u2;
		user1.setPositionX(100);
		user1.setPositionY(200);
		user1.setPrevPositionX(100);
		user1.setPrevPositionY(200);
		user1.setDirection(3);	//right
		user1.setPrevDirection(3);
		user2.setPositionX(300);
		user2.setPositionY(400);
		user2.setPrevPositionX(300);
		user2.setPrevPositionY(400);
		user2.setDirection(1);
		user2.setPrevDirection(1);
		
		//initialize map to empty
        for(int i=0; i < DIMENSION; i++){
        	for(int j=0; j < DIMENSION; j++){
        		map[i][j] = 0;
        	}
        }
        
        //add borders to the map
        for(int row=0; row< DIMENSION; row++){
        	map[1][row] = 1;
        	map[DIMENSION-1][row] = 1;
        	map[row][1] = 1;
        	map[row][DIMENSION-1] = 1;
        }
		
	}
	
	
	public void updatePosition(User usr, int dir, int x, int y){
		
		usr.setPrevPositionX(x);
		usr.setPrevPositionY(y);
		
		if (usr.getDirection() == 3){
    		//right
        	if(map[x+1][y] == 1){
        		crashed = true;
        	}else{
        		x++;
        	}
    	}else if (usr.getDirection() == 4){
    		//down
    		if(map[x][y+1] == 1){
    			crashed = true;
    		}else{
    			y++;
    		}
    	}else if (usr.getDirection() == 1){
    		//left
    		if(map[x-1][y] == 1){
    			crashed = true;
    		}else{
    			x--;
    		}
    	}else{
    		//up
    		if(map[x][y-1] == 1){
    			crashed = true;
    		}else{
    			y--;
    		}
    	}
		
		usr.setPositionX(x);
		usr.setPositionY(y);
		
	}
	
	public Boolean getCrashed(){
		return crashed;
	}
	
	public void updateMap(int x, int y){
		map[x][y] = 1;
	}
	
}
