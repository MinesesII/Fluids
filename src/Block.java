
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;


public abstract class Block extends Node{
	
	private final Geometry[] faces = new Geometry[6];
	private Vector3i pos;
	//private final RigidBodyControl rigidBodyControl = new RigidBodyControl(0.0f);;
	private double timeToBreak = 1;
	private MaterialBlock materialBlock;

	public Block(Vector3i pos, byte id){
		
		this.pos = pos;
		
		materialBlock = new MaterialBlock(SimpleBlockInfo.Dirt.getPathsTextures()[0]);
		
		initFaces();
	}
	
	public void breakBlock(){
		
		this.removeFromParent();
	}
	
	public void initFaces(){
		
		attachChild(makeAFace(FaceID.NORTH, 1));
		attachChild(makeAFace(FaceID.EAST, 1));
		attachChild(makeAFace(FaceID.SOUTH, 1));
		attachChild(makeAFace(FaceID.WEST, 1));
		attachChild(makeAFace(FaceID.UP, 1));
		attachChild(makeAFace(FaceID.DOWN, 1));

		//this.addControl(rigidBodyControl);
		//GameState.getPhysicsSpace().add(rigidBodyControl);
	}
	
	public Geometry makeAFace(FaceID faceID, float size) {

		Mesh mesh = new Mesh();
		
		Vector3f [] vertices = new Vector3f[4];
        int [] indexes = { 2,0,1, 1,3,2 };
        int [] indexes_ = { 1,0,2, 2,3,1 };
        
        float[] normals = new float[12];

        if(faceID == FaceID.NORTH) {
        	
            vertices[0] = new Vector3f(pos.x,pos.y,pos.z);
            vertices[1] = new Vector3f(pos.x+size,pos.y,pos.z);
            vertices[2] = new Vector3f(pos.x,pos.y+size,pos.z);
            vertices[3] = new Vector3f(pos.x+size,pos.y+size,pos.z);

            normals = new float[]{1,0,0, 1,0,0, 1,0,0, 1,0,0};
            
            indexes = indexes_;
        }
        else if(faceID == FaceID.EAST) {
        	
            vertices[0] = new Vector3f(pos.x+size,pos.y,pos.z);
            vertices[1] = new Vector3f(pos.x+size,pos.y,pos.z+size);
            vertices[2] = new Vector3f(pos.x+size,pos.y+size,pos.z);
            vertices[3] = new Vector3f(pos.x+size,pos.y+size,pos.z+size);

            normals = new float[]{-1,0,0, -1,0,0, -1,0,0, -1,0,0};
            
            indexes = indexes_;
        }
        else if(faceID == FaceID.SOUTH) {
        	
            vertices[0] = new Vector3f(pos.x,pos.y,pos.z+size);
            vertices[1] = new Vector3f(pos.x+size,pos.y,pos.z+size);
            vertices[2] = new Vector3f(pos.x,pos.y+size,pos.z+size);
            vertices[3] = new Vector3f(pos.x+size,pos.y+size,pos.z+size);

            normals = new float[]{0,0,-1, 0,0,-1, 0,0,-1, 0,0,-1};
        }
        else if(faceID == FaceID.WEST) {
        	
        	name = "W";
            vertices[0] = new Vector3f(pos.x,pos.y,pos.z);
            vertices[1] = new Vector3f(pos.x,pos.y,pos.z+size);
            vertices[2] = new Vector3f(pos.x,pos.y+size,pos.z);
            vertices[3] = new Vector3f(pos.x,pos.y+size,pos.z+size);

            normals = new float[]{0,0,1, 0,0,1, 0,0,1, 0,0,1};
        }
        else if(faceID == FaceID.UP) {
        	
            vertices[0] = new Vector3f(pos.x,pos.y+size,pos.z);
            vertices[1] = new Vector3f(pos.x+size,pos.y+size,pos.z);
            vertices[2] = new Vector3f(pos.x,pos.y+size,pos.z+size);
            vertices[3] = new Vector3f(pos.x+size,pos.y+size,pos.z+size);

            normals = new float[]{0,1,0, 0,1,0, 0,1,0, 0,1,0};
            
            indexes = indexes_;
        } 
        else if(faceID == FaceID.DOWN) {
        	
            vertices[0] = new Vector3f(pos.x,pos.y,pos.z);
            vertices[1] = new Vector3f(pos.x+size,pos.y,pos.z);
            vertices[2] = new Vector3f(pos.x,pos.y,pos.z+size);
            vertices[3] = new Vector3f(pos.x+size,pos.y,pos.z+size);

            normals = new float[]{0,-1,0, 0,-1,0, 0,-1,0, 0,-1,0};
        }
        
        Vector2f[] texCoord = new Vector2f[4];
        texCoord[0] = new Vector2f(0,0);
        texCoord[1] = new Vector2f(1,0);
        texCoord[2] = new Vector2f(0,1);
        texCoord[3] = new Vector2f(1,1);
        
        mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        mesh.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        mesh.updateBound();
        
        faces[faceID.getID()] = new Geometry(faceID.toString(), mesh);
        

            faces[faceID.getID()].getMesh().scaleTextureCoordinates(new Vector2f(1, 1));
            faces[faceID.getID()].setMaterial(materialBlock.getTextureAt(faceID.getID()));
       

        return faces[faceID.getID()];
	}
	
	public Vector3f getLocation(){
		
		return new Vector3f(pos.x, pos.y, pos.z);
	}
	
	public void hideFace(FaceID FaceID){
			
		faces[FaceID.getID()].removeFromParent();
	}
	
	public void displayFace(FaceID FaceID){
		
		this.attachChild(faces[FaceID.getID()]);
	}
	
	public boolean breakBlock(double degats){
		
		timeToBreak -= degats;
		
		if(timeToBreak > 0){
			
			return false;
		}
		
		return true;
	}
	
	public void stopBreakBlock(){
		
		timeToBreak = 1;
	}
}