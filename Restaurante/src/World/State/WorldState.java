package World.State;

import BESA.Kernell.Agent.StateBESA;
import Model.Map;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class WorldState extends StateBESA{

	private static final long serialVersionUID = 1L;
	
	private Map map;
    private List<String> botsAlias;

    public WorldState(int sizex, int sizey, int cocinaInicio, List< Pair<Integer, Integer> > posSillas ) {
        map = new Map(sizex , sizey, cocinaInicio, posSillas, 50);
        botsAlias = new ArrayList<>();
        Map.openInJFrame(map, 50*(sizex+1), 50*(sizey+1) );
    }

    public Map getMap() {
        return map;
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
    
    public void sit(String alias, int x, int y) {
        map.sit(alias,x,y);
    }
}
