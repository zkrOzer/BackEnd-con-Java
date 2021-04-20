package mbPais.DAOPais;

import conexion.conexiondbms;
import mbPais.dominio.Pais;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author clog10
 */
public class DAOPais {

    public ArrayList<Pais> damePaises() throws SQLException {
        ArrayList<Pais> lst = new ArrayList<>();
        conexiondbms cn = new conexiondbms();
        Connection c = cn.conectar();
        String sql = "Select * from scpais.pais";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Pais p = new Pais();
            p.setIdpais(rs.getInt("idpais"));
            p.setPais(rs.getString("pais"));
            lst.add(p);
        }
        cn.desconectar(c);
        return lst;
    }

    public Pais damePaises(int idpais) throws SQLException {
        Pais pais2 = new Pais();
        String sql = "Select * from scpais.pais where idpais = ?";
        conexiondbms cn = new conexiondbms();
        Connection c = cn.conectar();
        PreparedStatement ps;
        ps = c.prepareStatement(sql);
        ps.setInt(1, idpais);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pais2.setIdpais(rs.getInt("idpais"));
            pais2.setPais(rs.getString("pais"));
        }
        cn.desconectar(c);
        return pais2;
    }

}
