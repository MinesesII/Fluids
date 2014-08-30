

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/** Sample 1 - how to get started with the most simple JME 3 application.
 * Display a blue 3D cube and view from all sides by
 * moving the mouse and pressing the WASD keys. */
public class Main extends SimpleApplication {
	private static Main clientApp;
	private Events events;
	private Block[][][]blocks ;

	public static void main(String[] args){
		clientApp = new Main();
		clientApp.start(); 
	}

	@Override
	public void simpleInitApp() {
		clientApp.getCamera().setLocation(new Vector3f(0,0,3));
		DirectionalLight sun = new DirectionalLight();
		sun.setColor(ColorRGBA.White);
		sun.setDirection(new Vector3f(-.5f,-.5f,-.5f).normalizeLocal());
		rootNode.addLight(sun);
		clientApp.getRootNode().attachChild(createTerrain());
		events = new Events();
		blocks = new Block[32][32][32];
	}
	
	public void addWaterBlock(){
		
	}

	public static Main getInstance()
	{
		return clientApp;
	}
	
	private Node createTerrain()
	{
		Node node = new Node();
		for(int i = 0; i<32; i++)
		{
			for(int j = 0 ; j<32; j++)
			{
				node.attachChild(new BlockGrass(new Vector3i(i,0,j)));
			}
		}
		return node;
	}
	
	public Block[][][] getBlocks()
	{
		return blocks;
	}
}