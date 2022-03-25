package ui;
/*
 * PlayerDatabaseUI.java requires no other files.
 */

import model.Player;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// Generates a panel to visualize the player database.
// Code modified based on SimpleTableDemo from Oracle.
public class PlayerDatabaseUI extends JPanel {
    private boolean debug = false;

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: constructs the player table component of the UI.
    public PlayerDatabaseUI(ArrayList<Player> playerList) {
        super(new GridLayout(1,0));

        String[] columnNames = {"Rank", "Player", "Wins", "Losses", "Characters"};

        Object[][] data = new Object[playerList.size()][5];

        addPlayersToData(data, playerList);

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (debug) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

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

    // EFFECTS: prints debug data onto command line.
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}