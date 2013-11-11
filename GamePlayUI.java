import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class GameplayUI extends JApplet implements KeyListener,  AdjustmentListener, ActionListener {
	
    BufferedImage bi;
    Graphics2D p1Stroke;
    Graphics2D p2Stroke;
    Graphics2D mapStroke;
    Timer frameClock;
    Timer speedClock;
   // int x = 100;
    //int y = 200;
    int[][] lines = new int[8][200];

    int DIMENSION = 600;
    //int[][] map = new int[DIMENSION][DIMENSION];
    
    int lineIndex=0;
    int linesDrawn = 1;
    boolean changeDirection = false;
    private int strokeSize = 6;
    private int SCALE_FACTOR = 3;
    
    private User user1 = new User();
    private User user2 = new User();
    
    private Player player1 = new Player(user1); //will be initialized to constructors once we implement global users
    private Player player2 = new Player(user2);
    
    GameEngine game = new GameEngine(player1, player2);
    
    Label l;
    
    /* Direction values:
     * 1 = left
     * 2 = up
     * 3 = right
     * 4 = down
     */
    //int direction = 3;
    //int prevDir = direction;

    
    //int x0 = 100;
    //int y0 = 200;
    
    public GameplayUI(){
    	//takes settings and users as constructor
    	//get settings
    	
    }
    
    public void start(){
    	addKeyListener(this);
    	this.frameClock = new Timer(10,this);
    	this.speedClock = new Timer(30,this)
    	this.frameClock.start();
    	this.speedClock.start();
    }
	
    public void init(){

        setFocusable(true);
        bi = (BufferedImage) createImage(DIMENSION, DIMENSION);
        
        mapStroke = bi.createGraphics();
    	mapStroke.setColor(Color.white);
    	mapStroke.fillRect(0, 0, DIMENSION, DIMENSION);
    	
    	mapStroke.setStroke(new BasicStroke(strokeSize + 2));
    	mapStroke.setColor(Color.black);
        
        p1Stroke = bi.createGraphics();
        p1Stroke.setColor(Color.blue);
    	p1Stroke.setStroke(new BasicStroke(strokeSize));
    	
        p2Stroke = bi.createGraphics();
        p2Stroke.setColor(Color.red);
    	p2Stroke.setStroke(new BasicStroke(strokeSize));

        this.setSize(700,700);
        this.setLayout(null);
	
        //UI
        JPanel f = new JPanel();
        f.setLayout(null);
        f.setBounds(DIMENSION, 0, 800, 800);
	
        l = new JLabel("WORKING");
        l.setBounds(0,10,500,200);
        f.add(l);
        
	
        
        mapStroke.drawLine(1,1,1,DIMENSION-1);
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

    	p1Stroke.drawLine(player1.getPrevPositionX()*SCALE_FACTOR, player1.getPrevPositionY()*SCALE_FACTOR, player1.getPositionX()*SCALE_FACTOR, player1.getPositionY()*SCALE_FACTOR);
    	p2Stroke.drawLine(player2.getPrevPositionX()*SCALE_FACTOR, player2.getPrevPositionY()*SCALE_FACTOR, player2.getPositionX()*SCALE_FACTOR, player2.getPositionY()*SCALE_FACTOR);

    	g2.drawImage(bi, null, 0, 0);
    }
    
    
    public void updateGame(){
    	
    	game.updatePosition(player1, player1.getDirection(), player1.getPositionX(), player1.getPositionY());
    	game.updatePosition(player2, player2.getDirection(), player2.getPositionX(), player2.getPositionY());
    	
    	if (game.getCrashed()){
    		l.setText("CRASHED");
    	}

    	
    	
    	game.updateMap(player1.getPositionX(), player1.getPositionY());
    	game.updateMap(player2.getPositionX(), player2.getPositionY());
    	

    }
    
    public void update(Graphics g){ 
    	paint(g); 
    }

	@Override
    public void actionPerformed(ActionEvent e) {
 	if (e.getSource()==this.frameClock) {
		repaint();
	}else if (e.getSource() == this.speedClock){
		updateGame();
	}
		
    }

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//char i = e.getKeyChar();
		//l.setText(Character.toString(i));
		
		int keyCode = e.getKeyCode();
		l.setText(Integer.toString(keyCode));
		
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
		

		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}
	

}
