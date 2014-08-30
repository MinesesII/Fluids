
import com.jme3.material.Material;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;


public class MaterialBlock {
	
	private Material[] listTextures;

	public MaterialBlock(String path){ // the same texture is applied on all the faces
		
		listTextures = new Material[6];
		
		for(int i = 0; i < listTextures.length; i++){	        
			
			Texture texture = Main.getInstance().getAssetManager().loadTexture(path);
			texture.setWrap(WrapMode.Repeat);
			listTextures[i] = new Material(Main.getInstance().getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
			listTextures[i].setTexture("DiffuseMap", texture);
		}
	}
	
	public MaterialBlock(String[] paths){ //RESPECT this order for the textures : NORTH, EAST, SOUTH, WEST, UP, DOWN
		
		listTextures = new Material[6];
		
		for(int i = 0; i < listTextures.length; i++){	        
			
			Texture texture = Main.getInstance().getAssetManager().loadTexture(paths[i]);
			texture.setWrap(WrapMode.Repeat);
			listTextures[i] = new Material(Main.getInstance().getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
			listTextures[i].setTexture("DiffuseMap", texture);
		}
	}
	
	public Material getTextureAt(byte faceID){
		
		return listTextures[faceID];
	}
}