package ui;

import model.Player;
import model.exception.GameCharacterException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


// Code is based off GridBagLayoutDemo code from oracle documentation website.
public class MeleeAppUI extends JPanel {
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 1200;
    private static final String JSON_STORE = "./data/player.json";
    private ArrayList<Player> playerList;
    private JFrame frame;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    static final boolean shouldFill = true;
    static final boolean shouldWeightX = true;
    static final boolean RIGHT_TO_LEFT = false;

    private static final String searchString = "Search";
    private JTextField playerName;

    // EFFECTS: constructs the UI of the Melee application.
    public MeleeAppUI() {

        playerList = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //Create and set up the window.
        frame = new JFrame("Melee Player Database App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates a GridBagLayout and adds different components to the Layout.
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates and adds the search bar component of the UI.
    private void addSearchBar(Container pane, GridBagConstraints c) {
        JButton searchButton = new JButton(searchString);
        searchButton.setActionCommand(searchString);
//        searchButton.setEnabled(false);
        playerName = new JTextField(10);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(playerName);
        buttonPane.add(searchButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 3;   //2 columns wide
        c.gridy = 2;       //third row

        SearchListener searchListener = new SearchListener(searchButton, playerName);
        searchButton.addActionListener(searchListener);

        pane.add(buttonPane, c);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates and adds the 'Add', 'Load', and 'Save' buttons to the UI.
    // TODO implement helper functions to simplify this method
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: create and add the player list to the UI
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: saves playerList data onto JSON file.
    public void saveMeleeApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(playerList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Code based on JsonSerializationDemo
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: loads playerList data from JSON file.
    public void loadMeleeApp() {
        try {
            try {
                this.playerList = jsonReader.read();
                Container pane = frame.getContentPane();
                pane.removeAll();
                addComponentsToPane(pane);
                pane.revalidate();
                pane.repaint();
            } catch (GameCharacterException e) {
                System.out.println("Unable to load player database: Contains invalid Game Character.");
            }
            System.out.println("Loaded player database from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void refreshWindow(ArrayList<Player> playerList) {
        this.playerList = playerList;
        Container pane = frame.getContentPane();
        pane.removeAll();
        addComponentsToPane(pane);
        pane.revalidate();
        pane.repaint();
    }

    public void addPlayerUI() {
        AddPlayerUI addPlayerUI = new AddPlayerUI(playerList, this);
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Shows Add Player form when 'Add' button is pressed.
    class SearchListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;
        private JTextField textField;

        public SearchListener(JButton button, JTextField textField) {
            this.button = button;
            this.textField = textField;
        }

        public void actionPerformed(ActionEvent e) {
            // make changes to playerList
            String keyword = textField.getText();
            ArrayList<Player> newList = new ArrayList<>();
            for (Player player : playerList) {
                if (player.getName().contains(keyword)) {
                    newList.add(player);
                }
            }
            playerList = newList;
            refreshWindow(playerList);
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Shows Add Player form when 'Add' button is pressed.
    class AddListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            addPlayerUI();
        }
    }



    // REQUIRES:
    // MODIFIES:
    // EFFECTS: saves player data into JSON format when 'Save' button is pressed.
    class SaveListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public SaveListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            saveMeleeApp();
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Loads player data from JSON when 'Load' button is pressed.
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