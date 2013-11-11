import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class GameplayUI extends JApplet implements KeyListener,  AdjustmentListener, ActionListener {
	
    private BufferedImage bi;		//will host the game
    private Graphics2D p1Stroke;	//individual paintbrush for each player and the map
    private Graphics2D p2Stroke;
    private Graphics2D mapStroke;
    private Timer frameClock;		//frame rate
    private Timer speedClock;		//game speed
    long startTime = 0;				//timer to eliminate double key presses
    long endTime;		

   // int[][] lines = new int[8][200];

    int DIMENSION = 600;			//dimension of game
    private int strokeSize = 6;		//size of player trail
    
    //int lineIndex=0;
    //int linesDrawn = 1;
   // boolean changeDirection = false;
    
    private User user1 = new User();	//instantiation for test purposes only, will be initialized to constructors once we implement global users
    private User user2 = new User();	
    
    private Player player1 = new Player(user1); 
    private Player player2 = new Player(user2);
    
    GameEngine game = new GameEngine(player1, player2, strokeSize);
    int SCALE_FACTOR = (DIMENSION/game.getDimension());		//ratio of 2d array size to screen dimension
    JLabel l;	//for testing
    JLabel plr1Crash;	//player 1 status
    JLabel plr2Crash;	//player 2 status
    
    /* Direction values:
     * 1 = left
     * 2 = up
     * 3 = right
     * 4 = down
     */
    
    public GameplayUI(){
    	//takes settings as constructor
    	//get settings
    }
    
    public void start(){
    	addKeyListener(this);
    	this.frameClock = new Timer(10,this);
    	this.speedClock = new Timer(40,this);//50 = low speed, 30 = med speed, 10
    	this.frameClock.start();
    	this.speedClock.start();
    }
	
	public void init(){

        setFocusable(true);
        bi = (BufferedImage) createImage(DIMENSION, DIMENSION);		//create the arena graphic
        
        mapStroke = bi.createGraphics();
    	mapStroke.setColor(Color.white);	//fill the arena white
    	mapStroke.fillRect(0, 0, DIMENSION, DIMENSION);
    	
    	mapStroke.setStroke(new BasicStroke(strokeSize + 2));
    	mapStroke.setColor(Color.black);
        
        p1Stroke = bi.createGraphics();
        p1Stroke.setColor(Color.blue);		//player 1 is blue
    	p1Stroke.setStroke(new BasicStroke(strokeSize));
    	
        p2Stroke = bi.createGraphics();
        p2Stroke.setColor(Color.red);		//player 2 is red
    	p2Stroke.setStroke(new BasicStroke(strokeSize));

        this.setSize(700,700);
        this.setLayout(null);
		startTime = System.currentTimeMillis();		//start time for key press timing
	
        //UI
        JPanel f = new JPanel();
        f.setLayout(null);
        f.setBounds(DIMENSION, 0, 800, 800);
	
        l = new JLabel("");
        l.setBounds(0,10,500,200);
        f.add(l);
        
        plr1Crash = new JLabel("not");
        plr1Crash.setBounds(0, 50, 600, 300);
        f.add(plr1Crash);
        
        plr2Crash = new JLabel("not");
        plr2Crash.setBounds(0, 100, 600, 300);
        f.add(plr2Crash);

        mapStroke.drawLine(1,1,1,DIMENSION-1);		//draw the borders
        mapStroke.drawLine(1,DIMENSION-1,DIMENSION-1,DIMENSION-1);
        mapStroke.drawLine(DIMENSION-1,1,DIMENSION-1,DIMENSION-1);
        mapStroke.drawLine(1,1,DIMENSION-1,1);
    	mapStroke.setStroke(new BasicStroke(strokeSize));

        this.add(f);
    }
	
	
    public void paint(Graphics g)
    {
    	//super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;
    	
    	//draw player 1 position
    	p1Stroke.drawLine(player1.getPrevPositionX()*SCALE_FACTOR, player1.getPrevPositionY()*SCALE_FACTOR
    			, player1.getPositionX()*SCALE_FACTOR, player1.getPositionY()*SCALE_FACTOR);
    	
    	//draw player 2 position
    	p2Stroke.drawLine(player2.getPrevPositionX()*SCALE_FACTOR, player2.getPrevPositionY()*SCALE_FACTOR
    			, player2.getPositionX()*SCALE_FACTOR, player2.getPositionY()*SCALE_FACTOR);

    	g2.drawImage(bi, null, 0, 0);
    }
    
    
    public void updateGame(){
    	
    	//update the game with the new positions, and check for crashing
    	game.updatePosition(player1);
    	game.updatePosition(player2);
    	
    	//update the game map with new positions
    	game.updateMap(player1);
    	game.updateMap(player2);
    	
    	//print on label when player crashes
    	if (player1.hasCrashed()){
    		plr1Crash.setText("P1 CRASH");
    		l.setText("dif: " + Long.toString(endTime - startTime));
    	}
    	if(player2.hasCrashed()){
    		plr2Crash.setText("P2 CRASH");
    	}
    	
    }
    
    public void update(Graphics g){ 
    	paint(g); 
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.frameClock) {
			repaint();	//actually update the frame
		}else if (e.getSource()==this.speedClock){
			updateGame();	//actually update the game
		}
		
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if(startTime == 0){
			startTime = System.currentTimeMillis();		//get the current time to start
		}
		endTime = System.currentTimeMillis();		//update the new time
		int keyCode = e.getKeyCode();
		
		if((endTime - startTime) < 20){		//if key presses are too close together, ignore
			l.setText("time error read");
			updateGame();
			return;		//dont register key press
		}
		
		//l.setText("s: " + startTime + ", e: " + endTime);

		//update the last direction
		player1.setPrevDirection(player1.getDirection());
		player2.setPrevDirection(player2.getDirection());
		
		if(keyCode == 37){
			//left
			player1.setDirection(1);

		}else if (keyCode == 38){
			//up
			player1.setDirection(2);

		}else if (keyCode == 39){
			//right
			player1.setDirection(3);

		}else if (keyCode == 40){
			//down
			player1.setDirection(4);

		}else if (keyCode == 65){
			//a = left
			player2.setDirection(1);
		}else if (keyCode == 87){
			//w = up
			player2.setDirection(2);
		}else if (keyCode == 68){
			//d = right
			player2.setDirection(3);
		}else if (keyCode == 83){
			//s = down
			player2.setDirection(4);
		}

		startTime = System.currentTimeMillis();
		
		updateGame();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}
	

}
