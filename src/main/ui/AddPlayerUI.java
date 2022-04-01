package ui;

import model.GameCharacter;
import model.Player;
import model.exception.GameCharacterException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

// Creates UI window for Adding a new Player.
// Code modified based on SpringForm.java examples from Oracle.
public class AddPlayerUI extends JPanel {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    private ArrayList<Player> playerList;
    private MeleeAppUI mainFrame;

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: constructs the Add Player form component of the UI.
    public AddPlayerUI(ArrayList<Player> playerList, MeleeAppUI mainFrame) {
        String[] labels = {"Player Name: ", "Wins: ", "Losses: ", "Character 1: ", "Character 2: "};
        int numPairs = labels.length;
        this.playerList = playerList;
        this.mainFrame = mainFrame;

        JPanel p = new JPanel(new SpringLayout());

        createPanels(p, numPairs, labels);

        JFrame frame = new JFrame("Add New Player");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JLabel l = new JLabel("", JLabel.TRAILING);
        p.add(l);

        //Create add button
        JButton button = new JButton("Add");
        FinalAddListener addListener = new FinalAddListener(frame);
        button.setActionCommand("Add");
        button.addActionListener(addListener);
        l.setLabelFor(button);
        p.add(button);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(p,numPairs + 1, 2, 6, 6, 6, 6);

        //Set up the content pane.
        p.setOpaque(true);  //content panes must be opaque
        frame.setContentPane(p);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // REQUIRES:
    // MODFIIES:
    // EFFECTS: Create and populate the panel.
    private void createPanels(JPanel p, int numPairs, String[] labels) {
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);
            JTextField textField = new JTextField(10);
            l.setLabelFor(textField);
            p.add(textField);
        }
    }

    // EFFECTS: returns playerList
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    // ActionListener for the Add button in the Add Player window.
    class FinalAddListener implements ActionListener {
        private JFrame frame;

        // EFFECTS: Initializes the button listener.
        public FinalAddListener(JFrame frame) {
            this.frame = frame;
        }

        // EFFECTS: Adds the new player based on text field information to playerList and updates UI.
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> newPlayerDetails = new ArrayList<>();

            for (Component component : frame.getContentPane().getComponents()) {
                if (component instanceof JTextField) {
                    newPlayerDetails.add(((JTextField) component).getText());
                }
            }

            int lowestRank = playerList.size() + 1;
            ArrayList<GameCharacter> newPlayerCharList = new ArrayList<>();
            try {
                newPlayerCharList.add(new GameCharacter(newPlayerDetails.get(3)));
                newPlayerCharList.add(new GameCharacter(newPlayerDetails.get(4)));
            } catch (GameCharacterException j) {
                System.out.println("Invalid character, please try again.");
            }

            Player newPlayer = new Player(newPlayerDetails.get(0), Integer.parseInt(newPlayerDetails.get(1)),
                    Integer.parseInt(newPlayerDetails.get(2)), newPlayerCharList, lowestRank, 0);

            playerList.add(newPlayer);
            mainFrame.refreshWindow(playerList);
            frame.dispose();
        }
    }
}

