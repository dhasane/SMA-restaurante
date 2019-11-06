
import BESA.ExceptionBESA;
import Creator.CACreator;
import Creator.COCreator;
import Creator.EPCreator;
import Creator.TPCreator;
import DOOR.CLDoor;

public class Restaurante {


    private static double clave = 0.91;

    // CL -> cliente		TODO
    // CO -> cocinero		TODO
    // TP -> toma pedido	
    // EP -> entrega pedido	TODO
    // CA -> caja
    // AY -> ayudante		TODO

    public static void main(String[] args) throws ExceptionBESA {

        TPCreator.setClave( clave );
        EPCreator.setClave( clave );
        COCreator.setClave( clave );
        
        int cantidadMaximaDeClientes = 7;
        
        TPCreator.crearTP( 4 );
        CACreator.crearCA( 4 );
        
        EPCreator.crearEP( 5 );
        COCreator.crearCO( 3 );
        
        
        // siendo que los clientes son los que inician la cadena,
        // deben ser llamados de ultimo, cuando ya todos los demas
        // agentes hayan sido inicializados
        new CLDoor( clave, cantidadMaximaDeClientes );
        
    }

}
