package smdecommerce.produto_categoria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Classe que implementa o padrão DAO para a entidade produto_categoria
 */
public class Produto_CategoriaDAO {

    /**
     * Método utilizado para obter a categoria de um produto
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Integer obterCategoria(int id) throws Exception {
        Integer categoria = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id_categoria FROM produto_categoria WHERE id_produto = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = resultSet.getInt("id");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (categoria == null) {
            throw new Exception("Categoria não encontrada");
        }
        return categoria;
    }

    /**
     * Método utilizado para inserir um produto em uma categoria
     *
     * @param id_produto
     * @param id_categoria
     * @throws Exception
     */
    public void inserir( Integer id_produto,Integer id_categoria) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO produto_categoria (id_produto, id_categoria) VALUES (?, ?)");
        preparedStatement.setInt(1, id_produto);
        preparedStatement.setInt(2, id_categoria);

        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o produto na categoria");
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
