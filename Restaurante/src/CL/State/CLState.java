
package CL.State;

import BESA.Kernell.Agent.StateBESA;
import Utils.Utils;

public class CLState extends StateBESA {

	private static final long serialVersionUID = 1L;

	private int preguntas; // cantidad de preguntas que el agente ha realizado
	private String filaAHacer; // la mejor fila
	private int longitudFilaAHacer; // longitud de la fila
	private long tiempoEntrada;
	
	private long inicioTiempoEspera;

	public CLState() {
		tiempoEntrada = Utils.conseguirTiempo();
		this.sinPreguntas();
	}

	public void setPreguntas(int cantidad) {
		this.preguntas = cantidad;
	}

	public int getPreguntas() {
		return this.preguntas;
	}

	public void sinPreguntas() {
		this.preguntas = 0;
		this.filaAHacer = "";
		this.longitudFilaAHacer = 0;
	}

	public void setFila(String dueno, int longitud) {
		if (this.filaAHacer.equals("") || longitud < this.longitudFilaAHacer) {
			this.filaAHacer = dueno;
			this.longitudFilaAHacer = longitud;
		}
	}

	public String getFila() {
		return this.filaAHacer;
	}

	public void reducirPregunta() {
		this.preguntas--;
	}

	public long getTiempoEntrada()
	{
		return this.tiempoEntrada;
	}

	public void inicioTiempoEspera() {
		this.inicioTiempoEspera = Utils.conseguirTiempo();
	}
	
	public long conseguirTiempoEspera()
	{
		return  Utils.conseguirTiempo() - this.inicioTiempoEspera;
	}

}
