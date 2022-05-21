import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public App(){
        setPreferredSize(new Dimension(new Dimension(600, 900)));
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var drawingArea = new JPanel();
        drawingArea.setBackground(Color.PINK);
        drawingArea.setSize(getPreferredSize());
        add(drawingArea);
        pack();
    }
    public static void main(String[] args) {
        App app= new App();
        app.setVisible(true);
    }
}