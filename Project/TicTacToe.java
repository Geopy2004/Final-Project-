import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{
	
	Random random = new Random();
	JFrame frame = new JFrame("Tic-Tac-Toe");
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean  player1_turn;
	JButton pauseButton, resetButton;
	boolean isPaused = false;
	
	TicTacToe(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480, 800);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25,255, 0));
		textfield.setFont(new Font ("Courier", Font.ITALIC, 50));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0,800,100);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(125, 125, 125));
		
		for (int i=0; i<9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font ("MV Boli", Font.ITALIC, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		 
		pauseButton = new JButton("Pause");
	    pauseButton.setFocusable(false);
	    pauseButton.addActionListener(this);

	    resetButton = new JButton("Reset");
	    resetButton.setFocusable(false);
	    resetButton.addActionListener(this);

	    JPanel buttonControlPanel = new JPanel();
	    buttonControlPanel.setLayout(new FlowLayout());
	    buttonControlPanel.add(pauseButton);
	    buttonControlPanel.add(resetButton);

	    frame.add(buttonControlPanel, BorderLayout.SOUTH);

		firstturn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pauseButton) {
            togglePause();
        } else if (e.getSource() == resetButton) {
            reset();
        } else {
        	for (int i =0; i<9; i++) {
    			if(e.getSource()==buttons[i]) {
    				if(player1_turn) {
    					if(buttons[i].getText()=="") {
    						buttons[i].setForeground(new Color(255,0,0));
    						buttons[i].setText("X");
    						player1_turn=false;
    						textfield.setText("O turn");
    						check();
    					}
    				}
    				else{
    					if(buttons[i].getText()=="") {
    						buttons[i].setForeground(new Color(0,0,255));
    						buttons[i].setText("O");
    						player1_turn=true;
    						textfield.setText("X turn");
    						check();
        }
	}

				}
			}
		}
	}

	 private void togglePause() {
	        isPaused = !isPaused;

	        if (isPaused) {
	            // Add logic for pausing the game
	            // For example, disable buttons or show a pause message
	            textfield.setText("Game is Paused");
	        } else {
	            // Resume the game
	            textfield.setText(player1_turn ? "X turn" : "O turn");
	        }
	    }		
	
	public void reset() {
	    for (int i = 0; i < 9; i++) {
	        buttons[i].setText("");
	        buttons[i].setBackground(UIManager.getColor("Button.background")); 
	        buttons[i].setEnabled(true);
	        
	    }

	    player1_turn = (random.nextInt(2) == 0);
	    if (player1_turn) {
	        textfield.setText("X turn");
	    } else {
	        textfield.setText("O turn");
	    }
	}

	

	public void firstturn() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(random.nextInt(2)==0) {
			player1_turn=true;
			textfield.setText("X turn");
		}
		else {
			player1_turn=false;
			textfield.setText("0 turn");
		}
	}
	public void check() {
		
		if (
			    (buttons[0].getText().equals("X")) &&
			    (buttons[1].getText().equals("X")) &&
			    (buttons[2].getText().equals("X"))
			) {	
			    Xwins(0,1,2);
			}
		if (
			    (buttons[3].getText().equals("X")) &&
			    (buttons[4].getText().equals("X")) &&
			    (buttons[5].getText().equals("X"))
			) {	
			    Xwins(3,4,5);
		}
		if (
			    (buttons[6].getText().equals("X")) &&
			    (buttons[7].getText().equals("X")) &&
			    (buttons[8].getText().equals("X"))
			) {	
			    Xwins(6,7,8);
		}	
		if (
			    (buttons[0].getText().equals("X")) &&
			    (buttons[3].getText().equals("X")) &&
			    (buttons[6].getText().equals("X"))
			) {	
			    Xwins(0,3,6);
		}
		if (
			    (buttons[1].getText().equals("X")) &&
			    (buttons[4].getText().equals("X")) &&
			    (buttons[7].getText().equals("X"))
			) {	
			    Xwins(1,4,7);
		}
		if (
			    (buttons[2].getText().equals("X")) &&
			    (buttons[5].getText().equals("X")) &&
			    (buttons[8].getText().equals("X"))
			) {	
			    Xwins(2,5,8);
		}
		if (
			    (buttons[0].getText().equals("X")) &&
			    (buttons[4].getText().equals("X")) &&
			    (buttons[8].getText().equals("X"))
			) {	
			    Xwins(0,4,8);
		}
		if (
			    (buttons[2].getText().equals("X")) &&
			    (buttons[4].getText().equals("X")) &&
			    (buttons[6].getText().equals("X"))
			) {	
			    Xwins(2,4,6);
		}
		if (
			    (buttons[0].getText().equals("O")) &&
			    (buttons[1].getText().equals("O")) &&
			    (buttons[2].getText().equals("O"))
			) {	
			    Owins(0,1,2);
			}
		if (
			    (buttons[3].getText().equals("O")) &&
			    (buttons[4].getText().equals("O")) &&
			    (buttons[5].getText().equals("O"))
			) {	
			    Owins(3,4,5);
		}
		if (
			    (buttons[6].getText().equals("O")) &&
			    (buttons[7].getText().equals("O")) &&
			    (buttons[8].getText().equals("O"))
			) {	
			    Owins(6,7,8);
		}	
		if (
			    (buttons[0].getText().equals("O")) &&
			    (buttons[3].getText().equals("O")) &&
			    (buttons[6].getText().equals("O"))
			) {	
			    Owins(0,3,6);
		}
		if (
			    (buttons[1].getText().equals("O")) &&
			    (buttons[4].getText().equals("O")) &&
			    (buttons[7].getText().equals("O"))
			) {	
			    Owins(1,4,7);
		}
		if (
			    (buttons[2].getText().equals("O")) &&
			    (buttons[5].getText().equals("O")) &&
			    (buttons[8].getText().equals("O"))
			) {	
			    Owins(2,5,8);
		}
		if (
			    (buttons[0].getText().equals("O")) &&
			    (buttons[4].getText().equals("O")) &&
			    (buttons[8].getText().equals("O"))
			) {	
			    Owins(0,4,8);
		}
		if (
			    (buttons[2].getText().equals("O")) &&
			    (buttons[4].getText().equals("O")) &&
			    (buttons[6].getText().equals("O"))
			) {	
			    Owins(2,4,6);
		}
	}
			
		
		

	public void setWin(int a, int b, int c, Color color, String message) {
	    buttons[a].setBackground(color);
	    buttons[b].setBackground(color);
	    buttons[c].setBackground(color);

	    for (int i = 0; i < 9; i++) {
	        buttons[i].setEnabled(false);
	    }

	    textfield.setText(message);
	}

	public void Xwins(int a, int b, int c) {
	    setWin(a, b, c, Color.black, "X wins");
	}

	public void Owins(int a, int b, int c) {
	    setWin(a, b, c, Color.red, "O wins");
	}


	public JFrame getFrame() {
		return null;
	}
}
