package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public class Empilhadora extends GameObject implements Active {

	public Empilhadora(Position initialPosition) {
		super(initialPosition);
		objectname = "Empilhadora_U";
	}

	@Override
	public String getName() {
		return objectname;
	}

	@Override
	public int getLevel() {
		return 2;
	}

	@Override
	public void move(Direction d) {

		boolean canMove = true;

		if (d.equals(Direction.UP)) {
			setObjectname("Empilhadora_U");
		}

		else if (d.equals(Direction.DOWN)) {
			setObjectname("Empilhadora_D");
		}

		else if (d.equals(Direction.LEFT)) {
			setObjectname("Empilhadora_L");
		}

		else if (d.equals(Direction.RIGHT)) {
			setObjectname("Empilhadora_R");
		}

		Position newPosition = getPosition().plus(d.asVector());
		ArrayList<GameObject> objetos = SokobanGame.getInstance().getObjetos();
		GameObject bat = null;
		if (newPosition.getX() >= 0 && newPosition.getX() < 10 && newPosition.getY() >= 0 && newPosition.getY() < 10) {
			for (GameObject g : objetos) {

				if (newPosition.equals(g.getPosition()) && !g.isPassable()) {
					if (g.getName().equals("Caixote")) {
						((Caixote) g).move(d);
					} else if (g.getName().equals("BigStone")) {
						((PedraGrande) g).move(d);
					} else if (g.getName().equals("SmallStone")) {
						((PedraPequena) g).move(d);
					} else if (g.getName().equals("Bateria")) {
						BarraEstado barra = SokobanGame.getInstance().getBarra();
						barra.aumentaEnergia();
						bat = g;
						setPosition(newPosition);
						ImageMatrixGUI.getInstance().removeImage(bat);
					}

					else
						canMove = false;
				}

				if (newPosition.equals(g.getPosition()) && !g.isPassable()) {
					canMove = false;
				}

				if (newPosition.equals(g.getPosition()) && g.isPassable()) {
					if (g.getName().equals("Buraco")) {
						setPosition(newPosition);
						ImageMatrixGUI.getInstance().update();
						JOptionPane.showMessageDialog(null, "Perdeu :C");
						ImageMatrixGUI.getInstance().dispose();
					}
				}

			}
			objetos.remove(bat);
			if (canMove)
				setPosition(newPosition);
		}
	}

	@Override
	public boolean isPassable() {
		return false;
	}
}