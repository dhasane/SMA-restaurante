package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import Model.WorldObject;
import java.util.ArrayList;
import java.util.List;

public class SensorData extends DataBESA{

	private static final long serialVersionUID = 1L;
	
	private List<WorldObject> comida;
    private List<WorldObject> sillas;


    public SensorData() {
        comida = new ArrayList<>();
        sillas = new ArrayList<>();
    }

    public SensorData(List<WorldObject> comida, List<WorldObject> sillas ) {
        this.comida = comida;
        this.sillas = sillas;
    }

    public List<WorldObject> getComida() {
        return comida;
    }
    
//    public void addComida( )
//    {
//    	WorldObject wo = new WorldObject(xpos, ypos, )
//    }
    
    public List<WorldObject> getSillas() {
        return sillas;
    }


}
