import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeper implements MouseListener{
    static Cell[][] a;
    static int n;
    static int leftMine;
    static int remains;
    static GridBagConstraints gbc = new GridBagConstraints();
    static int chances = 6;
    static int count;
    
    public static void main(String args[]){
        generateMines();
        remains = n*n - Cell.totalMines;
        setAdjacentArea();
        setPanel();
    }
    
    static void setPanel(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JFrame frame = new JFrame("Mine Sweeper");
        frame.setSize(800,800);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu game = new JMenu("Game");
        //JMenu options = new JMenu("Options");
        JMenu help = new JMenu("Help"); 
        menuBar.add(game);
        //menuBar.add(options);
        menuBar.add(help);
        //JMenuItem start = new JMenuItem("start");
        //JMenuItem end = new JMenuItem("end");
        JMenuItem exit = new JMenuItem("exit");
        //game.add(start);
        //game.add(end);
        game.add(exit);
        //JMenuItem setSize = new JMenuItem("set size");
        //JMenuItem setQuantities = new JMenuItem("set mine quantities");
        //JMenuItem setDifficulty = new JMenuItem("different level");
        //options.add(setSize);
        //options.add(setQuantities);
        //options.add(setDifficulty);
        JMenu contact = new JMenu("contact us");
        JMenuItem contact1 = new JMenuItem("ererandkeke@gmail.com");
        help.add(contact);
        contact.add(contact1);
        JMenu howToPlay = new JMenu("how to play");
        JMenuItem instructions = new JMenuItem("refer to the last three lines");
        help.add(howToPlay);
        howToPlay.add(instructions);
        
        class exitaction implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }
        
        exit.addActionListener(new exitaction());
        
        panel.setLayout(new GridBagLayout());
        frame.getContentPane().add(panel);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                final int rows = i;
                final int columns = j;
                a[i][j].row = i;
                a[i][j].col = j;
                gbc.gridx = i;
                gbc.gridy = j;
                panel.add(a[i][j],gbc);
                
                a[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        a[rows][columns].setTheText();
                        if(a[rows][columns].isMine){
                            chances--;
                            a[rows][columns].setBackground(Color.black); 
                        }
                        if(chances == 0 && count == 0){
                            a[rows][columns].setText("Lose!");
                            count = 1;
                            JLabel label4 = new JLabel("The frame will be closed after several clicks.");
                            gbc.gridx = 0;
                            gbc.gridy = n+40;
                            gbc.gridheight = 10;
                            gbc.gridwidth = 600;
                            panel.add(label4,gbc);
                        }
                        if(chances == -1){
                            frame.setVisible(false);
                        }
                       
                    }
                });
                a[rows][columns].addMouseListener(new MouseListener(){
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
//right click to mark
                            a[rows][columns].setText("marked");
                            if(!a[rows][columns].isMine){
                                chances--;
                                a[rows][columns].setBackground(Color.black); 
                            }
                            
                            if(chances == 0 && count == 0){
                            a[rows][columns].setText("Lose!");
                            count = 1;
                            JLabel label4 = new JLabel("The frame will be closed after several clicks.");
                            gbc.gridx = 0;
                            gbc.gridy = n+40;
                            gbc.gridheight = 10;
                            gbc.gridwidth = 600;
                            panel.add(label4,gbc);
                        }
                        if(chances == -1){
                            frame.setVisible(false);
                        }
                        }
                    }
                    public void mouseExited(MouseEvent e) {
                    }
                    public void mouseEntered(MouseEvent e) {
                    }
                    public void mouseReleased(MouseEvent e) {
                    }
                    public void mousePressed(MouseEvent e) {
                    }
                    
                });
            }
        }
        JLabel label1 = new JLabel("Instructions: avoid clicking mines, once you click"+
                               " all adjacency areas of mines, you will win.");
        gbc.gridx = 0;
        gbc.gridy = n+10;
        gbc.gridheight = 10;
        gbc.gridwidth = 600;
        panel.add(label1,gbc);
        JLabel label2 = new JLabel("You have "+chances+" chances to click mines or mark incorrectly.");
        gbc.gridx = 0;
        gbc.gridy = n+20;
        gbc.gridheight = 10;
        gbc.gridwidth = 600;
        panel.add(label2,gbc);
        JLabel label3 = new JLabel("There are: "+Cell.totalMines+" mines.");
        gbc.gridx = 0;
        gbc.gridy = n+30;
        gbc.gridheight = 10;
        gbc.gridwidth = 600;
        panel.add(label3,gbc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    
    static void generateMines(){
        n = 9;
        a = new Cell[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int temp = (int) (Math.random()*12);
                a[i][j] = new Cell(temp);
                a[i][j].setForeground(Color.black);
                a[i][j].setOpaque(true);  
            }
        }
    }
    
    static void setAdjacentArea(){
        //four corners--up left
        if(a[0][0].isMine){
            a[0][1].mineCount++;
            a[1][0].mineCount++;
            a[1][1].mineCount++;
            leftMine++;
        }
        //four corners--up right
        if(a[0][n-1].isMine){
            a[0][n-2].mineCount++;
            a[1][n-1].mineCount++;
            a[1][n-2].mineCount++;
            leftMine++;
        }
        //four corners--bottom left
        if(a[n-1][0].isMine){
            a[n-1][1].mineCount++;
            a[n-2][0].mineCount++;
            a[n-2][1].mineCount++;
            leftMine++;
        }
        //four corners--bottom right
        if(a[n-1][n-1].isMine){
            a[n-1][n-2].mineCount++;
            a[n-2][n-1].mineCount++;
            a[n-2][n-2].mineCount++;
            leftMine++;
        }
        // four boundary lines--upper line
        for(int j = 1; j <= n-2;j++){
            if(a[0][j].isMine){
                a[0][j-1].mineCount++;
                a[1][j-1].mineCount++;
                a[1][j].mineCount++;
                a[1][j+1].mineCount++;
                a[0][j+1].mineCount++;
                leftMine++;
            }
        }
        // four boundary lines--left line
        for(int i = 1; i <= n-2;i++){
            if(a[i][0].isMine){
                a[i-1][0].mineCount++;
                a[i-1][1].mineCount++;
                a[i][1].mineCount++;
                a[i+1][1].mineCount++;
                a[i+1][0].mineCount++;
                leftMine++;
            }
        }
        // four boundary lines--right line
        for(int i = 1; i <= n-2;i++){
            if(a[i][n-1].isMine){
                a[i-1][n-1].mineCount++;
                a[i-1][n-2].mineCount++;
                a[i][n-2].mineCount++;
                a[i+1][n-2].mineCount++;
                a[i+1][n-1].mineCount++;
                leftMine++;
            }
        }
        // four boundary lines--bottom line
        for(int j = 1; j <= n-2;j++){
            if(a[n-1][j].isMine){
                a[n-1][j-1].mineCount++;
                a[n-2][j-1].mineCount++;
                a[n-2][j].mineCount++;
                a[n-2][j+1].mineCount++;
                a[n-1][j+1].mineCount++;
                leftMine++;
            }
        }
        //others
        for(int i = 1; i <= n-2;i++){
            for(int j = 1; j <= n-2; j++){
                if(a[i][j].isMine){
                    a[i-1][j-1].mineCount++;
                    a[i-1][j].mineCount++;
                    a[i-1][j+1].mineCount++;
                    a[i][j-1].mineCount++;
                    a[i][j+1].mineCount++;
                    a[i+1][j-1].mineCount++;
                    a[i+1][j].mineCount++;
                    a[i+1][j+1].mineCount++;
                    leftMine++;
                }
            }
        }
    }
    
    static void display(){
        for(Cell[] i: a){
            for(Cell j: i){
                j.display();
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    
    public void mouseClicked(MouseEvent e) {
    }
}