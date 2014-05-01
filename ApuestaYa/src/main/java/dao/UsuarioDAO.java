package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {

	public Usuario registrarUsuario(Usuario usuario);

	public void borrarUsuario(Usuario usuario);

	public Usuario modificarUsuario(Usuario usuario);

	public Usuario recuperarUsuario(int codigo);

	public List<Usuario> recuperarTodosUsuarios();

	public Usuario modificarContrasena(Usuario usuario);

	public Usuario modificarTipoIngreso(Usuario usuario);

	public Usuario modificarTipoPago(Usuario usuario);

	public Usuario modificarTransacciones(Usuario usuario);

	public Usuario modificarListaApuestas(Usuario usuario);

}