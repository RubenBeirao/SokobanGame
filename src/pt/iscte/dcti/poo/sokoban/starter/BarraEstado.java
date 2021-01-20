package pt.iscte.dcti.poo.sokoban.starter;

/** 
 * @author Rúben Beirão && Dinis Ferreira
 */

import javax.swing.JOptionPane;
import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class BarraEstado {

	private int nivel;
	private int movimentos;
	private int energia ;

	/**
	 * This constructs a StateBar with a specified initial energy for the player, 
	 * the number of moves initialized with 0 
	 * and the current level
	 * @param nivel the current level of the game
	 * @param movimentos the number of movements done
	 * @param energia the energy remaining
	 */
	
	public BarraEstado(int nivel, int movimentos, int energia) {
		this.nivel = nivel;
		this.movimentos = movimentos;
		this.energia = energia;
		mostraEstado();
	}

	/**
	 * This returns the current level where's the player 
	 * @return player current level
	 */
	
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * This returns the number of movements done by the player
	 * @return player's number of movements
	 */

	public int getMovimentos() {
		return movimentos;
	}

	/**
	 * This returns the amount of energy that the player can still spend
	 * @return player's amount of energy remaining
	 */
	
	public int getEnergia() {
		return energia;
	}

	/**
	 * This method shows the level, the energy and the number of moves in the game window
	 */
	
	public void mostraEstado() {
		ImageMatrixGUI.getInstance()
				.setStatusMessage("Nível:" + nivel + " Movimentos: " + movimentos + " Energia: " + energia);
	}
	
	/**
	 * This increases in 1 the number of moves
	 */

	public void incrementaMovimentos() {
		movimentos++;
	}
	
	/**
	 * This method decreases the amount of energy in 4. 
	 *  When the energy it's equal to 0 shows the message "You lost"
	 * and closes the game window
	 */

	public void decrementaEnergia() {
		if (energia > 0)
			energia = energia - 4;
		else{
			JOptionPane.showMessageDialog(null, "Perdeu :C");
			ImageMatrixGUI.getInstance().dispose();
		}
	}
	
	/**
	 * This method increases the amount of energy in 80 
	 */
	
	public void aumentaEnergia() {
		this.energia += 80;
	}

	public void aumentaNivel() {
		nivel++;
	}
	
}
