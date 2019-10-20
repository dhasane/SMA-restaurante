import java.util.ArrayList;
import java.util.List;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.AdmBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import Cocinero.CocineroAgent;
import Cocinero.Behavior.CocineroSensorGuard;
import Cocinero.State.CocineroState;
import Creator.ClientCreator;
import Creator.TPCreator;
import World.WorldAgent;
import World.Behavior.GameGuard;
import World.Behavior.SubscribeGuard;
import World.Behavior.UpdateGuard;
import World.State.WorldState;
import javafx.util.Pair;

public class Restaurante {

    public static int GAME_PERIODIC_TIME = 1000;
    public static int GAME_PERIODIC_DELAY_TIME = 100;

    private static double clave = 0.91;


    public static void main(String[] args) throws ExceptionBESA {

        AdmBESA admLocal = AdmBESA.getInstance();
        ClientCreator.setClave(clave);
        TPCreator.setClave(clave);
        
        List <Pair<Integer, Integer> > positions = new ArrayList< Pair<Integer,Integer> >();
        positions.add( new Pair<>(1, 2) );
        positions.add( new Pair<>(2, 2) );
        
        
        int tamx = 20;
        int tamy = 5;
        int nsuciedad = 11;

        crearRestaurante( tamx, tamy, nsuciedad );

        ClientCreator.crearClientes( tamx, tamy , 1 );
        
        TPCreator.crearTP(tamx , tamy, positions);
        // crearCocineros( tamx, tamy , 3 );

        // creo que esto es para la "sincronizacion" de tiempo de los agentes
        PeriodicDataBESA data  = new PeriodicDataBESA(GAME_PERIODIC_TIME, GAME_PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
        EventBESA startPeriodicEv = new EventBESA(GameGuard.class.getName(), data);
        AgHandlerBESA ah = admLocal.getHandlerByAlias("WORLD");
        ah.sendEvent(startPeriodicEv);

    }

    // crea el mapa (restaurante)
    public static void crearRestaurante( int tamx , int tamy , int nsuciedad ) throws ExceptionBESA
    {
        WorldState ws = new WorldState( tamx , tamy , nsuciedad );
        StructBESA wrlStruct = new StructBESA();
        wrlStruct.addBehavior("WorldBehavior");
        wrlStruct.bindGuard("WorldBehavior", GameGuard.class);
        wrlStruct.addBehavior("ChangeBehavior");
        wrlStruct.bindGuard("ChangeBehavior", SubscribeGuard.class);
        wrlStruct.bindGuard("ChangeBehavior", UpdateGuard.class);
        WorldAgent wa = new WorldAgent("WORLD", ws, wrlStruct, clave );
        wa.start();
    }

    // crea varios agentes
    public static void crearCocineros( int x , int y , int cantidad ) throws ExceptionBESA
    {
        for ( int a = 0 ; a < cantidad ; ++a )
            cocinero( x, y, "CO"+Integer.toString( a ) );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    public static void cocinero( int sizex, int sizey, String name ) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("CocineroplayerPerception");
        c1Struct.bindGuard("CocineroplayerPerception", CocineroSensorGuard.class);
        ( new CocineroAgent( name, new CocineroState(sizex, sizey), c1Struct, clave ) ).start() ;

    }

    

}
