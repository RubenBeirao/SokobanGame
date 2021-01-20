package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Parede extends GameObject {

	public Parede(Position position) {
		super(position);
		objectname = "Parede";
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
