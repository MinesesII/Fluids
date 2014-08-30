

public enum SimpleBlockInfo {

	Air((byte) 0 , null, false),
	Grass((byte) 1, new String[]{"Textures/dirt2.jpg", "Textures/dirt2.jpg", "Textures/dirt2.jpg", "Textures/dirt2.jpg", "Textures/grass.jpg", "Textures/dirt.jpg"}, false),
	Dirt((byte) 2, new String[]{"test.jpg"}, true);
//	Rock((byte) 3, new String[]{"Textures/dirt.jpg"}, true);
	
	private byte id;
	private String[] pathsTextures;
	private boolean uniformTexture;
	
	SimpleBlockInfo(byte id, String[] pathsTextures, boolean uniformTexture){
		
		this.id = id;
		this.pathsTextures = pathsTextures;
		this.uniformTexture = uniformTexture;
	}
	
	public byte getID(){
		
		return id;
	}
	
	public String[] getPathsTextures(){
		
		return pathsTextures;
	}
	
	public boolean isUniformTexture(){
		
		return uniformTexture;
	}
}