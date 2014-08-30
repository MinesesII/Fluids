import com.jme3.export.Savable;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

public class FluidControl extends AbstractControl implements Savable, Cloneable {

	BlockFluid water;
	boolean isCheck=false;
	int tick=0;

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
		if(spatial != null && isCheck==false) 
		{
			if(tick>100)
			{
				if(water.getLevel()<=8 && Main.getInstance().getBlocks()[(int)water.getLocation().x][(int)water.getLocation().y-1][(int)water.getLocation().z]!=null)
				{
					for(int x=-1; x<=1 ; x++)
					{
						for(int z=-1; z<=1 ; z++)
						{
							if(canExtend((int)water.getLocation().x+x,(int) water.getLocation().y, (int)water.getLocation().z+z))
							{
								Main.getInstance().addWaterBlock(new Vector3f(water.getLocation().x+x,water.getLocation().y,water.getLocation().z+z),water.getLevel()+1);
							}
						}
					}
				}
				if(canExtend((int)water.getLocation().x,(int)water.getLocation().y-1,(int)water.getLocation().z))
				{
					Main.getInstance().addWaterBlock(new Vector3f(water.getLocation().x,water.getLocation().y-1,water.getLocation().z),water.getLevel()+1);
				}
				isCheck=true;
				tick=0;
			}
			else
			{
				tick++;
			}
		}
	}

	private boolean canExtend(int x, int y, int z)
	{
		if(Main.getInstance().getBlocks()[x][y][z]==null)
		{
			return true;
		}
		return false;
	}

	@Override
	protected void controlRender(RenderManager arg0, ViewPort arg1) {	
	}
}