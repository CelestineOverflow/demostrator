import RMI_Implementation.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    public static ArrayList<String> robotNames;
    private RobotDatabase robotDatabase;
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
    private JCheckBox setScheduleCheckBox;
    private JLabel Time;
    private JComboBox hour;
    private JComboBox minute;
    private int currentRobotId;
    private Time scheduleTime;

    public MainFrame() throws RemoteException {
        initNames();
        robotDatabase = new RobotDatabase();
        robotDatabase.generateValues(robotNames);
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
                if (scheduleTime == null) {
                    InstructionController.getInstance().addInstruction("START AUTO", robotDatabase.getRobotValues(currentRobotId));
                } else {
                    InstructionController.getInstance().addInstruction("START AUTO", robotDatabase.getRobotValues(currentRobotId), scheduleTime);
                }
            }
        });
        REGIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.REGION);
                dialogBox("Please Select Regions");
                mapImage.setMode(MapImage.Mode.AUTO);
                if (scheduleTime == null) {
                    InstructionController.getInstance().addInstruction("START REGION", robotDatabase.getRobotValues(currentRobotId));
                } else {
                    InstructionController.getInstance().addInstruction("START REGION", robotDatabase.getRobotValues(currentRobotId), scheduleTime);
                }
            }
        });
        ROOMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.ROOM);
                dialogBox("Please Select Rooms");
                if (scheduleTime == null) {
                    InstructionController.getInstance().addInstruction("START ROOM", robotDatabase.getRobotValues(currentRobotId));
                } else {
                    InstructionController.getInstance().addInstruction("START ROOM", robotDatabase.getRobotValues(currentRobotId), scheduleTime);
                }
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
                currentRobotId = selectRobot.getSelectedIndex();
                RobotInstance robotValues = robotDatabase.getRobotValues(currentRobotId);
                batteryProgressBar.setValue(robotValues.getBattery());
                binProgressBar.setValue(robotValues.getBinLevel());
                jobProgressBar.setValue(robotValues.getJob().getJobCompleteness());
                jobText.setText(robotValues.getJob().getJobType());
            }
        });

        setScheduleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == setScheduleCheckBox) {
                    if (setScheduleCheckBox.isEnabled()) {
                        setNewSchedule();
                    } else {
                        scheduleTime = null;
                    }
                }
            }
        });
    }

    private void setNewSchedule() {
        scheduleTime = new Time(hour.getSelectedIndex(), minute.getSelectedIndex());
        scheduleTime.printTime();
    }

    public static void main(String[] args) throws RemoteException {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    private void createUIComponents() {// place custom component creation code here1
        ArrayList<String> hourArray = new ArrayList<>();
        ArrayList<String> minuteArray = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            hourArray.add(String.valueOf(i));
        }
        for (int i = 0; i <= 59; i++) {
            minuteArray.add(String.valueOf(i));
        }
        hour = new JComboBox(hourArray.toArray());
        minute = new JComboBox(minuteArray.toArray());
        map = new JPanel();
        map.setLayout(new GridBagLayout());
        map.setBackground(Color.GRAY);
        mapImage = new MapImage(MapImage.Mode.DEFAULT);
        GridBagConstraints c = new GridBagConstraints();// TODO: CENTER THIS SHIT
        map.add(mapImage, c);
        jobText = new JLabel("Default");
    }

    private void initNames() {
        robotNames = new ArrayList<>();
        robotNames.add("GLaDOS");
        robotNames.add("Arturito");
        robotNames.add("C-3PO");
        robotNames.add("DestroyerOfWorlds3000");
    }

    private void dialogBox(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

}
