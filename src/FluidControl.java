import com.jme3.export.Savable;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

public class FluidControl extends AbstractControl implements Savable, Cloneable {

	BlockFluid water;
	boolean isCheck=false;

	public FluidControl(){} 

	public void setSpatial(Spatial spatial) {
		super.setSpatial(spatial);
		/* Example:
	    if (spatial != null){
	        // initialize
	    }else{
	        // cleanup
	    }
		 */
		water=(BlockFluid)this.getSpatial();
	}

	@Override
	protected void controlUpdate(float tpf){
		if(spatial != null && isCheck==false) {
			if(water.getLevel()!=8)
			{
				for(int x=-1; x<1 ; x++)
				{
					for(int z=-1; z<1 ; z++)
					{
						if(canExtend(x,z))
						{
							Main.getInstance().addWaterBlock(new Vector3f(water.getLocation().x+x,water.getLocation().y,water.getLocation().z+z),water.getLevel()+1);
						}
					}
				}
			}
		}
		isCheck=true;
	}

	private boolean canExtend(int x, int z)
	{
		if(Main.getInstance().getBlocks()[(int) (water.getLocation().x+x)][(int) water.getLocation().y][(int) (water.getLocation().z+z)]==null)
		{
			return true;
		}
		return false;
	}

	@Override
	protected void controlRender(RenderManager arg0, ViewPort arg1) {	
	}
}