package Resultados;

import Utils.Utils;

public class Resultados {
//	private static float tiempoClienteVisitaCompletaPromedio;
//	private static float tiempoClienteEsperaFilaTPPromedio;
//	private static float tiempoClienteEsperaFilaPagosPromedio;
//	private static float tiempoClienteEsperaFilaExternaPromedio;

	private static long tiempoClienteEsperaFilaExterna;
	private static int tiempoClienteVisitaCompleta;
	private static long tiempoInicio;
	private static long tiempoClienteEsperaFilaTP;
	private static long tiempoClienteEsperaFilaCA;

	public static synchronized void iniciar() {
//		tiempoClienteEsperaFilaTPPromedio = 0;
//		tiempoClienteEsperaFilaPagosPromedio = 0;

		tiempoClienteEsperaFilaExterna = 0;
		tiempoClienteEsperaFilaTP = 0;
		tiempoClienteEsperaFilaCA= 0;
		tiempoClienteVisitaCompleta = 0;
		tiempoInicio = Utils.conseguirTiempo();
	}

	// creo que esto va a sobrepasar el maximo...
	public static synchronized void agregarATiempoClienteVisitaCompleta(long t) {
		tiempoClienteVisitaCompleta += t / Utils.clientesAAtender;
	}

	public static synchronized void agregarTiempoClienteEsperaFilaExterna(long tt) {
		tiempoClienteEsperaFilaExterna += tt / Utils.clientesAAtender;
	}

	public static void mostrarResultados() {
		long total = Utils.conseguirTiempo() - tiempoInicio;
		long porcentaje = tiempoClienteVisitaCompleta / total;
		System.out.println("promedio visita completa : " + tiempoClienteVisitaCompleta + " (" + porcentaje + "%)");
		porcentaje = tiempoClienteEsperaFilaTP / total;
		System.out.println("promedio espera fila pedidos : " + tiempoClienteEsperaFilaTP + " (" + porcentaje  + "%)");
		porcentaje = tiempoClienteEsperaFilaCA / total;
		System.out.println("promedio espera fila caja : " + tiempoClienteEsperaFilaCA + " (" + porcentaje + "%)");
		porcentaje = tiempoClienteEsperaFilaExterna / total;
		System.out.println("promedio espera externa al restaurante : " + tiempoClienteEsperaFilaExterna + " (" + porcentaje + "%)");
		System.out.println("tiempo total " + total );
	}

	public static void agregarTiempoClienteEsperaFilaTP(long tt) {
		tiempoClienteEsperaFilaTP += tt / Utils.clientesAAtender;
		
	}

	public static void agregarTiempoClienteEsperaFilaCA(long tt) {
		tiempoClienteEsperaFilaCA += tt / Utils.clientesAAtender;
		
	}
}
