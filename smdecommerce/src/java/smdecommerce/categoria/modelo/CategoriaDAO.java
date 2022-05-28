package smdecommerce.categoria.modelo;

import smdecommerce.categoria.modelo.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Classe que implementa o padrão DAO para a entidade categoria
 */
public class CategoriaDAO {

    /**
     * Método utilizado para obter uma categoria pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Categoria obter(int id) throws Exception {
        Categoria categoria = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id, descricao FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricao(resultSet.getString("descricao"));
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
     * Método utilizado para inserir uma nova categoria
     *
     * @param id
     * @param descricao
     * @throws Exception
     */
    
    public void inserir( Integer id,String descricao) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO categoria (id, descricao) VALUES (?, ?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, descricao);
       
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir a categoria");
        }
    }
    
    /**
     * Método utilizado para atualizar uma  categoria
     * 
     * @param id
     * @param descricao
     * @throws java.lang.Exception
     */
    
    public void atualizar(Integer id, String descricao) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE categoria SET descricao=? WHERE id = ?");
        preparedStatement.setString(1, descricao);
        preparedStatement.setInt(2, id);
        
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar a categoria");
        }
    }
    
    /**
     * Método utilizado para excluir uma  categoria
     * 
     * @param id
     * @throws Exception
     */
    public void excluir(Integer id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível excluir a categoria");
        }
    }
}
