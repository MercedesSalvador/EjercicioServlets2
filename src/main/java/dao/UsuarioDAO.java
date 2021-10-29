package dao;

import java.util.List;

import org.hibernate.Session;

import entity.Usuarios;


public class UsuarioDAO {
	
	//MOSTRAR USUARIO
	public static Usuarios getEmpleado(Session s, int id) {
		return s.get(Usuarios.class, id);
	}
	
	
	public static List<Usuarios> getAllUsuarios(Session s) {
		String hQuery = "from Usuarios";
		List<Usuarios> listaUsuarios = s.createQuery(hQuery, Usuarios.class).list();
		return listaUsuarios ;
	}
	
	/*
	public boolean existeUsuario(String user, String password) {
		
		boolean existe = false;
		if(user.equals(Usuarios.getNombre()) && password.equals(Usuarios.getClave())){
			existe = true;
		}
		
		return existe;
	}*/
	

}
