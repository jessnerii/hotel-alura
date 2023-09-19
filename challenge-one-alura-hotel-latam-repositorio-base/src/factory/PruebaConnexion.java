package factory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import modelo.Reserva;

public class PruebaConnexion {

	private Integer id;
	private LocalDate dateEntrada;
	private LocalDate dateSalida;
	private String valor;
	private String formaPago;
	public static void main(String[] args) throws SQLException {
		
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC", "root", "root1234");
		System.out.println("Probando Conexion");
		
		PreparedStatement stm = conexion.prepareStatement("INSERT INTO RESERVAS (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		
		Date fechaEntrada = Date.valueOf("2023-09-04");
		Date fechaSalida = Date.valueOf("2023-09-12");
		
		stm.setDate(1, fechaEntrada);
		stm.setDate(2, fechaSalida);
		stm.setString(3, "70");
		stm.setString(4, "efectivo");
		
		stm.execute();
		
		ResultSet rst = stm.getGeneratedKeys();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("El id creado fue " + id);
		}
		
		conexion.close();
		
		

		
	
		
	}
	

}
