import javax.swing.JButton;

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
            setText("Mine");
        } else {
            setText(""+mineCount);
        }
    }
    
    final int display(){
        if(isMine){
            System.out.print("Mine"+"      ");
        } else {
            System.out.print("Count: "+mineCount+"   ");
        }
        return mineCount;
    }
}