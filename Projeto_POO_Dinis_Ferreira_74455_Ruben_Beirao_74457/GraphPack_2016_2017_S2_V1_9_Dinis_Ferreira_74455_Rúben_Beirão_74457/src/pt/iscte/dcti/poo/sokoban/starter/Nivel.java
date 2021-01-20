package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Position;

public class Nivel {
	private int nivel;
	private ArrayList<GameObject> objetos = new ArrayList<GameObject>();
	private ArrayList<ImageTile> images = new ArrayList<ImageTile>();
	private GameObject player;

	public Nivel(int nivel) {
		this.nivel = 0;
		buildLevel("levels/level" + nivel + ".txt");
//		ImageMatrixGUI.getInstance().addImages(images);
//		ImageMatrixGUI.getInstance().addImages(objectasImage());
	}

	public int getNivel() {
		return nivel;
	}

	public void passaNivel() {
		if (nivel < 3)
			nivel++;
	}

	public ArrayList<GameObject> getObjetos() {
		return objetos;
	}

	ArrayList<ImageTile> buildFloor() {
		ArrayList<ImageTile> floorLevelTiles = new ArrayList<ImageTile>();
		for (int y = 0; y != 10; y++) {
			for (int x = 0; x != 10; x++) {
				floorLevelTiles.add(new Chao(new Position(x, y)));
			}
		}
		return floorLevelTiles;
	}

	// public void addGameElement(GameObject gameObject) {
	// tiles.add(gameObject);
	// }

	// public void sortTiles() {
	// List<ImageTile> newTiles = new ArrayList<ImageTile>();
	//
	// for(ImageTile imageTile : tiles){
	// if(imageTile instanceof Chao)
	// newTiles.add((Chao)imageTile);
	// else if(imageTile instanceof Parede)
	// newTiles.add((Parede)imageTile);
	// else if(imageTile instanceof Buraco)
	// newTiles.add((Buraco)imageTile);
	// else if(imageTile instanceof Alvo)
	// newTiles.add((Alvo)imageTile);
	// }
	//
	// for(ImageTile imageTile : tiles)//nao adicionar repetidos
	// if(!(imageTile instanceof Chao))
	// newTiles.add(imageTile);
	// tiles=newTiles;
	// }

	private void buildLevel(String file) {

		try {
			Scanner level = new Scanner(new File(file));

			int y = 0;// Vertical

			while (level.hasNextLine()) {
				String line = level.nextLine();
				GameObject aux;
				for (int x = 0; x != line.length(); x++) {
					if (line.charAt(x) == '#') { // adicionarParedes
						aux = new Parede(new Position(x, y));
						objetos.add(aux);
					} else if (line.charAt(x) == 'b') {
						aux = new Bateria(new Position(x, y));
						objetos.add(aux);
					} else if (line.charAt(x) == 'X') {
						aux = new Alvo(new Position(x, y));
						objetos.add(aux);
					} else if (line.charAt(x) == 'C') {
						aux = new Caixote(new Position(x, y));
						objetos.add(aux);
					} else if (line.charAt(x) == 'O') {
						aux = new Buraco(new Position(x, y));
						objetos.add(aux);
						// } else if (line.charAt(x) == ' ') {
						// aux = new Chao(new Position(x, y));
						// tiles.add(aux);
						// objetos.add(aux);
					} else if (line.charAt(x) == 'p') {
						aux = new PedraPequena(new Position(x, y));
						objetos.add(aux);
					} else if (line.charAt(x) == 'P') {
						aux = new PedraGrande(new Position(x, y));
						objetos.add(aux);
					} else if (line.charAt(x) == 'E') {
						// aux = new Chao(new Position(x, y));
						// tiles.add(aux);
						// objetos.add(aux);
						player = new Empilhadora(new Position(x, y));
					}
				}
				y++;
			} // CLOSED_WHILE
			objetos.add(player);
			level.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ImageTile> objectasImage() {

		ArrayList<ImageTile> lista = new ArrayList<ImageTile>();
		for (GameObject obj : objetos) {
			lista.add(obj);
		}
		return lista;
	}
}