package Creator;

import java.util.List;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import TP.TPAgent;
import TP.Behavior.TPSensorGuard;
import TP.State.TPState;
import javafx.util.Pair;

public class TPCreator {
	
	private static double clave;
	
	public static void setClave( double clave )
	{
		TPCreator.clave = clave ;
	}

	// crea varios agentes
    public static void crearTP( int x , int y , List< Pair<Integer, Integer> > positions ) throws ExceptionBESA
    {
        for ( int a = 0 ; a < positions.size() ; ++a )
            agente( x, y, "TP"+Integer.toString( a ), positions.get(a).getKey() , positions.get(a).getValue() );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    private static void agente( int sizex, int sizey, String name, int x, int y) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("TPPlayerPerception");
        c1Struct.bindGuard  ("TPPlayerPerception", TPSensorGuard.class);
        ( new TPAgent( name, new TPState(sizex, sizey), c1Struct, clave, x, y ) ).start();
    }
}
