

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;

/** Sample 1 - how to get started with the most simple JME 3 application.
 * Display a blue 3D cube and view from all sides by
 * moving the mouse and pressing the WASD keys. */
public class Main extends SimpleApplication {
	private static Main clientApp;

	public static void main(String[] args){
		Main app = new Main();
		app.start(); 
	}

	@Override
	public void simpleInitApp() {
		clientApp=this;
		clientApp.getCamera().setLocation(new Vector3f(0,0,3));
		rootNode.attachChild(new Socle());
	}

	public static Main getInstance()
	{
		return clientApp;
	}
}