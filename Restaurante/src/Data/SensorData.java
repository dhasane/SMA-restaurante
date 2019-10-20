package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import Model.WorldObject;
import java.util.ArrayList;
import java.util.List;

public class SensorData extends DataBESA{
    private List<WorldObject> comida;
    private List<WorldObject> sillas;


    public SensorData() {
        comida = new ArrayList<>();
    }

    public SensorData(List<WorldObject> comida) {
        this.comida = comida;
    }

    public List<WorldObject> getComida() {
        return comida;
    }

    public void setComida(List<WorldObject> comida) {
        this.comida = comida;
    }


}
