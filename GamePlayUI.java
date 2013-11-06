

import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class GameplayUI extends Applet implements KeyListener,  AdjustmentListener, ActionListener {
	
    BufferedImage bi;
    Graphics2D u1Stroke;
    Graphics2D u2Stroke;
    Timer clock;
   // int x = 100;
    //int y = 200;
    int[][] lines = new int[8][200];

    int DIMENSION = 600;
    //int[][] map = new int[DIMENSION][DIMENSION];
    
    int lineIndex=0;
    int linesDrawn = 1;
    boolean changeDirection = false;
    
    
    public User user1 = new User(); //will be initialized to constructors once we implement global users
    private User user2 = new User();
    
    GameEngine game = new GameEngine(user1, user2);
    
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
    	//takes settings as constructor
    	//get settings
    	
    }
    
    public void start(){
    	addKeyListener(this);
    	this.clock = new Timer(10,this);
    	this.clock.start();
    }
	
	public void init(){

        setFocusable(true);
        bi = (BufferedImage) createImage(DIMENSION, DIMENSION);
        u1Stroke = bi.createGraphics();
        u2Stroke = bi.createGraphics();
        
        this.setSize(900,900);
        this.setLayout(null);
	
        //UI
        Panel f = new Panel();
        f.setLayout(null);
        f.setBounds(DIMENSION, 0, 800, 800);
	
        l = new Label("WORKING");
       // l.setText("HOW ARE YOU");
        l.setBounds(0,10,500,200);
        f.add(l);
        
        //leftWall
        lines[0][lineIndex] = 1;
        lines[1][lineIndex] = 1;
        lines[2][lineIndex] = 1;
        lines[3][lineIndex] = DIMENSION - 1;
        lineIndex++;
        linesDrawn++;
        
        
        //bottomWall
        lines[0][lineIndex] = 1;
        lines[1][lineIndex] = DIMENSION-1;
        lines[2][lineIndex] = DIMENSION-1;
        lines[3][lineIndex] = DIMENSION-1;
        lineIndex++;
        linesDrawn++;
        
        
        //rightWall
        lines[0][lineIndex] = DIMENSION-1;
        lines[1][lineIndex] = 1;
        lines[2][lineIndex] = DIMENSION-1;
        lines[3][lineIndex] = DIMENSION-1;
        lineIndex++;
        linesDrawn++;
        
        //topWall
        lines[0][lineIndex] = 1;
        lines[1][lineIndex] = 1;
        lines[2][lineIndex] = DIMENSION-1;
        lines[3][lineIndex] = 1;
        lineIndex++;
        linesDrawn++;
        
        lines[0][lineIndex] = user1.getPositionX();
        lines[1][lineIndex] = user1.getPositionY();
        lines[4][lineIndex] = user2.getPositionX();
        lines[5][lineIndex] = user2.getPositionY();
        
        
        //input text field
      /*  TextField txt = new TextField("HI THERE");
    	txt.setSize(40,40);
    	txt.setBounds(0,50,50,100);
    	f.add(txt);*/

        this.add(f);
    }
	
	
    public void paint(Graphics g)
    {
    	//super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;

    	// clear screen
    	u1Stroke.setColor(Color.white);
    	u1Stroke.fillRect(0, 0, DIMENSION, DIMENSION);
    	u2Stroke.setColor(Color.white);
    	u2Stroke.fillRect(0, 0, DIMENSION, DIMENSION);

    	updateGame();

    	g2.drawImage(bi, null, 0, 0);
    }
    
    
    public void updateGame(){
    	u1Stroke.setStroke(new BasicStroke(4));
    	u1Stroke.setColor(Color.black);
    	u2Stroke.setStroke(new BasicStroke(4));
    	u2Stroke.setColor(Color.red);
    	
    	game.updatePosition(user1, user1.getDirection(), user1.getPositionX(), user1.getPositionY());
    	game.updatePosition(user2, user2.getDirection(), user2.getPositionX(), user2.getPositionY());
    	
    	if (game.getCrashed()){
    		l.setText("CRASHED");
    	}

    	
    	//update new position
    	lines[2][lineIndex] = user1.getPositionX();
    	lines[3][lineIndex] = user1.getPositionY();
    	lines[6][lineIndex] = user2.getPositionX();
    	lines[7][lineIndex] = user2.getPositionY();
    	
    	
    	for(int i=0; i < linesDrawn; i++){
    		u1Stroke.drawLine(lines[0][i], lines[1][i], lines[2][i], lines[3][i]);
    		u2Stroke.drawLine(lines[4][i], lines[5][i], lines[6][i], lines[7][i]);
    	}
    	
    	game.updateMap(user1.getPositionX(), user1.getPositionY());
    	game.updateMap(user2.getPositionX(), user2.getPositionY());
    	

    }
    
    public void update(Graphics g){ 
    	paint(g); 
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.clock) {
			repaint();
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
		
		user1.setPrevDirection(user1.getDirection());
		user2.setPrevDirection(user2.getDirection());
		
		if(keyCode == 37){
			//left
			user1.setDirection(1);
		}else if (keyCode == 38){
			//up
			user1.setDirection(2);
		}else if (keyCode == 39){
			//right
			user1.setDirection(3);
		}else if (keyCode == 40){
			//down
			user1.setDirection(4);
		}else if (keyCode == 65){
			//a = left
			user2.setDirection(1);
		}else if (keyCode == 87){
			//w = up
			user2.setDirection(2);
		}else if (keyCode == 68){
			//d = right
			user2.setDirection(3);
		}else if (keyCode == 83){
			//s = down
			user2.setDirection(4);
		}
		
		if((user1.getPrevDirection() != user1.getDirection())||(user2.getPrevDirection() != user2.getDirection())){
			lineIndex++;
			linesDrawn++;
			lines[0][lineIndex] = user1.getPositionX();
			lines[1][lineIndex] = user1.getPositionY();
			lines[4][lineIndex] = user2.getPositionX();
			lines[5][lineIndex] = user2.getPositionY();
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}
	

}
