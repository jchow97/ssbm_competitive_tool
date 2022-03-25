package ui;
/*
 * PlayerDatabaseUI.java requires no other files.
 */

import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.String.format;

// Generates a panel to visualize the player database.
// Code modified based on SimpleTableDemo from Oracle.
public class PlayerDatabaseUI extends JPanel {
    private boolean debug = false;
    MeleeAppUI mainFrame;
    JTable table;


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: constructs the player table component of the UI.
    public PlayerDatabaseUI(ArrayList<Player> playerList, MeleeAppUI mainFrame) {
        super(new GridLayout(1,0));
        this.mainFrame = mainFrame;

        String[] columnNames = {"Rank", "Player", "Wins", "Losses", "Characters"};

        Object[][] data = new Object[playerList.size()][5];

        addPlayersToData(data, playerList);

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override

            // EFFECTS: Reloads graphic of main application window based on row mouse clicked.
            public void valueChanged(ListSelectionEvent e) {
                String character = table.getValueAt(table.getSelectedRow(), 4).toString();
                mainFrame.reloadGraphic(character);
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: adds the playerList data into Object[][] format.
    private void addPlayersToData(Object[][] data, ArrayList<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            Player player = playerList.get(i);
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    data[i][j] = playerList.get(i).getRank();
                } else if (j == 1) {
                    data[i][j] = playerList.get(i).getName();
                } else if (j == 2) {
                    data[i][j] = playerList.get(i).getWins();
                } else if (j == 3) {
                    data[i][j] = playerList.get(i).getLosses();
                } else {
                    // add if statement here for 1 char vs 2 chars
                    data[i][j] = playerList.get(i).getMainChars().get(0).getName();
                }
            }
        }
    }
}