import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MapImage extends JPanel {
    static enum Mode {
        DEFAULT,
        AUTO,
        REGION,
        ROOM
    }
    private BufferedImage DEFAULT_IMAGE;
    private Mode currentMode;
    private static int DEFAULT_WIDTH = 600;
    private static int DEFAULT_HEIGHT = 702;
    public MapImage(Mode mode){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        currentMode = Mode.DEFAULT;
        setBackground(Color.BLUE);
        try {
            DEFAULT_IMAGE = ImageIO.read(new File("src/DirtMap.png"));
        } catch (IOException ex) {
            System.out.println("Error loading pic");
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(DEFAULT_IMAGE, 0, 0, this); // see javadoc for more info on the parameters
    }
    public void setMode(Mode mode){
        currentMode = mode;
        updateImage();
    }

    private void updateImage() {
        String path = "src/DirtMap.png";
        switch (currentMode){
            case DEFAULT :  path = new String("src/DirtMap.png");
                break;
            case AUTO:      path = new String("src/auto.png");
                break;
            case REGION:    path = new String("src/region.png");
                break;
            case ROOM:      path = new String("src/room.png");
                break;
        }
        try {
            DEFAULT_IMAGE = ImageIO.read(new File(path));
        } catch (IOException ex) {
            System.out.println("Error loading pic");
        }
        repaint();
    }
}
