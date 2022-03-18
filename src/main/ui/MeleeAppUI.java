package ui;

import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


// Code is based off GridBagLayoutDemo code from oracle documentation website.
public class MeleeAppUI extends JPanel {
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 1200;
    private static final String JSON_STORE = "./data/player.json";
    private ArrayList<Player> playerList;
    // private Menu m;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    //    private JDesktopPane desktop;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    private static final String searchString = "Search";
    private JTextField playerName;

    public MeleeAppUI() {

        playerList = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //Create and set up the window.
        JFrame frame = new JFrame("Melee Player Database App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }

    public void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        button = new JButton("Add");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Load");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Save");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(button, c);


        PlayerDatabaseUI list = new PlayerDatabaseUI();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 200;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(list, c);

        JButton hireButton = new JButton(searchString);
//        PlayerListUI.HireListener hireListener = new PlayerListUI.HireListener(hireButton);
        hireButton.setActionCommand(searchString);
//        hireButton.addActionListener(hireListener);
        hireButton.setEnabled(false);

        playerName = new JTextField(10);
//        employeeName.addActionListener(hireListener);
//        employeeName.getDocument().addDocumentListener(hireListener);
//        String name = listModel.getElementAt(
//                list.getSelectedIndex()).toString();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
//        buttonPane.add(fireButton);
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
//        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(playerName);
        buttonPane.add(hireButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

//        add(listScrollPane, BorderLayout.CENTER);

//        pane.add(buttonPane, c);

//        button = new JButton("Search");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 3;   //2 columns wide
        c.gridy = 2;       //third row
//        pane.add(button, c);
        pane.add(buttonPane, c);
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MeleeAppUI();
            }
        });
    }
}