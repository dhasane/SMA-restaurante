
package CL.State;

import BESA.Kernell.Agent.StateBESA;

public class CLState extends StateBESA {

	private static final long serialVersionUID = 1L;

	private int preguntas; 			// cantidad de preguntas que el agente ha realizado
	private String filaAHacer;		// la mejor fila
	private int longitudFilaAHacer;	// longitud de la fila

	public CLState() {
		this.preguntas = 0;
		this.filaAHacer = "";
		this.longitudFilaAHacer = 0;
	}

	public void setPreguntas(int cantidad) {
		this.preguntas = cantidad;
	}

	public int getPreguntas() {
		return this.preguntas;
	}

	public void setFila(String dueño, int longitud) {
		if (this.filaAHacer.equals("") || longitud < this.longitudFilaAHacer) {
			this.filaAHacer = dueño;
			this.longitudFilaAHacer = longitud;
		}
	}
	
	public String getFila( )
	{
		return this.filaAHacer;
	}

	public void reducirPregunta() {
		this.preguntas--;
	}

}
