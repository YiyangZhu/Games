import javax.swing.JFrame;
import java.awt.Color;

class Snake{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        //set properties of frame
        frame.setBounds(10,10,905,700);
        frame.setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setVisible(true);
    }
}