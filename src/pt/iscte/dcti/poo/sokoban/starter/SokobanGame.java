package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public class SokobanGame implements Observer {

	private Empilhadora player;
	private ArrayList<ImageTile> tiles = new ArrayList<ImageTile>();
	private ArrayList<GameObject> objetos = new ArrayList<GameObject>();
	private static SokobanGame INSTANCE = null;
	private BarraEstado barra = new BarraEstado(0, 0, 540);
	private int level = 0;
	private int num_caixotes;

	public SokobanGame() {
		tiles = buildFloor();
		buildLevel(nextlevel());
		ImageMatrixGUI.getInstance().addImages(tiles);
	}

	public static SokobanGame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SokobanGame();
		}
		return INSTANCE;
	}

	public String nextlevel() {
		String nl = null;
		if (level == 2) {
			nl = "levels/level2.txt";
			level++;
		}
		if (level == 1) {
			nl = "levels/level1.txt";
			level++;
		}
		if (level == 0) {
			nl = "levels/level0.txt";
			level++;
		}
		return nl;

	}

	private void jogoGanho() {
		int i = 0;
		for (GameObject obj1 : objetos) {
			if (obj1 instanceof Caixote) {
				for (GameObject obj2 : objetos) {
					if (obj2 instanceof Alvo) {
						if (obj1.getPosition().equals(obj2.getPosition())) {
							i++;
						}
					}
				}
			}
		}
		if (level != 3) {
			if (i == num_caixotes) {
				i = 0;
				ImageMatrixGUI.getInstance().removeImages(tiles);
				tiles.clear();
				objetos.clear();
				tiles = buildFloor();
				buildLevel(nextlevel());
				ImageMatrixGUI.getInstance().addImages(tiles);
				barra.aumentaNivel();
			}
		}

		else if (i == num_caixotes) {
			ImageMatrixGUI.getInstance().update();
			JOptionPane.showMessageDialog(null, "Ganhou!! :D");
			ImageMatrixGUI.getInstance().dispose();
		}
	}

	public BarraEstado getBarra() {
		return barra;
	}

	private ArrayList<ImageTile> buildFloor() {
		ArrayList<ImageTile> floorLevelTiles = new ArrayList<ImageTile>();
		for (int y = 0; y != 10; y++) {
			for (int x = 0; x != 10; x++) {
				floorLevelTiles.add(new Chao(new Position(x, y)));
			}
		}
		return floorLevelTiles;
	}

	private void buildLevel(String file) {
		num_caixotes = 0;
		try {
			Scanner level = new Scanner(new File(file));

			int y = 0;// Vertical

			while (level.hasNextLine()) {
				String line = level.nextLine();
				GameObject aux;
				for (int x = 0; x != line.length(); x++) {
					if (line.charAt(x) == '#') { // adicionarParedes
						aux = new Parede(new Position(x, y));
						tiles.add(aux);
						objetos.add(aux);
					} else if (line.charAt(x) == 'b') {
						aux = new Bateria(new Position(x, y));
						tiles.add(aux);
						objetos.add(aux);
					} else if (line.charAt(x) == 'X') {
						aux = new Alvo(new Position(x, y));
						tiles.add(aux);
						objetos.add(aux);
					} else if (line.charAt(x) == 'C') {
						aux = new Caixote(new Position(x, y));
						tiles.add(aux);
						objetos.add(aux);
						num_caixotes++;
					} else if (line.charAt(x) == 'O') {
						aux = new Buraco(new Position(x, y));
						tiles.add(aux);
						objetos.add(aux);
					} else if (line.charAt(x) == 'p') {
						aux = new PedraPequena(new Position(x, y));
						tiles.add(aux);
						objetos.add(aux);
					} else if (line.charAt(x) == 'P') {
						aux = new PedraGrande(new Position(x, y));
						tiles.add(aux);
						objetos.add(aux);
					} else if (line.charAt(x) == 'E') {
						player = new Empilhadora(new Position(x, y));
					}
				}
				y++;
			} // CLOSED_WHILE
			objetos.add(player);
			tiles.add(player);
			level.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public GameObject getBateria() {
		GameObject bat = null;
		for (GameObject obj : objetos) {
			if (obj.getName().equals("Bateria"))
				bat = (Bateria) obj;
		}
		return bat;
	}

	public GameObject getBuraco() {
		GameObject hole = null;
		for (GameObject obj : objetos) {
			if (obj.getName().equals("Buraco"))
				hole = (Buraco) obj;
		}
		return hole;
	}

	public ArrayList<GameObject> getObjetos() {
		return objetos;
	}

	public ArrayList<ImageTile> getTiles() {
		return tiles;
	}

	private void removeCaixaePedra() {
		GameObject objeto = null;

		for (GameObject obj1 : objetos) {
			if (obj1 instanceof Buraco) {
				for (GameObject obj2 : objetos) {
					if (obj2 instanceof Caixote) {
						if (obj1.getPosition().equals(obj2.getPosition())) {
							ImageMatrixGUI.getInstance().removeImage(obj2);
							objeto = (Caixote) obj2;
						}
					} else if (obj2 instanceof PedraPequena) {
						if (obj1.getPosition().equals(obj2.getPosition())) {
							ImageMatrixGUI.getInstance().removeImage(obj2);
							objeto = (PedraPequena) obj2;
						}
					}

					else if (obj2 instanceof PedraGrande) {
						if (obj1.getPosition().equals(obj2.getPosition())) {
							ImageMatrixGUI.getInstance().removeImage(obj1);
							objeto = (Buraco) obj1;
						}
					}
				}
			}
		}
		objetos.remove(objeto);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int lastKeyPressed = (Integer) arg1;
		System.out.println("Key pressed " + lastKeyPressed);
		Direction d = null;
		// VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT
		if (lastKeyPressed == KeyEvent.VK_UP) {
			d = Direction.UP;
			if (player != null)
				player.move(d);
			barra.decrementaEnergia();
			barra.incrementaMovimentos();
		}

		else if (lastKeyPressed == KeyEvent.VK_DOWN) {
			d = Direction.DOWN;
			if (player != null)
				player.move(d);
			barra.decrementaEnergia();
			barra.incrementaMovimentos();

		}

		else if (lastKeyPressed == KeyEvent.VK_LEFT) {
			d = Direction.LEFT;
			if (player != null)
				player.move(d);
			barra.decrementaEnergia();
			barra.incrementaMovimentos();

		}

		else if (lastKeyPressed == KeyEvent.VK_RIGHT) {
			d = Direction.RIGHT;
			if (player != null)
				player.move(d);
			barra.decrementaEnergia();
			barra.incrementaMovimentos();

		}
		removeCaixaePedra();
		barra.mostraEstado();
		jogoGanho();
		ImageMatrixGUI.getInstance().update();
	}

	private List<ImageTile> objectasImage() {

		List<ImageTile> lista = new ArrayList<ImageTile>();
		for (GameObject obj : objetos) {
			lista.add(obj);
		}
		return lista;
	}

}