package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import model.Usuario;
=======
>>>>>>> branch 'master' of https://github.com/davibrandao18/horadoevento
import service.InscricaoService;

<<<<<<< HEAD
public class CancelarInscricao implements Command{
   
    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioService us = new UsuarioService();
        InscricaoService is = new InscricaoService();
        String cpf = request.getParameter("cpf");

        try {
            Usuario user = us.carregar(request.getParameter("username"));
            
            if (request.getParameter("senha").equals(user.getSenha()) && cpf.equals(user.getCpf())) {
                is.excluir(cpf);
                us.excluirTags(user);
                us.excluir(user.getUserName(), cpf);
                response.sendRedirect("/horadoevento/inicio/");
            }
            else request.getRequestDispatcher("erro/credenciais/").forward(request, response);;
            
        } catch (Exception e) {
            response.sendRedirect("/horadoevento/erro/not_found/");
=======
public class CancelarInscricao {
    public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        InscricaoService is = new InscricaoService();
        
        try {
            is.excluir(request.getParameter("id_i"));
            String mensagem = "Inscri��o cancelada";
            
            request.getSession().setAttribute("sessao_mensagem", mensagem);
            response.sendRedirect("/horadoevento/home/member/");
        } catch (Exception e) {
            e.printStackTrace();
>>>>>>> branch 'master' of https://github.com/davibrandao18/horadoevento
        }
<<<<<<< HEAD
        
=======
>>>>>>> branch 'master' of https://github.com/davibrandao18/horadoevento
    }
}
