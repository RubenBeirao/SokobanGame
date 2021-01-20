package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Bateria extends GameObject {

	public Bateria(Position position) {
		super(position);
		objectname = "Bateria";
	}

	@Override
	public String getName() {
		return objectname;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

}
