package ui;

import model.EventLog;
import model.Player;
import model.Tournament;
import model.exception.GameCharacterException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.String.format;

// Java Swing UI for Competitive Super Smash Bros. Melee Application
// Code is based off GridBagLayoutDemo code from oracle documentation website.
public class MeleeAppUI extends JPanel {

    private static final String JSON_STORE = "./data/player.json";
    private Tournament tournament;
    private JFrame frame;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    static final boolean shouldFill = true;
    static final boolean RIGHT_TO_LEFT = false;
    private static final String[] topButtonNames = {"Add", "Load", "Save"};

    private static final String searchString = "Search";
    private JTextField playerName;
    private String graphicCharacter;
    private GridBagConstraints constraints;

    // EFFECTS: constructs the UI of the Melee application.
    public MeleeAppUI() {
        tournament = new Tournament();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        //Create and set up the window.
        frame = new JFrame("Melee Player Database App");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowListener());

        constraints = new GridBagConstraints();
        this.graphicCharacter = "Mario";

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
        if (shouldFill) {
            //natural height, maximum width
            constraints.fill = GridBagConstraints.HORIZONTAL;
        }

        addTopRowButtons(pane);

        makePlayerDatabaseUI(pane, tournament.getCompetitors());

        addSearchBar(pane);

        displayGraphic(pane);


    }

    // EFFECTS: Displays image of character of selected row, otherwise displays Mario on default.
    private void displayGraphic(Container pane) {
        BufferedImage retrievedImage = null;
        try {
            retrievedImage = ImageIO.read(new FileInputStream(format("./data/CharacterImages/%s_SSBM.jpg",
                    graphicCharacter)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel image = new JLabel(new ImageIcon(retrievedImage));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        constraints.ipady = 0;      //make this component tall
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.weightx = 0.0;
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 1;
        pane.add(image, constraints);

    }

    // MODIFIES: this
    // EFFECTS: updates graphicCharacter field and refreshes window.
    public void reloadGraphic(String graphicCharacter) {
        this.graphicCharacter = graphicCharacter;
        refreshWindow(tournament.getCompetitors());
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates and adds the search bar component of the UI.
    private void addSearchBar(Container pane) {
        //Creates a new Button and TextField
        JButton searchButton = new JButton(searchString);
        searchButton.setActionCommand(searchString);
        playerName = new JTextField(10);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(playerName);
        buttonPane.add(searchButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 0;       //reset to default
        constraints.ipadx = 600;
        constraints.weighty = 1.0;   //request any extra vertical space
        constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
        constraints.gridx = 0;       //aligned with button 2
        constraints.gridwidth = 3;   //3 columns wide
        constraints.gridy = 2;       //third row

        //Attaches listener to button.
        SearchListener searchListener = new SearchListener(searchButton, playerName);
        searchButton.addActionListener(searchListener);

        //Adds button to the pane.
        pane.add(buttonPane, constraints);
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates and adds the 'Add', 'Load', and 'Save' buttons to the UI.
    private void addTopRowButtons(Container pane) {
        JButton button;
        int numButtons = topButtonNames.length;
        for (int i = 0; i < numButtons; i++) {
            button = new JButton(topButtonNames[i]);
            constraints.weightx = 0.5;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = i;
            constraints.gridy = 0;
            constraints.ipadx = 150;
            attachListener(i, button, pane);
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Attaches action listener to button based on button name.
    private void attachListener(int i, JButton button, Container pane) {
        if (i == 0) {
            AddListener addListener = new AddListener(button);
            button.setActionCommand(topButtonNames[i]);
            button.addActionListener(addListener);
            pane.add(button, constraints);
        } else if (i == 1) {
            LoadListener loadListener = new LoadListener(button);
            button.setActionCommand(topButtonNames[i]);
            button.addActionListener(loadListener);
            pane.add(button, constraints);
        } else if (i == 2) {
            SaveListener saveListener = new SaveListener(button);
            button.setActionCommand(topButtonNames[i]);
            button.addActionListener(saveListener);
            pane.add(button, constraints);
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: create and add the player list to the UI
    private void makePlayerDatabaseUI(Container pane, ArrayList<Player> playerList) {
        PlayerDatabaseUI list = new PlayerDatabaseUI(playerList, this);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        constraints.ipady = 200;      //make this component tall
        constraints.weightx = 0.0;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(list, constraints);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: saves playerList data onto JSON file.
    public void saveMeleeApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(tournament.getCompetitors());
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Code based on JsonSerializationDemo
    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: loads playerList data from JSON file.
    public void loadMeleeApp() {
        try {
            try {
                tournament.setCompetitors(jsonReader.read());
                refreshWindow(tournament.getCompetitors());
            } catch (GameCharacterException e) {
                System.out.println("Unable to load player database: Contains invalid Game Character.");
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Refreshes the main application window with updated player information.
    public void refreshWindow(ArrayList<Player> playerList) {
        tournament.setCompetitors(playerList);
        Container pane = frame.getContentPane();
        pane.removeAll();
        addComponentsToPane(pane);
        pane.revalidate();
        pane.repaint();
    }

    // EFFECTS: Initiates the Add Player Window.
    public void addPlayerUI() {
        AddPlayerUI addPlayerUI = new AddPlayerUI(tournament.getCompetitors(), this);
    }


    // Listener class for Search Button
    class SearchListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;
        private JTextField textField;

        // EFFECTS: Initializes Search Button ActionListener
        public SearchListener(JButton button, JTextField textField) {
            this.button = button;
            this.textField = textField;
        }

        // EFFECTS: filters playerList to only have player names of search substring.
        public void actionPerformed(ActionEvent e) {
            // make changes to playerList
            String keyword = textField.getText();
            ArrayList<Player> playerList = new ArrayList<>();
            for (Player player : tournament.getCompetitors()) {
                if (player.getName().contains(keyword)) {
                    playerList.add(player);
                }
            }
            try {
                tournament.getCompetitors().get(0).logSearchEvent(keyword);
            } catch (IndexOutOfBoundsException exception) {
                // do nothing.
            }
            refreshWindow(playerList);
        }
    }

    // Listener class for Add Button
    class AddListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: Initializes Add Button ActionListener
        public AddListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: Initializes Add Player UI window.
        public void actionPerformed(ActionEvent e) {
            addPlayerUI();
        }
    }


    // Listener class for Save Button.
    class SaveListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: Initializes Save Button ActionListener
        public SaveListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: Saves current player data onto JSON format.
        public void actionPerformed(ActionEvent e) {
            saveMeleeApp();
        }
    }

    // Listener class for Load Button.
    class LoadListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: Initializes Load Button ActionListener
        public LoadListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: Loads current player data from JSON file.
        public void actionPerformed(ActionEvent e) {
            loadMeleeApp();
        }
    }

    class WindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            EventLog events = EventLog.getInstance();
            events.printLog();
            System.exit(0);
        }
    }
}