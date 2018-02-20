import javax.swing.JButton;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Cell extends JButton{
    int number;
    boolean isMine;
    int mineCount;
    int row;
    int col;
    static int totalMines;
    
    Cell(){
    }
    
    Cell(int k){
        number = k;
        if(number >= 9){
            isMine = true;
            mineCount = -1;
            totalMines++;
        }
        mineCount = 0;
    }
    
    void setTheText(){
        if(isMine){
            setText("*");
        } else {
            setText(""+mineCount);
        }
    }
    
    final int display(){
        if(isMine){
            System.out.print("*"+"      ");
        } else {
            System.out.print("Count: "+mineCount+"   ");
        }
        return mineCount;
    }
    
}