import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

class Bricks {
    int[][] allBricks;
    int brickWidth;
    int brickHeight;
    Bricks(int row, int col){
        allBricks = new int[row][col];
        for(int i = 0; i < allBricks.length; i++){
            for(int j = 0; j < allBricks[0].length; j++){
                allBricks[i][j] = 1;
            }
        }
        brickWidth = 500 / col;
        brickHeight = 100 / row;
    }
    
    void draw(Graphics2D g){
        for(int i = 0; i < allBricks.length; i++){
            for(int j = 0; j < allBricks[0].length; j++){
                if(allBricks[i][j] > 0){
                    g.setColor(Color.orange);
                    g.fillRect(j*brickWidth+78,i*brickHeight+48,brickWidth,brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+78,i*brickHeight+48,brickWidth,brickHeight);
                }
            }
        }
    }
    
    void setBrick(int value, int row, int col){
        allBricks[row][col] = value;
    }
    
}