package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Chao extends GameObject {
	
	public Chao(Position position){
		super(position);
		objectname = "Chao";
	}
	
	@Override
	public String getName() {
		return "Chao";
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
