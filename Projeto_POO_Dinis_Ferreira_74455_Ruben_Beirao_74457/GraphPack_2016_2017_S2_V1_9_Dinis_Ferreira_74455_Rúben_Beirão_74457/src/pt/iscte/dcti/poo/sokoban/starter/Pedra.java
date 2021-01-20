package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public abstract class Pedra extends GameObject implements Active {

	public Pedra(Position position) {
		super(position);
	}

	@Override
	public abstract void move(Direction d);

	@Override
	public abstract String getName();

	@Override
	public abstract int getLevel();

	@Override
	public boolean isPassable() {
		return false;
	}
	
	

}
