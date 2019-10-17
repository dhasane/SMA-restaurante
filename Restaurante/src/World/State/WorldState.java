package World.State;

import BESA.Kernell.Agent.StateBESA;
import Model.Map;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class WorldState extends StateBESA{

    private Map map;
    private List<String> botsAlias;

    public WorldState(int sizex, int sizey, int numOfDust) {
        map = new Map(sizex , sizey, numOfDust, 50);
        botsAlias = new ArrayList<>();
        Map.openInJFrame(map, 50*(sizex+1), 50*(sizey+1) );
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<String> getBotsAlias() {
        return botsAlias;
    }

    public void setBotsAlias(List<String> botsAlias) {
        this.botsAlias = botsAlias;
    }

    public void clean(String alias) {
        map.clean(alias);
    }

    public void move(String alias, int x, int y) {
        map.move(alias,x,y);
    }
}
