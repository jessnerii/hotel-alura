package modelo;

import java.sql.Date;

public class Reserva {

	private Integer id;
	private java.util.Date dateEntrada;
	private java.util.Date dateSalida;
	private String valor;
	private String formaPago;
	
	public Reserva(java.util.Date dateEntrada, java.util.Date dateSalida, String valor, String formaPago) {
		this.dateEntrada = dateEntrada;
		this.dateSalida = dateSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(int id, Date dateEntrada, Date dateSalida, String valor, String formaPago) {
		this.id = id;
		this.dateEntrada = dateEntrada;
		this.dateSalida = dateSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.util.Date getDateEntrada() {
		return dateEntrada;
	}

	public void setDateEntrada(Date dateEntrada) {
		this.dateEntrada = dateEntrada;
	}

	public java.util.Date getDateSalida() {
		return dateSalida;
	}

	public void setDateSalida(Date dateSalida) {
		this.dateSalida = dateSalida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	
}
