package mbEstado.DAOEstado;

import conexion.conexiondbms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import mbEstado.dominio.Estado;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.logging.Level;

/**
 *
 * @author clog10
 */
public class DAOEstado {

    public ArrayList<Estado> dameEstado(int idpais) throws SQLException {
        ArrayList<Estado> lst = new ArrayList<>();
        conexiondbms cn = new conexiondbms();
        Connection c = cn.conectar();

        String sql = "select * from scpais.estado where idpais = ?";
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idpais);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setIdpais(rs.getInt("idpais"));
                estado.setIdestado(rs.getInt("idestado"));
                estado.setEstado(rs.getString("estado"));
                lst.add(estado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEstado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cn.desconectar(c);
        }
        return lst;
    }

    public ArrayList<Estado> dameEstado() throws SQLException {
        ArrayList<Estado> lst = new ArrayList<>();
        conexiondbms cn = new conexiondbms();
        Connection c = cn.conectar();
        String sql = "select * from scpais.estado order by idestado asc ";
        PreparedStatement ps;

        try {
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setIdpais(rs.getInt("idpais"));
                estado.setIdestado(rs.getInt("idestado"));
                estado.setEstado(rs.getString("estado"));
                lst.add(estado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEstado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cn.desconectar(c);
        }
        return lst;
    }

    public ArrayList<Estado> dameEstadoPais2() throws SQLException {
        ArrayList<Estado> lst = new ArrayList<>();
        conexiondbms cn = new conexiondbms();
        Connection c = cn.conectar();
        String sql = "select pais.idpais, pais.pais, estado.idestado,estado.estado\n"
                + "from scpais.estado inner join scpais.pais\n"
                + "on estado.idpais=pais.idpais order by estado.idestado asc;";
        PreparedStatement ps;

        try {
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setIdpais(rs.getInt("idpais"));
                estado.setPais(rs.getString("pais"));
                estado.setIdestado(rs.getInt("idestado"));
                estado.setEstado(rs.getString("estado"));
                lst.add(estado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEstado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cn.desconectar(c);
        }
        return lst;
    }

    public Estado buscarEstado(String strEstado) throws SQLException {
        conexiondbms cn = new conexiondbms();
        Connection c = cn.conectar();
        Estado e = new Estado();
        String sql = "select * from scpais.estado where estado = ?";
        PreparedStatement ps;

        ps = c.prepareStatement(sql);
        ps.setString(1, strEstado);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            e = new Estado();
            e.setIdpais(rs.getInt("idpais"));
            e.setIdestado(rs.getInt("idestado"));
            e.setEstado(rs.getString("estado"));
        }
        cn.desconectar(c);
        return e;
    }

    public void guardarEstado(Estado estado) throws SQLException {
        conexiondbms c = new conexiondbms();
        Connection cn = c.conectar();
        PreparedStatement ps;
        String sql = "insert into scpais.estado (idpais, estado) values (?,?)";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, estado.getIdpais());
        ps.setString(2, estado.getEstado());
        ps.executeUpdate();
        c.desconectar(cn);
    }

    public void actualizarEstado(Estado estado) throws SQLException {
        conexiondbms c = new conexiondbms();
        Connection cn = c.conectar();
        PreparedStatement ps;
        String sql = "update scpais.estado set idpais=?, estado=? where idestado=?";

        ps = cn.prepareStatement(sql);
        ps.setInt(1, estado.getIdpais());
        ps.setString(2, estado.getEstado());
        ps.setInt(3, estado.getIdestado());

        ps.executeUpdate();
        c.desconectar(cn);

    }
}
