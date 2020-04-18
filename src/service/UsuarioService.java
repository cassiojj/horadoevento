package service;

import model.Usuario;
import dao.UsuarioDao;


public class UsuarioService {
	
	/**
	 * Atributo para instanciar a classe UsuarioDao
	 */
	private UsuarioDao dao = new UsuarioDao();
	
	
	/**
	 * Metodo responsavel por chamar o inserir DAO
	 * 
	 * @param usuario
	 */
	public void criar(Usuario usuario) {
		dao.inserirUsuario(usuario);
		System.out.println("Deu certo a exclus�o");
	}
	
	
	/**
	 * Metodo responsavel por chamar o atualizar DAO
	 * 
	 * @param usuario
	 */
	public void atualizar(Usuario usuario){
		dao.atualizarUsuario(usuario.getCpf(), usuario.getUserName(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getLinkedin());
		System.out.println("Deu certo a atualiza��o ! /n"+usuario.toString());
	}
	
	/**
	 * Metodo responsavel por chamar o excluir DAO
	 * 
	 * @param username
	 * @param cpf
	 */
	public void excluir(String username, String cpf){
		dao.deletarUsuario(username, cpf);
		System.out.println("Deu certo a exclus�o");
	}
	
	/**
	 * Metodo responsavel por chamar a consulta de usuario do DAO
	 * 
	 * @param username
	 * @return
	 */
	public Usuario carregar(String cpf){
		return dao.consultarUsuario(cpf);
	}
	
	public Usuario listar(String username) {
		return dao.listarUsuarios(username);
	}

}