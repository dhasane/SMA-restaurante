package TP.State;

import java.util.LinkedList;
import java.util.Queue;
import BESA.Kernell.Agent.StateBESA;

public class TPState extends StateBESA {

	private static final long serialVersionUID = -1247162188878975352L;
	
	private Queue<String> fila;

	public TPState() {
		this.fila = new LinkedList<>();
	}

	public int getAtendiendo() {
		return this.fila.size();
	}
	
	public void addFila( String id )
	{
		this.fila.add(id);
	}
	
	public String getSiguiente()
	{
		return this.fila.remove();
	}
	
	public void print( String id)
	{
		System.out.print("contenido " + id +" : ");
		for (String var : fila) 
		{ 
		    System.out.print(var + " | ");
		}
		System.out.println();
	}
}
