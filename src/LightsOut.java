import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class LightsOut extends JFrame {
	
	private static int GRIDSIZE = 3;
	private LightButton [] [] lightBoard = new LightButton[GRIDSIZE][GRIDSIZE];
	
	public LightsOut(){

		initGUI();
		
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
		
	}
	
	public void buttonClicked(int r, int c){
		lightBoard[r][c].toggle();
	}
	
	public void promptForNewGame(){
		
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
