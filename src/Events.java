import com.jme3.collision.CollisionResults;
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
			CollisionResults results = Main.getInstance().getPointedObject();
			if (results.size()!=0){
				Block block = (Block)results.getCollision(0).getGeometry().getParent();
				if(results.getCollision(0).getGeometry().getName().contentEquals(FaceID.UP.toString())){
					Main.getInstance().addWaterBlock(block.getLocation());
				}
			}
		}
	}
}