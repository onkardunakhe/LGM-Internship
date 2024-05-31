import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class TicTacToeGame implements ActionListener {
    JFrame frame = new JFrame();
    JPanel textPanel = new JPanel();
    JPanel btnPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JButton[] btn = new JButton[9];
    int iChanceFlag = 0;
    boolean bPlayerOneChance;
    Random random = new Random();

    // Constructor sets up the grid and calls the 'startGame()' method
    TicTacToeGame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setTitle("Tic-Tac-Toe Game");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textLabel.setBackground(new Color(245, 245, 245));
        textLabel.setForeground(new Color(0,0,0));
        textLabel.setFont(new Font("Arial", Font.BOLD, 75));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe Game");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.setBounds(0, 0, 800, 100);

        btnPanel.setLayout(new GridLayout(3, 3));
        btnPanel.setBackground(new Color(245, 245, 245));

        for (int i = 0; i < 9; i++) {
            btn[i] = new JButton();
            btnPanel.add(btn[i]);
            btn[i].setFont(new Font("Arial", Font.BOLD, 120));
            btn[i].setFocusable(false);
            btn[i].addActionListener(this);
            btn[i].setBackground(Color.gray);
        }

        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(btnPanel);

        startGame();
    }

    // Creating method to start the game and decide the chance
    public void startGame() {
        try {
            textLabel.setText("Wait ...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int chance = random.nextInt(100);

        if (chance % 2 == 0) {
            bPlayerOneChance = true;
            textLabel.setText("X's turn");
        } else {
            bPlayerOneChance = false;
            textLabel.setText("O's turn");
        }
    }

    public void gameOver(String s) {
        iChanceFlag = 0;
        Object[] option={"Restart", "Exit"};
        int n=JOptionPane.showOptionDialog(frame, "Game Over\n"+s,"Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        if(n == 0) {
            frame.dispose();
            new TicTacToeGame();
        } else {
            frame.dispose();
        }
    }

    // Checking win conditions - HARDCODED FOR NOW - WILL BE CHANGED
    public void matchCheck() {
        if ((btn[0].getText() == "X") && (btn[1].getText() == "X") && (btn[2].getText() == "X")) {
            xWins(0, 1, 2);
        } else if ((btn[0].getText() == "X") && (btn[4].getText() == "X") && (btn[8].getText() == "X")) {
            xWins(0, 4, 8);
        } else if ((btn[0].getText() == "X") && (btn[3].getText() == "X") && (btn[6].getText() == "X")) {
            xWins(0, 3, 6);
        } else if ((btn[1].getText() == "X") && (btn[4].getText() == "X") && (btn[7].getText() == "X")) {
            xWins(1, 4, 7);
        } else if ((btn[2].getText() == "X") && (btn[4].getText() == "X") && (btn[6].getText() == "X")) {
            xWins(2, 4, 6);
        } else if ((btn[2].getText() == "X") && (btn[5].getText() == "X") && (btn[8].getText() == "X")) {
            xWins(2, 5, 8);
        } else if ((btn[3].getText() == "X") && (btn[4].getText() == "X") && (btn[5].getText() == "X")) {
            xWins(3, 4, 5);
        } else if ((btn[6].getText() == "X") && (btn[7].getText() == "X") && (btn[8].getText() == "X")) {
            xWins(6, 7, 8);
        } else if ((btn[0].getText() == "O") && (btn[1].getText() == "O") && (btn[2].getText() == "O")) {
            oWins(0, 1, 2);
        } else if ((btn[0].getText() == "O") && (btn[3].getText() == "O") && (btn[6].getText() == "O")) {
            oWins(0, 3, 6);
        } else if ((btn[0].getText() == "O") && (btn[4].getText() == "O") && (btn[8].getText() == "O")) {
            oWins(0, 4, 8);
        } else if ((btn[1].getText() == "O") && (btn[4].getText() == "O") && (btn[7].getText() == "O")) {
            oWins(1, 4, 7);
        } else if ((btn[2].getText() == "O") && (btn[4].getText() == "O") && (btn[6].getText() == "O")) {
            oWins(2, 4, 6);
        } else if ((btn[2].getText() == "O") && (btn[5].getText() == "O") && (btn[8].getText() == "O")) {
            oWins(2, 5, 8);
        } else if ((btn[3].getText() == "O") && (btn[4].getText() == "O") && (btn[5].getText() == "O")) {
            oWins(3, 4, 5);
        } else if ((btn[6].getText() == "O") && (btn[7].getText() == "O") && (btn[8].getText() == "O")) {
            oWins(6, 7, 8);
        } else if(iChanceFlag == 9) {
            textLabel.setText("Game Drawn");
            gameOver("Game Drawn");
        }
    }

    // If player X wins
    public void xWins(int x1, int x2, int x3) {
        btn[x1].setBackground(Color.darkGray);
        btn[x2].setBackground(Color.darkGray);
        btn[x3].setBackground(Color.darkGray);

        for (int i = 0; i < 9; i++) {
            btn[i].setEnabled(false);
        }
        textLabel.setText("X wins");
        gameOver("X Wins");
    }

    // If player O wins
    public void oWins(int x1, int x2, int x3) {
        btn[x1].setBackground(Color.darkGray);
        btn[x2].setBackground(Color.darkGray);
        btn[x3].setBackground(Color.darkGray);

        for (int i = 0; i < 9; i++) {
            btn[i].setEnabled(false);
        }
        textLabel.setText("O Wins");
        gameOver("O Wins");
    }

    // Actions performed at each player's turn
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == btn[i]) {
                if (bPlayerOneChance) {
                    if (btn[i].getText() == "") {
                        btn[i].setForeground(new Color(255, 0, 0));
                        btn[i].setText("X");
                        bPlayerOneChance = false;
                        textLabel.setText("O turn");
                        iChanceFlag++;
                        matchCheck();
                    }
                } else {
                    if (btn[i].getText() == "") {
                        btn[i].setForeground(new Color(0, 0, 255));
                        btn[i].setText("O");
                        bPlayerOneChance = true;
                        textLabel.setText("X turn");
                        iChanceFlag++;
                        matchCheck();
                    }
                }
            }
        }
    }
}

// Driver code
public class TicTacToeMain {
    public static void main(String[] args) throws Exception {
        new TicTacToeGame();
    }
}
