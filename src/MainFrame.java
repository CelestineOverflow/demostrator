import RMI_Implementation.Time;
import loginAMQ.LoggerUI;
import loginAMQ.LoginManager;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jms.JMSException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    public static ArrayList<String> robotNames;
    //private RobotDatabase robotDatabase;
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
    private InstructionController instructionController;

    public MainFrame() throws Exception {
        initInstructionController();
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
                    instructionController.addInstruction("START AUTO");
                } else {
                    instructionController.addInstruction("START AUTO", scheduleTime);
                }
            }
        });
        REGIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.REGION);
                dialogBox("Please Select Regions");
                if (scheduleTime == null) {
                    instructionController.addInstruction("START REGION");
                } else {
                    instructionController.addInstruction("START REGION", scheduleTime);
                }
            }
        });
        ROOMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapImage.setMode(MapImage.Mode.ROOM);
                dialogBox("Please Select Rooms");
                if (scheduleTime == null) {
                    instructionController.addInstruction("START ROOM");
                } else {
                    instructionController.addInstruction("START ROOM", scheduleTime);
                }
            }
        });
        settingsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialogBox("under construction ^^");
            }
        });
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoggerUI.test();
            }
        });
        selectRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentRobotId = selectRobot.getSelectedIndex();
                RMI_Implementation.Stats stats;
                try {
                    stats = instructionController.getRobotValues();
                    batteryProgressBar.setValue(stats.getBattery());
                    binProgressBar.setValue(stats.getBinLevel());
                    jobProgressBar.setValue(stats.getJob().getJobCompleteness());
                    jobText.setText(stats.getRobotName());
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

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

    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    private void setNewSchedule() {
        scheduleTime = new Time(hour.getSelectedIndex(), minute.getSelectedIndex());
        scheduleTime.printTime();
    }

    private void createUIComponents() {// place custom component creation code here
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

    private void dialogBox(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void initInstructionController() throws Exception {
        instructionController = InstructionController.getInstance();
    }

}
