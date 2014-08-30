

public class BlockFluid extends Block{
	int level;

	public BlockFluid(Vector3i pos, int Level) {
		
		super(pos, SimpleBlockInfo.Water.getID());
		level = Level;
	}
	
	public int getLevel()
	{
		return level;
	}
}