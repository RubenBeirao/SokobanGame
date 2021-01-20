package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Alvo extends GameObject {

	public Alvo(Position position) {
		super(position);
		objectname = "Alvo";
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
		return true;
	}

}
