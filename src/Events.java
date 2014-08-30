

import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;

public class Events implements ActionListener
{
	public Events(){
		initializeControls();
	}

	private void initializeControls(){
		Main.getInstance().getInputManager().setCursorVisible(true);
		Main.getInstance().getInputManager().addMapping("rightclick", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
		Main.getInstance().getInputManager().addListener(this, "rightclick");

	}

	public void onAction(String name, boolean keyPressed, float tpf) {
		if (name.contentEquals("rightclick") && !keyPressed){
			
		}
	}
}