import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MapImage extends JPanel {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 702;
    private BufferedImage DEFAULT_IMAGE;
    private Mode currentMode;

    public MapImage(Mode mode) {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        currentMode = Mode.DEFAULT;
        setBackground(Color.BLUE);
        try {
            DEFAULT_IMAGE = ImageIO.read(new File("src/textures/DirtMap.png"));
        } catch (IOException ex) {
            System.out.println("Error loading pic");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(DEFAULT_IMAGE, 0, 0, this); // see javadoc for more info on the parameters
    }

    public void setMode(Mode mode) {
        currentMode = mode;
        String path = "src/DirtMap.png";
        switch (currentMode) {
            case DEFAULT:
                path = "src/textures/DirtMap.png";
                break;
            case AUTO:
                path = "src/textures/auto.png";
                break;
            case REGION:
                path = "src/textures/region.png";
                break;
            case ROOM:
                path = "src/textures/room.png";
                break;
        }
        try {
            DEFAULT_IMAGE = ImageIO.read(new File(path));
        } catch (IOException ex) {
            System.out.println("Error loading pic");
        }
        repaint();
    }

    public enum Mode {
        DEFAULT,
        AUTO,
        REGION,
        ROOM
    }
}
