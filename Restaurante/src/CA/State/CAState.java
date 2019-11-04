/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CA.State;

import java.util.LinkedList;
import java.util.Queue;

import BESA.Kernell.Agent.StateBESA;

public class CAState extends  StateBESA{
	
	private Queue<String> fila;

	public CAState() {
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
