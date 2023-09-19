package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import factory.ConnectionFactory;
import views.MenuUsuario;

public class Usuario {
	
	private Long id;
	private String name;
	private String password;
	
	/*public void Usuarios (Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}*/
	
	public void validarUsuario(JTextField usuario, JPasswordField contrasenha) {
		
		try {
			ResultSet rs = null;
			PreparedStatement status = null;
			ConnectionFactory con = new ConnectionFactory();
			
			String consulta = "SELECT * FROM usuarios WHERE usuarios.login = (?) "
					+ "AND usuarios.clave = (?)";
			status = con.crearConexion().prepareStatement(consulta);
			
			String contra = String.valueOf(contrasenha.getPassword());
			
			status.setString(1, usuario.getText());
			status.setString(2, contra);
			
			rs = status.executeQuery();
			
			if(rs.next()) {
				MenuUsuario menu = new MenuUsuario();
				menu.setVisible(true);
					 
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o Contraseña no válidos");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuario o Contraseña no válidos");
			
		}
		
		
		Connection cone = null;
		PreparedStatement status = null;
		System.out.println("Se conecto con exito");
		//cone.close();
		
		System.out.println("Cerro con exito");
	}
	
	
	
	
	
	
	
	
	
	
	
}
