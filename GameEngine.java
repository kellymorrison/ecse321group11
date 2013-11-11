public class GameEngine {
	private Player player1;
	private Player player2;

	private final int DIMENSION = 200; 
	private final int speed = 1;		//frame rate is too slow, but increasing increases speed too much
										//speed at 2 skips because
	private final int strokeSize;
	int[][] map = new int[DIMENSION][DIMENSION];

	public GameEngine(Player p1, Player p2, int stroke){
		player1 = p1;
		player2 = p2;
		
		player1.setPositionX(DIMENSION/4);
		player1.setPositionY(DIMENSION/4);
		player1.setPrevPositionX(DIMENSION/4);
		player1.setPrevPositionY(DIMENSION/4);
		player1.setDirection(3);	//right
		player1.setPrevDirection(3);
		
		player2.setPositionX((DIMENSION*3)/4);
		player2.setPositionY((DIMENSION*3)/4);
		player2.setPrevPositionX((DIMENSION*3)/4);
		player2.setPrevPositionY((DIMENSION*3)/4);
		player2.setDirection(1);	//left
		player2.setPrevDirection(1);
		
		strokeSize = stroke;
		
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
	
	
	public void updatePosition(Player plr){
		int x = plr.getPositionX();
		int y = plr.getPositionY();
		
		plr.setPrevPositionX(x);
		plr.setPrevPositionY(y);
		
		if (plr.getDirection() == 3){
    		//right
        	if((map[x+1][y] == 1) || (map[x+1][y-1] == 1) || (map[x+1][y+1] == 1)){
        		//location or surrounding locations contains a wall
        		if (plr.getPrevDirection() == 1){
        			//press right left key while traveling right negates the turn
        			plr.setDirection(1);		//continue in this direction
    				plr.setPrevDirection(1);
        			x-=speed;
        		}else{
        			plr.isCrashing();
        		}
        	}else{
        		x+=speed;
        	}
    	}else if (plr.getDirection() == 4){
    		//down
    		if((map[x][y+1] == 1) || (map[x+1][y+1] == 1) || (map[x-1][y+1] == 1)){
    			//location or surrounding locations contain a wall
    			if(plr.getPrevDirection() == 2){
    				//press up while traveling down
    				plr.setDirection(2);		//continue in this direction
    				plr.setPrevDirection(2);
    				y-=speed;
    			}else{
    				plr.isCrashing();
    			}
    		}else{
    			y+=speed;
    		}
    	}else if (plr.getDirection() == 1){
    		//left
    		if((map[x-1][y] == 1) || (map[x-1][y+1] == 1) || (map[x-1][y-1] == 1)){
    			//location or surrounding locations contain a wall
    			if(plr.getPrevDirection() == 3){
    				plr.setDirection(3);
    				plr.setPrevDirection(3);
    				x+=speed;
    			}else{
    				plr.isCrashing();
    			}
    		}else{
    			x-=speed;
    		}
    	}else{
    		//up
    		if((map[x][y-1] == 1) || (map[x-1][y-1] == 1) || (map[x+1][y-1] == 1)){
    			//location or surrounding locations contain a wall
    			if(plr.getPrevDirection() == 4){
    				plr.setDirection(4);
    				plr.setPrevDirection(4);
    				y+=speed;
    			}else{
    				plr.isCrashing();
    			}
    		}else{
    			y-=speed;
    		}
    	}
		
		//update new position
		plr.setPositionX(x);
		plr.setPositionY(y);
		
	}
	
	//check if player has crashed
	public Boolean checkCrash(Player plr){
		return plr.hasCrashed();
	}
	
	//updates map walls with player's location
	public void updateMap(Player plr){
		map[plr.getPositionX()][plr.getPositionY()] = 1;
	}
	
	//returns dimension of 2d array
	public int getDimension(){
		return DIMENSION;
	}
	
	
	
}
