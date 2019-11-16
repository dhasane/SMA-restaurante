/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CO.State;

import BESA.Kernell.Agent.StateBESA;

public class COState extends  StateBESA{

	private static final long serialVersionUID = 1L;
	private boolean cocinando;

    public COState() {
    	this.cocinando = false;
    }
    
    public void inicioCocina()
    {
    	this.cocinando = true;
    }
    
    public void finalCocina()
    {
    	this.cocinando = false;
    }
    
    public Boolean getCocinando()
    {
    	return this.cocinando;
    }

}
