import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class LightsOut extends JFrame {
	
	private static int GRIDSIZE = 3;
	private LightButton [] [] lightBoard = new LightButton[GRIDSIZE][GRIDSIZE];
	
	public LightsOut(){
		
	}
	
	public void initGUI(){
		
	}
	
	public void boardSetup(){
		
	}
	
	public void buttonClicked(int r, int c){
		
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
