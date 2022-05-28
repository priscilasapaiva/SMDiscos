package smdecommerce.venda_produto.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Classe que implementa o padrão DAO para a entidade usuário
 */
public class Venda_ProdutoDAO {

    /**
     * Método utilizado para obter uma venda relacionado a um produto
     *
     * @param id_venda
     * @return
     * @throws Exception
     */
    public Venda_Produto obterProduto(Integer id_venda) throws Exception {
        Venda_Produto venda_produto = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id_venda, id_produto, quantidade FROM venda_produto WHERE id_venda = ?");
        preparedStatement.setInt(1, id_venda);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            venda_produto = new Venda_Produto();
            venda_produto.setId_venda(resultSet.getInt("id_venda"));
            venda_produto.setId_venda(resultSet.getInt("id_produto"));
            venda_produto.setQuantidade(resultSet.getInt("quantidade"));
          
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (venda_produto == null) {
            throw new Exception("Produto não encontrado");
        }
        return venda_produto;
    }

    /**
     * Método utilizado para inserir um produto em uma categoria
     *
     * @param id_produto
     * @param id_venda
     * @param quantidade
     * @throws Exception
     */
    public void inserir( Integer id_venda, Integer id_produto,Integer quantidade) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO produto_categoria (id_produto, id_categoria) VALUES (?, ?, ?)");
        preparedStatement.setInt(1, id_venda);
        preparedStatement.setInt(2, id_produto);
        preparedStatement.setInt(3, quantidade);

        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o produto na venda");
        }
    }
    
    /**
     * Método utilizado para atualizar uma categoria do produto
     * 
     * @param id_produto
     * @param id_categoria
     * @throws Exception
     */
    public void atualizar(Integer id_produto, Integer id_categoria) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE produto_categoria SET id_categoria=? WHERE id_produto = ?");
        preparedStatement.setInt(1, id_categoria);
        preparedStatement.setInt(2, id_produto);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar a categoria do produto");
        }
    }
    
    /**
     * Método utilizado para excluir um  produto da categoria
     * 
     * @param id_produto
     * @throws Exception
     */
    public void excluir(Integer id_produto) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM produto_categoria WHERE id_produto = ?");
        preparedStatement.setInt(1, id_produto);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível excluir o produto da categoria");
        }
    }
}
