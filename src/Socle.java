

import com.jme3.scene.Node;

public class Socle extends Node
{
	public Socle()
	{
		Block model;
		int test = 0;
		for(int i = 0; i<32; i++)
		{
			for(int j = 0 ; j<32; j++)
			{
				test++;
				model = new BlockGrass(new Vector3i(i,0,j));
				attachChild(model);
			}
		}
	}
}
