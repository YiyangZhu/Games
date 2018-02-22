import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

class BrickBreakerPanel extends JPanel implements KeyListener, ActionListener{
    boolean play = false;
    int score = 0;
    int totalBricks = 20;
    Timer timer;
    int delay = 8;
    int ballPosX = 660;
    int ballPosY = 500;
    int ballXDir = -1;
    int ballYDir = -2;
    int playerX = 300;
    Bricks bricks;
    
    BrickBreakerPanel(){
        bricks = new Bricks(4,5);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
        
    }
    
    public void paint(Graphics g){
        //set panel background and border color
        g.setColor(Color.black);
        g.fillRect(10,10,680,560);
        g.setColor(Color.GRAY);
        g.fillRect(0,0,10,590);
        g.fillRect(0,0,690,10);
        g.fillRect(690,0,10,580);
        g.fillRect(10,570,680,10);
        //set ball and player
        g.setColor(Color.orange);
        g.fillRect(playerX, 560,100,10);
        g.setColor(Color.white);
        g.fillOval(ballPosX,ballPosY,22,22);
        bricks.draw((Graphics2D) g);
        //set scores
        g.setColor(Color.orange);
        g.setFont(new Font("SansSerif",Font.ITALIC,20));
        g.drawString("Score: "+score, 30,30);
        //check win conditions
        if(totalBricks <= 0){
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("SansSerif",Font.ITALIC,50));
            g.drawString("You win!", 240,300);
            g.drawString("Total Score: "+score, 200,350);
            g.setFont(new Font("SansSerif",Font.ITALIC,30));
            g.drawString("You can press enter to restart", 150,400);
        }
        //check game over conditions
        if(ballPosY > 570){
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("SansSerif",Font.ITALIC,50));
            g.drawString("Game Over", 200,300);
            g.drawString("Score: "+score, 240,350);
            g.setFont(new Font("SansSerif",Font.ITALIC,30));
            g.drawString("Press enter to restart", 190,400);
        } 
        g.dispose();
    }
    
    public void actionPerformed(ActionEvent e){
        timer.start();
        if(play){
            if(new Rectangle(ballPosX,ballPosY,22,22).intersects(new Rectangle(playerX,550,100,10))){
                ballYDir = -ballYDir;
            }
            
            BrickLoop: for(int i = 0; i < bricks.allBricks.length; i++){
                for(int j = 0; j < bricks.allBricks[0].length; j++){
                    if(bricks.allBricks[i][j] > 0){
                        int brickX = j * bricks.brickWidth + 78;
                        int brickY = i * bricks.brickHeight + 48;
                        int brickWidth = bricks.brickWidth;
                        int brickHeight = bricks.brickHeight;
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 22,22);
                        Rectangle brickRect = rect;
                        if(ballRect.intersects(brickRect)){
                            bricks.setBrick(0,i,j);
                            totalBricks--;
                            score++;
                            if(ballPosX+18<=brickRect.x || ballPosX>=brickRect.x+brickRect.width){
                                ballXDir = - ballXDir;
                            } else {
                                ballYDir = - ballYDir;
                            }
                            break BrickLoop;
                        }   
                    }
                }
            }
            
            ballPosX += ballXDir;
            ballPosY += ballYDir;
            if(ballPosX < 10){
                ballXDir = -ballXDir;
            }
            if(ballPosY < 10){
                ballYDir = -ballYDir;
            }
            if(ballPosX > 660){
                ballXDir = -ballXDir;
            }
        }
        
        repaint();
    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 580){
                playerX = 590;
            } else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 20){
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        
        if((e.getKeyCode() == KeyEvent.VK_ENTER)){
            if(!play){
                play = true;
                
                ballPosX = (int)(Math.random()*650+10);
                ballPosY = (int)(Math.random()*400+160);
                ballXDir = -1;
                ballYDir = -2;
                playerX = 300;
                score = 0;
                totalBricks = 20;
                bricks = new Bricks(4,5);
                repaint();
            }
        }
    }
    
    void moveRight(){
        play = true;
        playerX += 20;
    }
    
    void moveLeft(){
        play = true;
        playerX -= 20;
    }
    //no need to implement
    public void keyReleased(KeyEvent e){
    }
    //no need to implement
    public void keyTyped(KeyEvent e){
    }
    
    
}