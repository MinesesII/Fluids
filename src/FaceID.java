

public enum FaceID {

NORTH((byte)0),
EAST((byte)1),
SOUTH((byte)2),
WEST((byte)3),
UP((byte)4),
DOWN((byte)5);

private byte faceID;

FaceID(byte faceID){

this.faceID = faceID;
}

public byte getID(){

return faceID;
}
}