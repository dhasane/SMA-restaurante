/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CA.State;

import java.util.LinkedList;
import java.util.Queue;

import BESA.Kernell.Agent.StateBESA;

public class CAState extends StateBESA {

	private static final long serialVersionUID = 3698051779850902560L;
	private Queue<String> fila;

	public CAState() {
		this.fila = new LinkedList<>();
	}

	public int getAtendiendo() {
		return this.fila.size();
	}

	public void addFila(String id) {
		this.fila.add(id);
	}

	public String getSiguiente() {
		return this.fila.remove();
	}

}
