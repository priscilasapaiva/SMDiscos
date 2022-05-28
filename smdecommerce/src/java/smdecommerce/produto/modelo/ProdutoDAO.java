package smdecommerce.produto.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Classe que implementa o padrão DAO para a entidade produto
 */
public class ProdutoDAO {

    /**
     * Método utilizado para obter um produto pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Produto obter(int id) throws Exception {
        Produto produto = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id, nome, autor, descricao, preco, quantidade, foto FROM produto WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            produto = new Produto();
            produto.setId(resultSet.getInt("id"));
            produto.setNome(resultSet.getString("nome"));
            produto.setAutor(resultSet.getString("autor"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setPreco(resultSet.getDouble("preco"));
            produto.setQuantidade(resultSet.getInt("quantidade"));
            produto.setFoto(resultSet.getString("foto"));
            
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (produto == null) {
            throw new Exception("Produto não encontrado");
        }
        return produto;
    }


    /**
     * Método utilizado para inserir um novo produto
     *
     * @param id
     * @param nome
     * @param autor
     * @param descricao
     * @param preco
     * @param foto
     * @param quantidade

     * @throws Exception
     */
    public void inserir( Integer id,String nome, String autor, String descricao, Double preco, Integer quantidade, String foto) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO produto (id, nome, autor, descricao, preco, quantidade, foto) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, autor);
        preparedStatement.setString(4, descricao);
        preparedStatement.setDouble(5, preco);
        preparedStatement.setInt(6, quantidade);
        preparedStatement.setString(7, foto);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o produto");
        }
    }
    
    /**
     * Método utilizado para atualizar um  produto
     * 
     * @param id
     * @param nome
     * @param autor
     * @param descricao
     * @param preco
     * @param quantidade
     * @param foto
     * @throws Exception
     */
    public void atualizar(Integer id,String nome, String autor, String descricao, Double preco, Integer quantidade, String foto) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE produto SET nome=?, autor=?, descricao=?, preco=?, quantidade=?, foto=? WHERE id = ?");
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, autor);
        preparedStatement.setString(3, descricao);
        preparedStatement.setDouble(4, preco);
        preparedStatement.setInt(5, quantidade);
        preparedStatement.setString(6, foto);
        preparedStatement.setInt(7, id);
        
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar o produto");
        }
    }
    
    /**
     * Método utilizado para exclur um  produto
     * 
     * @param id
     * @throws Exception
     */
    public void excluir(Integer id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível excluir o produto");
        }
    }
}
