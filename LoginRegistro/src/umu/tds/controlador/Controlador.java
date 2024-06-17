package umu.tds.controlador;

import umu.tds.dao.UsuarioDAO;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.RepositorioUsuarios;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private FactoriaDAO factoria;
	
	public static double precio=6;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return RepositorioUsuarios.INSTANCE.findUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	public boolean loginUsuarioGit(String nombre) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(nombre);
		if (usuario != null) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	public boolean comprobarPremium() 
	{
		return usuarioActual.getPremium();
	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);

		UsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO.create(usuario);

		RepositorioUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.delete(usuario);

		RepositorioUsuarios.INSTANCE.removeUsuario(usuario);
		return true;
	}
	
	public boolean usuarioPremium() 
	{
		usuarioActual.pago();
		//Añadir playlist y actulizar en la bbdd el valor a premium
		Boolean salida=usuarioActual.getPremium();
		UsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO();
		usuarioDAO.update(usuarioActual);
		return salida;
	
	}
	
	
}
