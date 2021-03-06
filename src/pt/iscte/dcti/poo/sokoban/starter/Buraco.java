package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Buraco extends GameObject {

	public Buraco(Position position) {
		super(position);
		objectname = "Buraco";
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
