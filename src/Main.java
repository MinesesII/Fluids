
 
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
		getFlyByCamera().setEnabled(true);
		this.getCamera().setLocation(new Vector3f(0,0,3));
    	for(int i = 0 ; i<32; i++)
    	{
    		for(int j = 0 ; j<32; j++)
        	{
    	    	rootNode.attachChild(new BlockDirt(new Vector3i(i,0,j)));
        	}
    	}
    }
    
    public static Main getInstance()
    {
    	return clientApp;
    }
}