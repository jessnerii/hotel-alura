package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Huespes;
import modelo.Reserva;

public class HuespesDAO {
	private Connection con;

	public HuespesDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huespes huespes) {
		try {
			String sql = "INSERT INTO HUESPES (nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) VALUES (?,?,?,?,?,?)";
			try (PreparedStatement stm = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

				stm.setString(1, huespes.getNombre());
				stm.setString(2, huespes.getApellido());
				stm.setObject(3, huespes.getFechaNacimiento());
				stm.setString(4, huespes.getNacionalidad());
				stm.setString(5, huespes.getTelefono());
				stm.setString(6, huespes.getIdReserva());

				stm.executeUpdate();

				ResultSet rst = stm.getGeneratedKeys();

				while (rst.next()) {
					huespes.setId(rst.getInt(1));
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("animal" + e.getMessage(), e);
		}

	}

	public List<Huespes> mostrar() {
		List<Huespes> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespes");

			try {
				statement.execute();

				final ResultSet resultSet = statement.executeQuery();

				try {
					while (resultSet.next()) {
						resultado.add(new Huespes(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("fechaNacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getString("idReserva")));
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
	
	public List<Huespes> buscarPorNombre(String id) {
	    List<Huespes> huespes = new ArrayList<>();
	    try {
	        String sql = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespes WHERE nombre = ?";
	        try (PreparedStatement stm = con.prepareStatement(sql)) {
	            stm.setString(1, id);
	            ResultSet resultSet = stm.executeQuery();
	            while (resultSet.next()) {
	                Huespes huespe = new Huespes(resultSet.getInt("id"), resultSet.getString("nombre"),
							resultSet.getString("apellido"), resultSet.getDate("fechaNacimiento"),
							resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
							resultSet.getString("idReserva"));
	                huespes.add(huespe);
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    return huespes;
	}
	
	public List<Huespes> buscarPorIdReserva(String id) {
	    List<Huespes> huespes = new ArrayList<>();
	    try {
	        String sql = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespes WHERE idReserva = ?";
	        try (PreparedStatement stm = con.prepareStatement(sql)) {
	        	 stm.setString(1, id);
		            ResultSet resultSet = stm.executeQuery();
		            while (resultSet.next()) {
		                Huespes huespe = new Huespes(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("fechaNacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getString("idReserva"));
		                huespes.add(huespe);
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    return huespes;
	}
	


	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM huespes WHERE ID = ?");
			statement.setInt(1, id);
			statement.execute();

			int updateCount = statement.getUpdateCount();

			return updateCount;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, String idReserva, Integer id) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("UPDATE huespes SET "
	                + " nombre = ?, "
	                + " apellido = ?,"
	                + " fechaNacimiento = ?,"
	                + " nacionalidad = ?,"
	                + " telefono = ?,"
	                + " idReserva = ?"
	                + " WHERE ID = ?");

			statement.setString(1, nombre);
			statement.setString(2, apellido);
			statement.setObject(3, java.sql.Date.valueOf(fechaNacimiento));
			statement.setString(4, nacionalidad);
			statement.setString(5, telefono);
			statement.setString(6, idReserva);
			statement.setInt(7, id);
			statement.execute();

			int updateCount = statement.executeUpdate();

			return updateCount;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}

}
