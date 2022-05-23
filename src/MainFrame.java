import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame{
    public static ArrayList<String> robotNames;
    private StatsWorker statsWorker;
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
    private JProgressBar batteryProgressBar;
    private JPanel all;
    private JProgressBar binProgressBar;
    private JProgressBar jobProgressBar;
    private JLabel jobText;

    public MainFrame() {
        initNames();
        statsWorker = new StatsWorker();
        statsWorker.generateValues(robotNames);
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
                dialogBox("Job Sent Successfully");
            }
        });
        REGIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.REGION);
                dialogBox("Please Select Regions");
            }
        });
        ROOMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.ROOM);
                dialogBox("Please Select Rooms");
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
        selectRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = selectRobot.getSelectedIndex();
                System.out.printf("COMBO VALUE = %d\n", selected);
                RobotValues robotValues= statsWorker.getRobotValues(selected);
                batteryProgressBar.setValue(robotValues.getBattery());
                binProgressBar.setValue(robotValues.getBinLevel());
                jobProgressBar.setValue(robotValues.getJob().getJobCompleteness());
                jobText.setText(robotValues.getJob().getJobType());
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

    private void createUIComponents() {// place custom component creation code here
        map = new JPanel();
        map.setLayout(new GridBagLayout());
        map.setBackground(Color.GRAY);
        mapImage = new MapImage(MapImage.Mode.DEFAULT);
        GridBagConstraints c = new GridBagConstraints();// TODO: CENTER THIS SHIT
        map.add(mapImage, c);
        jobText = new JLabel("Default");
    }
    private void initNames(){
        robotNames = new ArrayList<>();
        robotNames.add("GLaDOS");
        robotNames.add("Arturito");
        robotNames.add("C-3PO");
        robotNames.add("DestroyerOfWorlds3000");
    }
    private void dialogBox(String msg){
        JOptionPane.showMessageDialog(this,msg);
}
}
