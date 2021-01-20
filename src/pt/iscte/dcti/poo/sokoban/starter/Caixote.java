package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public class Caixote extends GameObject implements Active {

	public Caixote(Position position) {
		super(position);
		objectname = "Caixote";
	}

	@Override
	public String getName() {
		return objectname;
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public void move(Direction d) {

		boolean canMove = true;

		Position newPosition = getPosition().plus(d.asVector());
		ArrayList<GameObject> objetos = SokobanGame.getInstance().getObjetos();

		if (newPosition.getX() >= 0 && newPosition.getX() < 10 && newPosition.getY() >= 0 && newPosition.getY() < 10) {
			for (GameObject g : objetos) {
				if (newPosition.equals(g.getPosition()) && !g.isPassable()) {
					canMove = false;
				}
			}
			if (canMove)
				setPosition(newPosition);
		}
		// ImageMatrixGUI.getInstance().update();
	}
	
	@Override
	public boolean isPassable() {
		return false;
	}

}
