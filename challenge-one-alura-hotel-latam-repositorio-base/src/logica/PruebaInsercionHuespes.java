package logica;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;

import factory.ConnectionFactory;
import views.RegistroHuesped;
import views.ReservasView;

public class PruebaInsercionHuespes {
	

	public static String obtenerNombre() {
		String nombre = (String) RegistroHuesped.txtNombre.getSelectedText();
		return nombre;

	}

	public static String obtenerApellido() {
		String apellido = (String) RegistroHuesped.txtApellido.getSelectedText();
		return apellido;
	}

	public static Date obtenerFechaNacimiento() {
		return new Date(RegistroHuesped.txtFechaN.getDate().getTime());
	}

	public static String obtenerNacionalidad() {
		String nacionalidad = (String) RegistroHuesped.txtNacionalidad.getSelectedItem();
		return nacionalidad;
	}

	public static String obtenerTelefono() {
		String telefono = (String) RegistroHuesped.txtTelefono.getSelectedText();
		return telefono;
	}

	public static String obtenerIdReservas() {
		String idReserva = RegistroHuesped.txtNreserva.getSelectedText();
		return idReserva;
	}

	
	public void insercion() throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.crearConexion();

		PreparedStatement stm = conexion.prepareStatement(
				"INSERT INTO HUESPEDES (nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) VALUES (?,?,?,?,?,?)");

		String nombre = obtenerNombre();
		String apellido = obtenerApellido();
		Date fechaNacimiento = obtenerFechaNacimiento();
		String nacionalidad = obtenerNacionalidad();
		String telefono = obtenerTelefono();
		String idReserva = obtenerIdReservas();

		stm.setString(1, nombre);
		stm.setString(2, apellido);
		stm.setDate(3, fechaNacimiento);
		stm.setString(4, nacionalidad);
		stm.setString(5, telefono);
		stm.setString(6, idReserva);

		stm.execute();

		ResultSet rst = stm.getGeneratedKeys();

		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("El id creado fue el " + id);
		}

		rst.close();
		stm.close();
		conexion.close();

	}
}
