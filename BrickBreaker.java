import javax.swing.JFrame;

class BrickBreaker{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        BrickBreakerPanel bPanel = new BrickBreakerPanel();
        frame.setBounds(10,10,700,600);
        frame.setTitle("Brick Breaker");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(bPanel);
        frame.setVisible(true);
    }
}