import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class LightButton extends JButton {
	public static Dimension SIZE = new Dimension (50, 50);
	private boolean isOn = false;
	
	public LightButton(){
		isOn = false;
		setBackground(Color.BLACK);
	}
	
	public boolean getIsOn(){
		return isOn;
	}
	
	public void reset(){
		isOn = false;
		setBackground(Color.BLACK);
	}
	
	public void toggle(){
		if (isOn){
			isOn = false;
			setBackground(Color.BLACK);
		} else {
			isOn = true;
			setBackground(Color.RED);
		}
	}
}