package controller;

//import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Empresa;
import model.Usuario;
import service.EmpresaService;
import service.UsuarioService;

/**
 * Servlet implementation class Cadastro
 */
@WebServlet("/cadastro/Cadastro.do")
public class CadastroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("CARREGANDO");
		
		UsuarioService us = new UsuarioService();
		EmpresaService es = new EmpresaService();
		Usuario usuario = new Usuario();
		Empresa empresa = new Empresa();
		
		switch (request.getParameter("entidade")) {
			case "usuario": {
				usuario.setNome(request.getParameter("nome"));
				usuario.setCpf(request.getParameter("cpf"));
				usuario.setUserName(request.getParameter("username"));
				usuario.setEmail(request.getParameter("email"));
				usuario.setSenha(request.getParameter("senha"));
				usuario.setLinkedin(request.getParameter("linkedin"));
				//File foto = new File("/horadoevento/assets/logo/default250.png"); /!\ N�O FUNCIONA SOCORRO
				//usuario.setFoto(foto);
				
				System.out.println(usuario.toString());
				
				us.criar(usuario);
				//us.criarImagem(usuario.getFoto(), usuario.getUserName());
				break;
			}
			case "empresa": {
				empresa.setCnpj(request.getParameter("cnpj"));
				empresa.setUserName(request.getParameter("username"));
				empresa.setNome(request.getParameter("nome"));
				empresa.setCidade(request.getParameter("cidade"));
				empresa.setEstado(request.getParameter("estado"));
				empresa.setPais(request.getParameter("pais"));
				empresa.setSenha(request.getParameter("senha"));
				empresa.setEmail(request.getParameter("email"));
				empresa.setLinkedin(request.getParameter("linkedin"));
				
				System.out.println(empresa.toString());
				
				es.criar(empresa);
				break;
			}
		}
		response.sendRedirect("/horadoevento/login/login.jsp");
	}
}
