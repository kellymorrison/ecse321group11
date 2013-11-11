public class GameEngine {
	private Player Player1;
	private Player Player2;
	private boolean crashed = false;
	private final int DIMENSION = 600; 
	int[][] map = new int[DIMENSION][DIMENSION];

	public GameEngine(Player u1, Player u2){
		Player1 = u1;
		Player2 = u2;
		Player1.setPositionX(100);
		Player1.setPositionY(200);
		Player1.setPrevPositionX(100);
		Player1.setPrevPositionY(200);
		Player1.setDirection(3);	//right
		Player1.setPrevDirection(3);
		Player2.setPositionX(300);
		Player2.setPositionY(400);
		Player2.setPrevPositionX(300);
		Player2.setPrevPositionY(400);
		Player2.setDirection(1);	//left
		Player2.setPrevDirection(1);
		
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
	
	
	public void updatePosition(Player plr, int dir, int x, int y){
		
		plr.setPrevPositionX(x);
		plr.setPrevPositionY(y);
		
		if (plr.getDirection() == 3){
    		//right
        	if(map[x+1][y] == 1){
        		crashed = true;
        	}else{
        		x++;
        	}
    	}else if (plr.getDirection() == 4){
    		//down
    		if(map[x][y+1] == 1){
    			crashed = true;
    		}else{
    			y++;
    		}
    	}else if (plr.getDirection() == 1){
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
		
		plr.setPositionX(x);
		plr.setPositionY(y);
		
	}
	
	public Boolean getCrashed(){
		return crashed;
	}
	
	public void updateMap(int x, int y){
		map[x][y] = 1;
	}
	
}
