/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TP.State;

import BESA.Kernell.Agent.StateBESA;

public class TPState extends  StateBESA{

	private static final long serialVersionUID = -1247162188878975352L;
	
	private int atendiendo;
	
    public TPState() {
    	this.atendiendo = 0;
    }
    
    public void aumentar()
    {
    	this.atendiendo ++;
    }
    
    public void reducir()
    {
    	this.atendiendo--;
    }
    
    public int getAtendiendo()
    {
    	return this.atendiendo;
    }
}
