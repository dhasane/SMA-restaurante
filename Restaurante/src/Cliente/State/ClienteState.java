

package Cliente.State;

import BESA.Kernell.Agent.StateBESA;

public class ClienteState extends  StateBESA{

    private static final long serialVersionUID = 1L;

    private boolean tieneHambre;
    private boolean seated;
    private boolean yaPidio;

    private int x;
    private int y;

    public ClienteState(int x, int y) {
        this.tieneHambre = true;
        this.seated = false;
        this.yaPidio = false;
        this.x = x;
        this.y = y;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
