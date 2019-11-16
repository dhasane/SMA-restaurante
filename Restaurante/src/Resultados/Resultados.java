package Resultados;

public class Resultados {
//	private static float tiempoClienteVisitaCompletaPromedio;
//	private static float tiempoClienteEsperaFilaTPPromedio;
//	private static float tiempoClienteEsperaFilaPagosPromedio;
//	private static float tiempoClienteEsperaFilaExternaPromedio;
	
	private static long tiempoClienteEsperaFilaExterna;
	private static int tiempoClienteVisitaCompleta;
	
	public static synchronized void iniciar()
	{
//		tiempoClienteVisitaCompletaPromedio = 0;
//		tiempoClienteEsperaFilaTPPromedio = 0;
//		tiempoClienteEsperaFilaPagosPromedio = 0;
//		tiempoClienteEsperaFilaExternaPromedio = 0;
		
		tiempoClienteEsperaFilaExterna = 0;
		tiempoClienteVisitaCompleta = 0;
	}
	
	// creo que esto va a sobrepasar el maximo...
	public static synchronized void agregarATiempoClienteVisitaCompletaPromedio( long t)
	{
		tiempoClienteVisitaCompleta += t;
	}

	public static synchronized void agregarTiempoClienteEsperaFilaExternaPromedio(long tt)
	{
		tiempoClienteEsperaFilaExterna  = tt;
	}
	
	
	public static void mostrarResultados()
	{
		System.out.println("promedio visita completa : " + tiempoClienteVisitaCompleta);
		System.out.println("promedio espera externa al restaurante" + tiempoClienteEsperaFilaExterna);
	}
}
