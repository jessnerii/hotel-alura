package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import DAO.HuespesDAO;
import factory.ConnectionFactory;
import modelo.Huespes;
import modelo.Reserva;


public class HuespesController {
private HuespesDAO huespesDAO;
	
	public HuespesController() throws SQLException {
		Connection con = new ConnectionFactory().crearConexion();
		this.huespesDAO = new HuespesDAO(con);
	}


	public void guardar(Huespes huespes) {
		huespesDAO.guardar(huespes);
	}
	
	public List<Huespes> mostrar() {
		return this.huespesDAO.mostrar();
	}
	
	
	public List<Huespes> buscar(String nombre) {
		return this.huespesDAO.buscarPorNombre(nombre);
}
	
	public List<Huespes> buscarIdReserva(String idReserva) {
		return this.huespesDAO.buscarPorIdReserva(idReserva);
	}
	
	public void actualizarHuespes(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, String idReserva, Integer id) {
		this.huespesDAO.modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva, id);
	}
	
	
	public void Elimar(Integer id) {
		this.huespesDAO.eliminar(id);
	}
	

}
