package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection crearConexion() throws SQLException{
    	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC", "root", "root1234");
		System.out.println("Conexion Exitosa");
		return conexion;
    }

}