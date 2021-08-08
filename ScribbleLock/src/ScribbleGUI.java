import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ScribbleGUI {
    JFrame frame = new JFrame("ScribbleLock");
    CardLayout cl = new CardLayout();
    private JPanel mainPanel = new JPanel();
    private JPanel entryPanel;
    private JPanel dataPanel;
    private JPanel startUpPanel;
    private JLabel entryLabel;
    private JPasswordField masterPassEntry;
    private JButton entryButton;
    private JTabbedPane tabbedPanel;
    private JPanel userpassTab;
    private JPanel QATab;
    private JScrollPane UPScroll;
    private JScrollPane QAScroll;
    private JTable UPTable;
    private JTable QATable;
    private JLabel startLabel;
    private JPasswordField firstPassEntry;
    private JPasswordField repeatEntry;
    private JButton startUpButton;

    private final char[] sample;
    {
        sample = new char[]{'m', 'i', 't', 'h', 'r', 'a', 'n', 'd', 'i', 'r'};
    }

    public ScribbleGUI(){
        mainPanel.setLayout(cl);

        //initialize startup panel
        startUpPanel = new JPanel();
        startLabel = new JLabel("Enter your master password twice");
        firstPassEntry = new JPasswordField();
        repeatEntry = new JPasswordField();
        startUpButton = new JButton("Enter");
        startUpPanel.add(startLabel);
        startUpPanel.add(firstPassEntry);
        startUpPanel.add(repeatEntry);
        startUpPanel.add(startUpButton);

        //initialize returning user panel
        entryPanel = new JPanel();
        entryLabel = new JLabel("Enter your master password");
        masterPassEntry = new JPasswordField();
        entryButton = new JButton("Enter");
        entryPanel.add(entryLabel);
        entryPanel.add(masterPassEntry);
        entryPanel.add(entryButton);

        //initialize tabbed data tables
        dataPanel = new JPanel();
        tabbedPanel = new JTabbedPane();
        userpassTab = new JPanel();
        QATab = new JPanel();
        UPScroll = new JScrollPane();
        UPTable = new JTable();
        QAScroll = new JScrollPane();
        QATable = new JTable();
        UPScroll.add(UPTable);
        QAScroll.add(QATable);
        userpassTab.add(UPScroll);
        QATab.add(QAScroll);
        tabbedPanel.add("Passwords", userpassTab);
        tabbedPanel.add("Answers", QATab);
        dataPanel.add(tabbedPanel);

        //cardlayout conditions and visibility
        mainPanel.add(startUpPanel, "1");
        mainPanel.add(entryPanel, "2");
        mainPanel.add(dataPanel, "3");

        //divide showing based on whether an account is established or not
        if(Arrays.equals(null,sample)){
            //startup menu
            cl.show(mainPanel, "1");
        }else{
            //established account menu
            cl.show(mainPanel, "2");
        }
        /*
        TODO
        Shape panels correctly and utilize piece placement
        Add logo and otherwise shape up and beautify panels
        */

        frame.add(mainPanel);
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //entryPanel action listener for enter button
        entryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] mastPassAttempt = masterPassEntry.getPassword();
                if(Arrays.equals(mastPassAttempt,sample)){
                    cl.show(mainPanel, "3");
                } else {
                    entryLabel.setText("Wrong password!");
                }
            }
        });
        startUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] firstAttempt = firstPassEntry.getPassword();
                char[] secondAttempt = repeatEntry.getPassword();
                if(Arrays.equals(firstAttempt, secondAttempt)){
                    cl.show(mainPanel, "3");
                    //time to store password and hash encrypt it
                }else{
                    startLabel.setText("The passwords do not match!");
                }
            }
        });
    }

    public static void main(String args[]){
        new ScribbleGUI();
    }
}
