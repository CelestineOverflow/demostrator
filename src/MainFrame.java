import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JPanel ModeSelect;
    private JButton AUTOButton;
    private JButton REGIONButton;
    private JButton ROOMButton;
    private JPanel map;
    private MapImage mapImage;
    private JPanel NavBar;
    private JButton settingsButton;
    private JButton accountButton;
    private JComboBox selectRobot;
    private JPanel Stats;
    private JTextField batteryTextField;
    private JProgressBar progressBar1;
    private JPanel all;
    private String selectionOfRobots[] = new String[]{"GlaDos", "R2D2", ""};

    public MainFrame() {
        setContentPane(all);
        setSize(600, 900);
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        AUTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.AUTO);
                System.out.println("Auto");
            }
        });
        REGIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.REGION);
            }
        });
        ROOMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.ROOM);
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    private void createUIComponents() {// TODO: place custom component creation code here
        map = new JPanel();
        map.setLayout(new GridBagLayout());
        map.setBackground(Color.GRAY);
        mapImage = new MapImage(MapImage.Mode.DEFAULT);
        GridBagConstraints c = new GridBagConstraints();// TODO: CENTER THIS SHIT
        map.add(mapImage, c);
    }
}
