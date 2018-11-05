package dk.dtu.CDIT_Grp_43_matador.matador.entity;

public class Tile {

    private int tileValue;
    private boolean extraTurn = false;
    private String tileName;
    private String tileMessage;
    

    public Tile(String tileName, String tileInfoString){
    	String[] nameInfo = tileName.split(",");
        this.tileName = nameInfo[0];
        this.tileMessage = nameInfo[1];
        
        String[] tileInfo = tileInfoString.split(";");
        for (String string : tileInfo) {
        	String[] split = string.split(":");
        	switch (split[0]) {
			case "Score":
				tileValue = Integer.valueOf(split[1]);
				break;
			case "ExtraTurn":
				extraTurn = true;
				break;
			default:
				break;
			}
        }
        
    }

    // Getters

    public int getTileValue() {
        return tileValue;
    }

    public String getTileName() {
        return tileName;
    }
    
    public String getTileMessage() {
		return tileMessage;
	}
    
    public boolean givesExtraTurn() {
    	return extraTurn;
    }
}
