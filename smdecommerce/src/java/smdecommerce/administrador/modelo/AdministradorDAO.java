
package smdecommerce.administrador.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vitor
 */
public class AdministradorDAO {
    
    /**
     * Método utilizado para obter um administrador pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Administrador obter(int id) throws Exception {
        Administrador adm = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id FROM administrador WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            adm = new Administrador();
            adm.setId(resultSet.getInt("id"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (adm == null) {
            throw new Exception("Administrador não encontrado");
        }
        return adm;
    }
    
    /**
     * Método utilizado para inserir um novo administrador
     *
     * @param id
     * @throws Exception
     */
    public void inserir(String id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO administrador (id) VALUES (?)");
        preparedStatement.setString(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o administrador");
        }
    }
    
    /**
     * Método utilizado para excluir um  administrador
     * 
     * @param id
     * @throws Exception
     */
    public void excluir(Integer id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMD_Ecommerce", "postgres", "Smd3152439");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM administrador WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível excluir o administrador");
        }
    }
}
