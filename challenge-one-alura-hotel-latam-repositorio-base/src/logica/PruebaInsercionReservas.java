package logica;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.temporal.ChronoUnit;

import factory.ConnectionFactory;
import views.ReservasView;

import java.text.SimpleDateFormat;

public class PruebaInsercionReservas {
		
	
	public static String obtenerFormaPago() {
		String formaPago = (String) ReservasView.txtFormaPago.getSelectedItem();
	    return formaPago;
		
	}
	
	public static Date obtenerFechaEntrada() {
	    return new Date(ReservasView.txtFechaEntrada.getDate().getTime());
	}

	public static Date obtenerFechaSalida() {
	    return new Date(ReservasView.txtFechaSalida.getDate().getTime());
	}
	


	public static String obtenerValor() {
	    Date fechaEntrada = obtenerFechaEntrada();
	    Date fechaSalida = obtenerFechaSalida();
	    
	    long differenceInDays = ChronoUnit.DAYS.between(fechaEntrada.toLocalDate(), fechaSalida.toLocalDate()); // Convertir Date a LocalDate
	    long valor = differenceInDays * 35;
	    
	    return String.valueOf(valor);
	}


	
	public void insercion() throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.crearConexion();
		
		PreparedStatement stm = conexion.prepareStatement("INSERT INTO RESERVAS (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?,?,?,?)");
		
		Date fechaEntrada = obtenerFechaEntrada();
		Date fechaSalida = obtenerFechaSalida();
		String valor = obtenerValor();
		String formaPago = obtenerFormaPago();
		
		stm.setDate(1, fechaEntrada);
		stm.setDate(2, fechaSalida);
		stm.setString(3, valor);
		stm.setString(4, formaPago);
		
		stm.execute();
		
		ResultSet rst = stm.getGeneratedKeys();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("El id creado fue el " + id);
		}
		
		rst.close();
        stm.close();
		conexion.close();

	}
	}
	
	
