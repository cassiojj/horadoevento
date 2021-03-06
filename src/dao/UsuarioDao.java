package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;;

/**
 * Data Acess Object para o Usuario
 * 
 * @author Davi Fonseca
 * @version 0.1
 * @since 0.1
 */
public class UsuarioDao {
	
	/**
	 * Metodo de insercao de usuario no Banco de dados
	 * @since 0.1
	 * @param Usuario usuario
	 */
	public void inserirUsuario(Usuario usuario) {
		String sqlInsert = "INSERT INTO usuario"
				+"(cpf,username,nome,email,senha,linkedin,foto)"
				+"VALUES (?,?,?,?,?,?,?)";
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(sqlInsert);) {
			pst.setString(1,usuario.getCpf());
			pst.setString(2,usuario.getUserName());
			pst.setString(3,usuario.getNome());
			pst.setString(4,usuario.getEmail());
			pst.setString(5,usuario.getSenha());
			pst.setString(6,usuario.getLinkedin());
			pst.setString(7,null);

			pst.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	/**
	 * Metodo para consultar usuarios:
	 * @author Davi Fonseca
	 * @since 0.2
	 * @param String username;
	 */
	public Usuario consultarUsuario(String username, String path) {
		String consulta = "SELECT * FROM usuario WHERE username='"+username+"'";
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(consulta);) {
			
			ResultSet resultado = pst.executeQuery();
			
			if (resultado.next()){
				Usuario usuario = new Usuario();
				String cpf = resultado.getString("cpf");
				String nome = resultado.getString("nome");
				String email = resultado.getString("email");
				String senha = resultado.getString("senha");
				String linkedin = resultado.getString("linkedin");
				File foto = recuperarImagem(username, path);
				
				usuario.setCpf(cpf);
				usuario.setUserName(username);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				usuario.setLinkedin(linkedin);
				usuario.setFoto(foto);
				
				return usuario;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * consulta de usuario sem manipulação de arquivo
	 * @param username
	 * @return
	 */
	public Usuario consultarUsuario(String username) {
		String consulta = "SELECT * FROM usuario WHERE username='"+username+"'";
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(consulta);) {
			
			ResultSet resultado = pst.executeQuery();
			
			if (resultado.next()){
				Usuario usuario = new Usuario();
				String cpf = resultado.getString("cpf");
				String nome = resultado.getString("nome");
				String email = resultado.getString("email");
				String senha = resultado.getString("senha");
				String linkedin = resultado.getString("linkedin");
				
				usuario.setCpf(cpf);
				usuario.setUserName(username);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				usuario.setLinkedin(linkedin);
				
				return usuario;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * consulta de usuario sem manipulação de arquivo
	 * @param username
	 * @return
	 */
	public Usuario consultarUsuarioCpf(String cpf) {
		String consulta = "SELECT * FROM usuario WHERE cpf=?";
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(consulta);) {
			pst.setString(1, cpf);
			
			ResultSet resultado = pst.executeQuery();
			
			if (resultado.next()){
				Usuario usuario = new Usuario();
				String username = resultado.getString("username");
				String nome = resultado.getString("nome");
				String email = resultado.getString("email");
				String senha = resultado.getString("senha");
				String linkedin = resultado.getString("linkedin");
				
				usuario.setCpf(cpf);
				usuario.setUserName(username);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				usuario.setLinkedin(linkedin);
				
				return usuario;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Metodo de listagem de usuarios semelhantes utilizando o username
	 * @author Maicon Souza e Davi Fonseca
	 * @since
	 * @param String username
	 * @return arraylist de usuarios
	 */
	public ArrayList<Usuario> listarUsuarios(String username) {
		String sqlSelect = "SELECT * FROM usuario WHERE username LIKE '%" +username +"%'";

		ArrayList<Usuario> listaUsuario = new ArrayList<>();
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(sqlSelect);) {
			
			ResultSet resultado = pst.executeQuery();
			
			while(resultado.next())
				listaUsuario.add(consultarUsuario(resultado.getString("username")));
			return listaUsuario;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * Metodo para alteracao de imagem:
	 * @author Davi Fonseca
	 * @since 0.1
	 * @param File foto
	 * @param String username
	 */
	public void inserirImagem(File foto, String username) {
			
		//Preparando a String para insercao:
		String sqlUpdate = "UPDATE usuario SET foto = ?"
				+ "WHERE username = ?";
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(sqlUpdate)) {
			
			//criando fluxo de manipulacao de arquivo
			FileInputStream inputStream = new FileInputStream(foto);
			
			pst.setBinaryStream(1,				//Posicao do Sql
					(InputStream) inputStream,	//Fluxo de informacao
					(int) (foto.length())		//Quantidade de bytes
					);
			pst.setString(2, username);
			
			//Enviando um comando para o MySQL
			pst.execute();
			
			
		} catch (Exception e) {
			//Imprimido a pilha de erros:
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para recuperar imagem do banco de dados de acordo com o apelido.
	 * @author Davi Fonseca
	 * @since 0.1
	 * @param String username
	 */
	public File recuperarImagem(String username, String path) {
		String selectImg = "SELECT foto FROM usuario WHERE username = ?";
		
		try {
			Connection conectar = ConnectionFactory.obtemConexao();
			PreparedStatement pst = conectar.prepareStatement(selectImg);
			
			pst.setString(1, username);
			
			ResultSet resultado = pst.executeQuery();
			// Arquivo onde a imagem sera armazenada no disco:
			File file = new File(path + File.separator + username +".jpg");
			// Objeto para tratar saida de dados para um arquivo:
			FileOutputStream output = new FileOutputStream(file);
			
			// Verificando se encontrou registro para o select:
			if (resultado.next()) {
				// Pegando o campo do tipo blob, chamado "foto":
				InputStream input = resultado.getBinaryStream("foto");
				// Preparando um vetor de bytes para enviar para o arquivo:
		        byte[] buffer = new byte[1024];
		        // Enquanto existir conteudo no fluxo de dados, continua:
		        while (input.read(buffer) > 0) {
		        	// Escreve o conteudo no arquivo de destino no disco:
		            output.write(buffer);
		        }
				// Fechando a entrada:
		        input.close();
			}
			// Encerra a saida:
			output.close();
			resultado.close();
			return file;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Metodo para atualizar usuario no bd
	 * @author Davi Fonseca
	 * @since 0.1
	 * @param user
	 * @throws SQLException
	 */
	public void atualizarUsuario(Usuario user) {
		
		/**
		 * Criando a String de atualizacao
		 */
		String atualizar = "UPDATE usuario SET cpf = ?, username = ?, nome = ?, email = ?, senha = ?, linkedin = ? WHERE username='"+user.getUserName()+"'";
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(atualizar)) {
			
			pst.setString(1, user.getCpf());
			pst.setString(2, user.getUserName());
			pst.setString(3, user.getNome());
			pst.setString(4, user.getEmail());
			pst.setString(5, user.getSenha());
			pst.setString(6, user.getLinkedin());
			
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para deletar usuario no bd
	 * @author Davi Fonseca
	 * @since 0.1
	 * @param username
	 * @param cpf
	 */
	public void deletarUsuario(String username, String cpf) {
		String deletarUsuario = "DELETE FROM usuario WHERE username='" +username +"' AND cpf='" +cpf +"'";
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(deletarUsuario)) {
			
			pst.execute();
		} catch (Exception e) {
			//Imprimido a pilha de erros:
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Insere tag_usuario
	 * @param cpf
	 * @param idTag
	 */
	public void inserirTag(String cpf, int idTag){
		String sqlInsert = "INSERT INTO tag_usuario"
				+"(fk_tag_id, fk_usuario_cpf)"
				+"VALUES (?,?)";
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(sqlInsert);) {
			pst.setInt(1, idTag);
			pst.setString(2, cpf);

			pst.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void deletarTags(String cpf){
		String deletarTags = "DELETE FROM tag_usuario WHERE fk_usuario_cpf=?";
		
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(deletarTags)) {
			pst.setString(1, cpf);
			
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void inserirEmpresa(String cpf, String cnpj){
		String sqlInsert = "INSERT INTO empresa_usuario"
				+"(fk_empresa_cnpj, fk_usuario_cpf)"
				+"VALUES (?,?)";
		try (Connection conectar = ConnectionFactory.obtemConexao();
				PreparedStatement pst = conectar.prepareStatement(sqlInsert);) {
			pst.setString(1, cnpj);
			pst.setString(2, cpf);

			pst.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
}