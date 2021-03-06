package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Evento;
import model.Inscricao;
import model.Usuario;
import service.EventoService;
import service.InscricaoService;

public class CriarInscricao implements Command {

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEvento = Integer.parseInt(request.getParameter("idEvento"));
        HttpSession sessao = request.getSession();
        Usuario u = (Usuario) sessao.getAttribute("sessao_user");

        Inscricao i = new Inscricao();
        InscricaoService is = new InscricaoService();
        EventoService evs = new EventoService();
        Evento e = evs.carregar(idEvento);

        i.setUser(u);
        i.setEvento(e);
        i.setId(is.criar(i));

        sessao.removeAttribute("inscricoes");

        ArrayList<Inscricao> inscricoes = new ArrayList<Inscricao>();
        inscricoes = is.listar(u);

        sessao.setAttribute("inscricoes", inscricoes);

        response.sendRedirect("/horadoevento/view/inscricao/");
    }
}
