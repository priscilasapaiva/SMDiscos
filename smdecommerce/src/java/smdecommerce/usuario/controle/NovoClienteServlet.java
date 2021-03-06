package smdecommerce.usuario.controle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.cliente.modelo.ClienteDAO;
import smdecommerce.usuario.modelo.Usuario;
import smdecommerce.usuario.modelo.UsuarioDAO;

/**
 *
 * 
 * Classe que implementa a ação de inserir um novo usuário do tipo cliente
 */
public class NovoClienteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        /* processamento */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean inseriu = false;
        String mensagem = null;
        try {
            usuarioDAO.inserir(nome, email, login, senha);
            Usuario usuario = usuarioDAO.obter(login);
            clienteDAO.inserir(usuario.getId(), "Não cadastrado");
            inseriu = true;
            mensagem = "Cliente inserido com sucesso";
        } catch (Exception ex) {
            inseriu = false;
            mensagem = ex.getMessage();
        }
        /* saída */
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

}
