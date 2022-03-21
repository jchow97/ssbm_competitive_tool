package ui;

import model.Player;
import model.exception.GameCharacterException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    static final boolean shouldFill = true;
    static final boolean shouldWeightX = true;
    static final boolean RIGHT_TO_LEFT = false;

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

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }


        addTopRowButtons(pane, c);

        makePlayerDatabaseUI(pane, c, playerList);

        addSearchBar(pane, c);
    }

    private void addSearchBar(Container pane, GridBagConstraints c) {
        JButton searchButton = new JButton(searchString);
        searchButton.setActionCommand(searchString);
        searchButton.setEnabled(false);
        playerName = new JTextField(10);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(playerName);
        buttonPane.add(searchButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 3;   //2 columns wide
        c.gridy = 2;       //third row
        pane.add(buttonPane, c);
    }

    private void addTopRowButtons(Container pane, GridBagConstraints c) {
        JButton button;
        String[] topButtonNames = {"Add", "Load", "Save"};
        int numButtons = topButtonNames.length;
        for (int i = 0; i < numButtons; i++) {
            button = new JButton(topButtonNames[i]);
            c.weightx = 0.5;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = i;
            c.gridy = 0;
            if (i == 0) {
                AddListener addListener = new AddListener(button);
                button.setActionCommand(topButtonNames[i]);
                button.addActionListener(addListener);
                pane.add(button, c);
            } else if (i == 1) {
                LoadListener loadListener = new LoadListener(button);
                button.setActionCommand(topButtonNames[i]);
                button.addActionListener(loadListener);
                pane.add(button, c);
            } else if (i == 2) {
                SaveListener saveListener = new SaveListener(button);
                button.setActionCommand(topButtonNames[i]);
                button.addActionListener(saveListener);
                pane.add(button, c);
            }
        }
    }

    private void makePlayerDatabaseUI(Container pane, GridBagConstraints c, ArrayList<Player> playerList) {
        PlayerDatabaseUI list = new PlayerDatabaseUI(playerList);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 200;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(list, c);
    }

    // Code based on JsonSerializationDemo
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: loads playerList data from JSON file.
    public void loadMeleeApp() {
        try {
            try {
                playerList = jsonReader.read();
                revalidate();
            } catch (GameCharacterException e) {
                System.out.println("Unable to load player database: Contains invalid Game Character.");
            }
            System.out.println("Loaded player database from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    class AddListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {

            new AddPlayerUI();
        }
    }

    class SaveListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public SaveListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            //TODO
        }
    }

    class LoadListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public LoadListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            loadMeleeApp();
        }
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