package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servletProduto")
public class ServletProduto extends HttpServlet {
    
    @EJB
    private ProdutoFacadeLocal facade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            
            List<Produto> produtos = facade.findAll();

            // Gera a resposta HTML com a lista de produtos
            out.println("<html>");
            out.println("<head><title>Lista de Produtos</title></head>");
            out.println("<body>");
            out.println("<h1>Lista de Produtos</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Nome</th><th>Preço</th></tr>");

            // Percorre a lista de produtos e exibindo-os na tabela
            for (Produto produto : produtos) {
                out.println("<tr>");
                out.println("<td>" + produto.getId() + "</td>");
                out.println("<td>" + produto.getNome() + "</td>");
                out.println("<td>" + produto.getPrecoVenda() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Erro ao buscar os produtos</h2>");
        } finally {
            out.close();
        }
    }
}
