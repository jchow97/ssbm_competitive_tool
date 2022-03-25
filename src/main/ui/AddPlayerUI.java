package ui;

import model.GameCharacter;
import model.Player;
import model.exception.GameCharacterException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

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

        //Create and populate the panel.
        JPanel p = new JPanel(new SpringLayout());
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);
            JTextField textField = new JTextField(10);
            l.setLabelFor(textField);
            p.add(textField);
        }

        JFrame frame = new JFrame("Add New Player");
        JLabel l = new JLabel("", JLabel.TRAILING);
        p.add(l);
        //Create add button
        JButton button = new JButton("Add");
        FinalAddListener addListener = new FinalAddListener(button, frame);
        button.setActionCommand("Add");
        button.addActionListener(addListener);
        l.setLabelFor(button);
        p.add(button);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(p,
                    numPairs + 1, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad


        //Create and set up the window.
//        JFrame frame = new JFrame("Add New Player");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        p.setOpaque(true);  //content panes must be opaque
        frame.setContentPane(p);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new AddPlayerUI();
//            }
//        });
//    }

    class FinalAddListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;
        private JFrame frame;

        public FinalAddListener(JButton button, JFrame frame) {
            this.button = button;
            this.frame = frame;
        }

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


            Player newPlayer = new Player(newPlayerDetails.get(0), Integer.valueOf(newPlayerDetails.get(1)),
                    Integer.valueOf(newPlayerDetails.get(2)), newPlayerCharList, lowestRank, 0);

            playerList.add(newPlayer);
            mainFrame.refreshWindow(playerList);
            frame.dispose();
        }
    }
}

