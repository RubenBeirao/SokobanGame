package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Position;

public abstract class GameObject implements ImageTile {
	
	private Position position;
	protected String objectname;

	public GameObject(Position position){
		this.position = position;
	}

	public String getObjectname() {
		return objectname;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	@Override
	public abstract String getName();

	@Override
	public Position getPosition(){
		return position;
	}

	@Override
	public abstract int getLevel();
	
	public abstract boolean isPassable();
	/**
	 * @return - Returns true if the object is trespassable and false if its not
	 */

}
