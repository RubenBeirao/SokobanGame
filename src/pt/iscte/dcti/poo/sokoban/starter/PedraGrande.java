package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public class PedraGrande extends Pedra{

	private boolean isBuraco = false;
	
	public PedraGrande(Position position) {
		super(position);
		objectname = "BigStone";
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
				
				else if (newPosition.equals(g.getPosition()) && g.getName().equals("Buraco")) {
//					GameObject buraco = SokobanGame.getInstance().getBuraco();
//					setPosition(newPosition);
//					ImageMatrixGUI.getInstance().removeImage(buraco);
					setPosition(newPosition);
					canMove = false;
					isBuraco = true;
					ImageMatrixGUI.getInstance().update();
				}
			}
			if (canMove && isBuraco == false)
				setPosition(newPosition);
		}
		
	}

}
