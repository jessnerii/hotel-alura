package logica;

import java.sql.Date;

import java.text.SimpleDateFormat;

import views.ReservasView;



public class ObtenerIdReserva {
	
	public static Date obtenerFechaEntrada() {
	    return new Date(ReservasView.txtFechaEntrada.getDate().getTime());
	}

	public static Date obtenerFechaSalida() {
	    return new Date(ReservasView.txtFechaSalida.getDate().getTime());
	}
	public static int contadorReservas = 1;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
	

	public static String obtenerIdReserva() {
		String mesDiaEntrada = dateFormat.format(obtenerFechaEntrada());
	    String mesDiaSalida = dateFormat.format(obtenerFechaSalida());

	    String idReserva = mesDiaEntrada + mesDiaSalida + contadorReservas;

	    return idReserva;
	}

	
}
