

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/** Sample 1 - how to get started with the most simple JME 3 application.
 * Display a blue 3D cube and view from all sides by
 * moving the mouse and pressing the WASD keys. */
public class Main extends SimpleApplication {
	private static Main clientApp;
	private Events events;
	private Block[][][]blocks;

	public static void main(String[] args){
		clientApp = new Main();
		clientApp.start(); 
	}

	@Override
	public void simpleInitApp() {
		clientApp.getFlyByCamera().setEnabled(false);
		getCamera().setLocation(new Vector3f(32,12,40));
		AmbientLight al = new AmbientLight();
		al.setColor(ColorRGBA.White.mult(3f));
		rootNode.addLight(al);
		blocks = new Block[64][64][64];
		getRootNode().attachChild(createTerrain());
		events = new Events();
	}

	public void addWaterBlock(Vector3f pos, int level){
		BlockFluid fluid = new BlockFluid(new Vector3i((int)pos.x,(int)pos.y,(int)pos.z),level);
		fluid.addControl(new FluidControl());
		rootNode.attachChild(fluid);
		blocks[(int) pos.x][(int) (pos.y)][(int) pos.z]=fluid;
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
				BlockGrass grass=new BlockGrass(new Vector3i(i,10,j));
				blocks[i][10][j]=grass;
				node.attachChild(grass);
			}
		}
		return node;
	}

	public Block[][][] getBlocks()
	{
		return blocks;
	}

	public CollisionResults getPointedObject(){

		CollisionResults results = new CollisionResults();
		Vector2f click2d = getInputManager().getCursorPosition();
		Vector3f click3d = getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
		Vector3f dir = getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
		Ray ray = new Ray(click3d, dir);
		getRootNode().collideWith(ray, results);
		return results;
	}
}