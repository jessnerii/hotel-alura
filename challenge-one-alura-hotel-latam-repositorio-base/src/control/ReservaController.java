package control;

import java.sql.Connection;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import DAO.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Huespes;
import modelo.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;
	
	

	public ReservaController() throws SQLException {
		Connection con = new ConnectionFactory().crearConexion();
		this.reservaDAO = new ReservaDAO(con);
	}


	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	
	}
	
	public List<Reserva> mostrar() {
		return this.reservaDAO.mostrar();
	}
	
	
	public List<Reserva> buscar(String id) {
		return this.reservaDAO.buscarPorId(id);
	
}
	
	public int modificarReserva(LocalDate dateEntrada, LocalDate dateSalida, String valor, String formaPago, Integer id) {
		return this.reservaDAO.modificar(dateEntrada, dateSalida, valor, formaPago, id);
	}
	
	
	public void Elimar(Integer id) {
		this.reservaDAO.eliminar(id);
	}
	
	 
	
}





















