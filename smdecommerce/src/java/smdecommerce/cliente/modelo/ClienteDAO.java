package smdecommerce.cliente.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Classe que implementa o padrão DAO para a entidade cliente
 */
public class ClienteDAO {

    /**
     * Método utilizado para obter um cliente pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Cliente obter(int id) throws Exception {
        Cliente cliente = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id_usuario, endereco FROM cliente WHERE id_usuario = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            cliente = new Cliente();
            cliente.setId(resultSet.getInt("id_usuario"));
            cliente.setEndereco(resultSet.getString("endereco"));
          
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (cliente == null) {
            throw new Exception("Cliente não encontrado");
        }
        return cliente;
    }

     /**
     * Método utilizado para inserir um novo cliente
     *
     * @param id
     * @param endereco
     * @throws Exception
     */
    public void inserir( Integer id,String endereco) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO usuario (id_usuario, endereco) VALUES (?, ?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, endereco);
       
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o cliente");
        }
    }
    
    /**
     * Método utilizado para atualizar um  cliente
     * 
     * @param id
     * @param endereco
     * @throws Exception
     */
    public void atualizar(Integer id, String endereco) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE usuario SET endereco=? WHERE id_usuario = ?");
        preparedStatement.setString(1, endereco);
        preparedStatement.setInt(2, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar o cliente");
        }
    }

    
    /**
     * Método utilizado para excluir um  cliente
     * 
     * @param id
     * @throws Exception
     */
    public void excluir(Integer id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM cliente WHERE id_usuario = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível excluir o cliente");
        }
    }
}
