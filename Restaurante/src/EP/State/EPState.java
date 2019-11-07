/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EP.State;

import BESA.Kernell.Agent.StateBESA;

public class EPState extends StateBESA {

	private static final long serialVersionUID = 1L;

	private boolean enPreparacion;

	public EPState() {
		this.enPreparacion = false;
	}

	public void comenzandoPedido() {
		this.enPreparacion = true;
	}

	public boolean preparando() {
		return this.enPreparacion;
	}

}
