import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

class GamePanel extends JPanel implements KeyListener, ActionListener
{
  int[] snakeXLength = new int['ˮ'];
  int[] snakeYLength = new int['ˮ'];
  
  boolean left = false;
  boolean right = false;
  boolean up = false;
  boolean down = false;
  
  ImageIcon titleImage;
  
  ImageIcon rightMouth;
  ImageIcon upMouth;
  ImageIcon leftMouth;
  ImageIcon downMouth;
  int lengthOfSnake = 3;
  Timer timer;
  int delay = 100;
  ImageIcon snakeBody;
  int moves = 0;
  int[] enemyXPos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 800 };
  

  int[] enemyYPos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625 };
  
  ImageIcon enemyImage;
  Random random = new Random();
  int xPos = random.nextInt(34);
  int yPos = random.nextInt(23);
  int score;
  
  GamePanel() {
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay, this);
    timer.start();
  }
  
  public void paint(Graphics g)
  {
    if (moves == 0) {
      snakeXLength[2] = 50;
      snakeXLength[1] = 75;
      snakeXLength[0] = 100;
      snakeYLength[2] = 100;
      snakeYLength[1] = 100;
      snakeYLength[0] = 100;
    }
    

    g.setColor(Color.white);
    g.drawRect(24, 10, 851, 55);
    titleImage = new ImageIcon("snakeTitle.png");
    titleImage.paintIcon(this, g, 25, 11);
    

    g.setColor(Color.white);
    g.drawRect(24, 74, 851, 577);
    g.setColor(Color.black);
    g.fillRect(25, 75, 850, 575);
    

    g.setColor(new Color(51, 0, 0));
    g.setFont(new Font("Serif", 1, 20));
    g.drawString("Scores:  " + score, 750, 30);
    

    g.setColor(new Color(51, 0, 0));
    g.setFont(new Font("Serif", 1, 20));
    g.drawString("Length: " + lengthOfSnake, 750, 50);
    

    rightMouth = new ImageIcon("snakeMouthRight25.png");
    rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
    for (int i = 0; i < lengthOfSnake; i++) {
      if ((i == 0) && (right)) {
        rightMouth = new ImageIcon("snakeMouthRight25.png");
        rightMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
      }
      if ((i == 0) && (left)) {
        leftMouth = new ImageIcon("snakeMouthLeft25.png");
        leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
      }
      if ((i == 0) && (up)) {
        upMouth = new ImageIcon("snakeMouthUp2.png");
        upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
      }
      if ((i == 0) && (down)) {
        downMouth = new ImageIcon("snakeMouthDown25.png");
        downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
      }
      
      if (i != 0) {
        snakeBody = new ImageIcon("snakeBody25.png");
        snakeBody.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
      }
    }
    enemyImage = new ImageIcon("bone25.png");
    if ((enemyXPos[xPos] == snakeXLength[0]) && (enemyYPos[yPos] == snakeYLength[0])) {
      score += 1;
      lengthOfSnake += 1;
      xPos = random.nextInt(34);
      yPos = random.nextInt(23);
    }
    enemyImage.paintIcon(this, g, enemyXPos[xPos], enemyYPos[yPos]);
    for (int b = 1; b < lengthOfSnake; b++) {
      if ((snakeXLength[b] == snakeXLength[0]) && (snakeYLength[b] == snakeYLength[0])) {
        right = false;
        left = false;
        up = false;
        down = false;
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 0, 50));
        g.drawString("Game Over", 300, 300);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 0, 25));
        g.drawString("Press Space to Restart", 300, 350);
      }
    }
    
    g.dispose();
  }
  


  public void actionPerformed(ActionEvent e)
  {
    timer.start();
    if (right) {
      for (int r = lengthOfSnake - 1; r >= 0; r--) {
        snakeYLength[(r + 1)] = snakeYLength[r];
      }
      for (int r = lengthOfSnake; r >= 0; r--) {
        if (r == 0) {
          snakeXLength[r] += 25;
        } else {
          snakeXLength[r] = snakeXLength[(r - 1)];
        }
        if (snakeXLength[r] > 850) {
          snakeXLength[r] = 25;
        }
      }
      repaint();
    }
    
    if (left) {
      for (int r = lengthOfSnake - 1; r >= 0; r--) {
        snakeYLength[(r + 1)] = snakeYLength[r];
      }
      for (int r = lengthOfSnake; r >= 0; r--) {
        if (r == 0) {
          snakeXLength[r] -= 25;
        } else {
          snakeXLength[r] = snakeXLength[(r - 1)];
        }
        if (snakeXLength[r] < 25) {
          snakeXLength[r] = 850;
        }
      }
      repaint();
    }
    if (down) {
      for (int r = lengthOfSnake - 1; r >= 0; r--) {
        snakeXLength[(r + 1)] = snakeXLength[r];
      }
      for (int r = lengthOfSnake; r >= 0; r--) {
        if (r == 0) {
          snakeYLength[r] += 25;
        } else {
          snakeYLength[r] = snakeYLength[(r - 1)];
        }
        if (snakeYLength[r] > 625) {
          snakeYLength[r] = 75;
        }
      }
      repaint();
    }
    if (up) {
      for (int r = lengthOfSnake - 1; r >= 0; r--) {
        snakeXLength[(r + 1)] = snakeXLength[r];
      }
      for (int r = lengthOfSnake; r >= 0; r--) {
        if (r == 0) {
          snakeYLength[r] -= 25;
        } else {
          snakeYLength[r] = snakeYLength[(r - 1)];
        }
        if (snakeYLength[r] < 75) {
          snakeYLength[r] = 625;
        }
      }
      repaint();
    }
  }
  

  public void keyTyped(KeyEvent e) {}
  

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == 32) {
      moves = 0;
      score = 0;
      lengthOfSnake = 3;
      repaint();
    }
    
    if (e.getKeyCode() == 39) {
      moves += 1;
      if (!left) {
        right = true;
      } else {
        right = false;
        left = true;
      }
      up = false;
      down = false;
    }
    if (e.getKeyCode() == 37) {
      moves += 1;
      if (!right) {
        left = true;
      } else {
        left = false;
        right = true;
      }
      up = false;
      down = false;
    }
    if (e.getKeyCode() == 38) {
      moves += 1;
      if (!down) {
        up = true;
      } else {
        up = false;
        down = true;
      }
      right = false;
      left = false;
    }
    if (e.getKeyCode() == 40) {
      moves += 1;
      if (!up) {
        down = true;
      } else {
        down = false;
        up = true;
      }
      right = false;
      left = false;
    }
  }
  
  public void keyReleased(KeyEvent e) {}
}
