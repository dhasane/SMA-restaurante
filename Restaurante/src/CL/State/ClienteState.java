
package CL.State;

import BESA.Kernell.Agent.StateBESA;

public class ClienteState extends  StateBESA{

    private static final long serialVersionUID = 1L;

    private boolean tieneHambre;
    private boolean seated;
    private boolean yaPidio;


    public ClienteState() {
        this.tieneHambre = true;
        this.seated = false;
        this.yaPidio = false;
    }

    public boolean hambre()
    {
        return this.tieneHambre;
    }

    public void eat()
    {
        this.tieneHambre = false;
    }

    public void pedir()
    {
    	this.yaPidio = true;
    }

    public boolean pedidoHecho() {
		return this.yaPidio;
	}

    public boolean getSeated() {
    	return this.seated;
    }

    public void sit() {
		this.seated = true;
	}

    public void getUp() {
		this.seated = false;
	}


}
