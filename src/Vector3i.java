

import java.io.Serializable;

import com.jme3.math.Vector3f;

public class Vector3i implements Serializable{
	
	public int x;
	public int y;
	public int z;

	public Vector3i(int x, int y, int z){
		
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Vector3i fromFloat(Vector3f pos) {
		// TODO Auto-generated method stub
		return new Vector3i((int)pos.x, (int)pos.y, (int)pos.z);
	}
}