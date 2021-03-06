import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class LightsOut extends JFrame {
	
	private static int GRIDSIZE = 3;
	private LightButton [] [] lightBoard = new LightButton[GRIDSIZE][GRIDSIZE];
	
	public LightsOut(){

		initGUI();
		
		boardSetup();
		
		setTitle("Lights Out");
		setSize(200, 100); //pixels
		setResizable(true);
		pack();
		setLocationRelativeTo(null); //centers on screen, do this after packing but before visible

			
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initGUI(){
		JLabel titleLabel = new JLabel("Lights Out");
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setForeground(Color.BLACK);
		Font titleFont = new Font ("Arial", Font.BOLD, 32);
		titleLabel.setFont(titleFont);
		add(titleLabel);
		titleLabel.setHorizontalAlignment(JLabel.CENTER); //left or right
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(gamePanel, BorderLayout.CENTER);
		for (int r = 0; r < GRIDSIZE; r++){
			for (int c = 0; c < GRIDSIZE; c++){
				lightBoard[r][c] = new LightButton(r, c);
				lightBoard[r][c].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LightButton button = (LightButton) e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						buttonClicked(row, col);
					}
				});
				gamePanel.add(lightBoard[r][c]);
			}
		}
		add(gamePanel, BorderLayout.CENTER);
		
	}
	
	public void boardSetup(){
		//reset all lights
		for (int r = 0; r < GRIDSIZE; r++){
			for (int c = 0; c < GRIDSIZE; c++){
				lightBoard[r][c].reset();
			}
		}
		//turn on outside lights
		for (int r = 0; r < GRIDSIZE; r++){
			for (int c = 0; c < GRIDSIZE; c++){
				if ( r == 0 || c == 0 || r == GRIDSIZE - 1 || c == GRIDSIZE -1
						&& !lightBoard[r][c].getIsOn())
				lightBoard[r][c].toggle();
			}
		}
		

		Random rand = new Random();
		//generate a random number of "steps" between 10 and 20
		int steps = rand.nextInt(10) + 10;
		for (int i = 0; i < steps; i++){
			//generate a random row & col to toggle
			int pickRow = rand.nextInt(GRIDSIZE);
			int pickCol = rand.nextInt(GRIDSIZE);
			lightBoard[pickRow][pickCol].toggle();
		}
	}
	
	public void buttonClicked(int r, int c){
		//toggle button
		lightBoard[r][c].toggle();
		
		//check neighbors & toggle
		if (valid(r - 1, c)){
			//System.out.println("" + (r - 1) + c + lightBoard[r-1][c].getIsOn());
			lightBoard[r-1][c].toggle();
			//System.out.println("" + (r - 1) + c + lightBoard[r-1][c].getIsOn());
		}
		if (valid(r + 1, c)){
			lightBoard[r+1][c].toggle();
		}
		if (valid(r, c - 1)){
			lightBoard[r][c - 1].toggle();
		}
		if (valid(r, c + 1)){
			lightBoard[r][c + 1].toggle();
		}
		

		if (checkForWin()){
			String message = "Congratulations!  You did it!";
			promptForNewGame(message);
		}
		
	}
	
	public boolean checkForWin(){
		//checks individually
//		if (lightBoard[0][0].getIsOn()
//				&& lightBoard[0][1].getIsOn()
//				&& lightBoard[0][2].getIsOn()
//				&& lightBoard[1][0].getIsOn()
//				&& lightBoard[1][2].getIsOn()
//				&& lightBoard[2][0].getIsOn()
//				&& lightBoard[2][1].getIsOn()
//				&& lightBoard[2][2].getIsOn()
//				&& !lightBoard[1][1].getIsOn()){
//			return true;
//		} else {
//			return false;
//		}
		
		boolean won = true;
		for (int r = 0; r < GRIDSIZE; r++){
			for (int c = 0; c < GRIDSIZE; c ++){
				if (r == 0 || c == 0
						|| r == GRIDSIZE - 1 || c == GRIDSIZE -1
						&& (r != GRIDSIZE/2  && c != GRIDSIZE/2)){
					if (!lightBoard[r][c].getIsOn()){
						won = false;
					}
				}
			}
		}
		
		if (!lightBoard[GRIDSIZE/2][GRIDSIZE/2].getIsOn()){
			won = false;
		}
		
		return won;
		
	}
	
	public boolean valid(int r, int c){
		if (r < GRIDSIZE && r >= 0
				&& c < GRIDSIZE && c >= 0){
			return true;
		} else {
			return false;
		}
	}
	
	public void promptForNewGame(String message){
		int option = JOptionPane.showConfirmDialog(this, message, "Play Again?", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			boardSetup();
		} else {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch ( Exception e) {}
        
        EventQueue.invokeLater(new Runnable (){
            @Override
            public void run() {
                new LightsOut();
            }   
        });


	}

}
