

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.audio.Environment;
import com.jme3.collision.CollisionResults;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh.Type;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
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
	private Block[][][]blocks;
	private String testType="fluids";

	public static void main(String[] args){
		clientApp = new Main();
		clientApp.start(); 
	}

	@Override
	public void simpleInitApp() {
		if(testType.contentEquals("fluids"))
		{
			clientApp.getFlyByCamera().setEnabled(false);
			getCamera().setLocation(new Vector3f(32,12,40));
			AmbientLight al = new AmbientLight();
			al.setColor(ColorRGBA.White.mult(3f));
			rootNode.addLight(al);
			blocks = new Block[64][64][64];
			getRootNode().attachChild(createTerrain());
			new Events();
		}
		if(testType.contentEquals("audio"))
		{
			AudioNode music = new AudioNode(assetManager, "Audio/music1.ogg", true);
			music.play();
		}
		if(testType.contentEquals("particle"))
		{
			ParticleEmitter fire = 
					new ParticleEmitter("Emitter", Type.Triangle, 30);
			Material mat_red = new Material(assetManager, 
					"Common/MatDefs/Misc/Particle.j3md");
			mat_red.setTexture("Texture", assetManager.loadTexture(
					"Effects/Explosion/flame.png"));
			fire.setMaterial(mat_red);
			fire.setImagesX(2); 
			fire.setImagesY(2); // 2x2 texture animation
			fire.setEndColor(  new ColorRGBA(1f, 0f, 0f, 1f));   // red
			fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
			fire.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 2, 0));
			fire.setStartSize(1.5f);
			fire.setEndSize(0.1f);
			fire.setGravity(0, 0, 0);
			fire.setLowLife(1f);
			fire.setHighLife(3f);
			fire.getParticleInfluencer().setVelocityVariation(0.3f);
			rootNode.attachChild(fire);

			ParticleEmitter debris = 
					new ParticleEmitter("Debris", Type.Triangle, 10);
			Material debris_mat = new Material(assetManager, 
					"Common/MatDefs/Misc/Particle.j3md");
			debris_mat.setTexture("Texture", assetManager.loadTexture(
					"Effects/Explosion/Debris.png"));
			debris.setMaterial(debris_mat);
			debris.setImagesX(3); 
			debris.setImagesY(3); // 3x3 texture animation
			debris.setRotateSpeed(4);
			debris.setSelectRandomImage(true);
			debris.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 4, 0));
			debris.setStartColor(ColorRGBA.White);
			debris.setGravity(0, 6, 0);
			debris.getParticleInfluencer().setVelocityVariation(.60f);
			rootNode.attachChild(debris);
			debris.emitAllParticles();
		}
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