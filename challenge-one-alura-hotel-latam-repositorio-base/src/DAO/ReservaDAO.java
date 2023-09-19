package DAO;

import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Huespes;
import modelo.Reserva;

public class ReservaDAO {
	
	private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO RESERVAS (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?,?,?,?)";
			try(PreparedStatement stm = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)){
				
				stm.setObject(1, reserva.getDateEntrada());
				stm.setObject(2, reserva.getDateSalida());
				stm.setString(3, reserva.getValor());
				stm.setString(4, reserva.getFormaPago());
				
				stm.executeUpdate();
				
				ResultSet rst = stm.getGeneratedKeys();
				
				while(rst.next()) {
					reserva.setId(rst.getInt(1));
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("animal" + e.getMessage(),e);
		}
		
	}
	
	public List<Reserva> mostrar() {
	    List<Reserva> resultado = new ArrayList<>();

	    try {
	        final PreparedStatement statement = con
	                .prepareStatement("SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas");

	        try {
	            statement.execute();

	            final ResultSet resultSet = statement.getResultSet();

	            try {
	                while (resultSet.next()) {
	                    resultado.add(new Reserva(
	                            resultSet.getInt("id"),
	                            resultSet.getDate("fechaEntrada"),
	                            resultSet.getDate("fechaSalida"),
	                            resultSet.getString("valor"),
	                            resultSet.getString("formaPago")));
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

	    return resultado;
	}

	public List<Reserva> buscarPorId(String id) {
	    List<Reserva> reservas = new ArrayList<>();
	    try {
	        String sql = "SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas WHERE id = ?";
	        try (PreparedStatement stm = con.prepareStatement(sql)) {
	            stm.setString(1, id);
	            ResultSet resultSet = stm.executeQuery();
	            while (resultSet.next()) {
	                Reserva reserva = new Reserva(
	                        resultSet.getInt("id"),
	                        resultSet.getDate("fechaEntrada"),
	                        resultSet.getDate("fechaSalida"),
	                        resultSet.getString("valor"),
	                        resultSet.getString("formaPago"));
	                reservas.add(reserva);
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    return reservas;
	}

	
	public int eliminar(Integer id) {
	    try {
	        final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE ID = ?");
	        statement.setInt(1, id);
	        statement.execute();

	        int updateCount = statement.getUpdateCount();

	        return updateCount;
	    } catch (SQLException e) {
	    	throw new RuntimeException("animal" + e.getMessage(),e);
	    }
	}
	
	public int modificar(LocalDate dateEntrada, LocalDate dateSalida, String valor, String formaPago, Integer id) {
	    PreparedStatement statement = null;
	    try {
	        statement = con.prepareStatement(
	                "UPDATE reservas SET "
	                + " fechaEntrada = ?, "
	                + " fechaSalida = ?, "
	                + " valor = ?, "
	                + " formaPago = ? "
	                + " WHERE ID = ?");

	        statement.setObject(1, java.sql.Date.valueOf(dateEntrada));
	        statement.setObject(2, java.sql.Date.valueOf(dateSalida));
	        statement.setString(3, valor);
	        statement.setString(4, formaPago);
	        statement.setInt(5, id);
	        statement.execute();

	        int updateCount = statement.executeUpdate();

	        return updateCount;
	    } catch (SQLException e) {
	    	throw new RuntimeException("animal" + e.getMessage(),e);
	    } 
	}


	
}
