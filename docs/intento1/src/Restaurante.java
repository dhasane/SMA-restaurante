import java.util.ArrayList;
import java.util.List;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.AdmBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import Creator.CLCreator;
import Creator.COCreator;
import Creator.EPCreator;
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


    // EP -> entrega pedido
    // TP -> toma pedido
    // CO -> cocinero
    // CL -> cliente
    // CA -> caja
    // AY -> ayudante // TODO


    public static void main(String[] args) throws ExceptionBESA {

        AdmBESA admLocal = AdmBESA.getInstance();
        CLCreator.setClave(clave);
        TPCreator.setClave(clave);
        EPCreator.setClave(clave);
        COCreator.setClave(clave);


        int cocinaInicio = 18;
        int tamx = 40;
        int tamy = 13;

        List <Pair<Integer, Integer> > sillas = new ArrayList< Pair<Integer,Integer> >();

        pintarMesa( sillas , 4 , 1);

        pintarMesa( sillas , 4 , 5);

        pintarMesa( sillas , 4 , 9);

        pintarMesa( sillas , 11 , 1);

        pintarMesa( sillas , 11 , 5);

        pintarMesa( sillas , 11 , 9);


        crearRestaurante( tamx, tamy, cocinaInicio, sillas );


        List <Pair<Integer, Integer> > tpPos = new ArrayList< Pair<Integer,Integer> >();
        tpPos.add( new Pair<>( cocinaInicio , tamy -1 ) );
        tpPos.add( new Pair<>( cocinaInicio , tamy -2 ) );

        TPCreator.crearTP(tamx , tamy, tpPos);


        List <Pair<Integer, Integer> > entregas = new ArrayList< Pair<Integer,Integer> >();
        entregas.add( new Pair<>( cocinaInicio, 2) );
        entregas.add( new Pair<>( cocinaInicio, 3) );

        EPCreator.crearEP(tamx, tamy, entregas);

        CLCreator.crearCL(tamx, tamy, 1 );

        COCreator.crearCocineros(tamx, tamy, 3);

        // creo que esto es para la "sincronizacion" de tiempo de los agentes
        PeriodicDataBESA data  = new PeriodicDataBESA(GAME_PERIODIC_TIME, GAME_PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
        EventBESA startPeriodicEv = new EventBESA(GameGuard.class.getName(), data);
        AgHandlerBESA ah = admLocal.getHandlerByAlias("WORLD");
        ah.sendEvent(startPeriodicEv);

    }

    public static void pintarMesa( List <Pair<Integer, Integer> > sillas, int inix,int iniy )
    {
    	// estas son todas las sillas de una mesa
        sillas.add( new Pair<>( inix, iniy) );
        sillas.add( new Pair<>( inix+1, iniy) );
        sillas.add( new Pair<>( inix+2, iniy) );
        sillas.add( new Pair<>( inix+3, iniy) );

        sillas.add( new Pair<>( inix+4, iniy+1) );

        sillas.add( new Pair<>( inix, iniy+2) );
        sillas.add( new Pair<>( inix+1, iniy+2) );
        sillas.add( new Pair<>( inix+2, iniy+2) );
        sillas.add( new Pair<>( inix+3, iniy+2) );

        sillas.add( new Pair<>( inix-1, iniy+1) );
        // final de las sillas de la mesa
    }

    // crea el mapa (restaurante)
    public static void crearRestaurante( int tamx , int tamy, int cocinaInicio , List<Pair<Integer, Integer>> sillas ) throws ExceptionBESA
    {
        WorldState ws = new WorldState( tamx , tamy, cocinaInicio , sillas );
        StructBESA wrlStruct = new StructBESA();
        wrlStruct.addBehavior("WorldBehavior");
        wrlStruct.bindGuard("WorldBehavior", GameGuard.class);
        wrlStruct.addBehavior("ChangeBehavior");

        wrlStruct.bindGuard("ChangeBehavior", SubscribeGuard.class);
        wrlStruct.bindGuard("ChangeBehavior", UpdateGuard.class);
        ( new WorldAgent("WORLD", ws, wrlStruct, clave ) ).start();
    }




}
