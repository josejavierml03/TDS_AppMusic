package umu.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;

public enum RepositorioUsuarios {
	INSTANCE;
	private FactoriaDAO factoria;

	private HashMap<Integer, Usuario> usuariosPorID;
	private HashMap<String, Usuario> usuariosPorLogin;

	private RepositorioUsuarios (){
		usuariosPorID = new HashMap<Integer, Usuario>();
		usuariosPorLogin = new HashMap<String, Usuario>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Usuario> listausuarios = factoria.getUsuarioDAO().getAll();
			for (Usuario usuario : listausuarios) {
				usuariosPorID.put(usuario.getId(), usuario);
				usuariosPorLogin.put(usuario.getLogin(), usuario);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Usuario> findUsuarios() throws DAOException {
		return new LinkedList<Usuario>(usuariosPorLogin.values());
	}
	
	public Usuario findUsuario(String login) {
		return usuariosPorLogin.get(login);
	}

	public Usuario findUsuario(int id) {
		return usuariosPorID.get(id);
	}
	
	public void addUsuario(Usuario usuario) {
		usuariosPorID.put(usuario.getId(), usuario);
		usuariosPorLogin.put(usuario.getLogin(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuariosPorID.remove(usuario.getId());
		usuariosPorLogin.remove(usuario.getLogin());
	}

}
