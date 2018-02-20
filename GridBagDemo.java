import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GridBagDemo{
    JButton b1, b2, b3, b4, b5;
    GridBagConstraints gbc = new GridBagConstraints();
    JFrame frame;
    JPanel panel;
    
    public static void main(String[] args){
        GridBagDemo g = new GridBagDemo();
        g.go();
    }
    
    void go(){
        JFrame frame = new JFrame("GridBagDemo");
        frame.setSize(700,700);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        b1 = new JButton("Button 1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(b1,gbc);
        b2 = new JButton("Button 2");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(b2,gbc);
        b3 = new JButton("Button 3");
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(b3,gbc);
        b4 = new JButton("Button 4");
        gbc.gridx = 3;
        gbc.gridy = 3;
        panel.add(b4,gbc);
        b5 = new JButton("Button 5");
        gbc.gridx = 4;
        gbc.gridy = 4;
        panel.add(b5,gbc);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        
    }
}