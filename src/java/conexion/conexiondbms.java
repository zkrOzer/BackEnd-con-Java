
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author clog10
 */
public class conexiondbms {
    private String host ="localhost";
    private String puerto = "5432";
    private String baseDatos ="dbpaises";
    public String servidor ="jdbc:postgresql://" + host + ":" + puerto + "/" + baseDatos;
    public String usuario = "postgres";
    public String clave = "123456";
    
    //Registrar el drive
    
    public Connection conectar(){
        Connection conexion = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        //Establecer la conexion
        try {
            conexion = DriverManager.getConnection(servidor,usuario,clave);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return conexion;
    }
    
    public void desconectar (Connection conexion) throws SQLException{
        conexion.close();
    }
    
}
