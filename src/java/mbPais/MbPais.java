package mbPais;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import mbPais.DAOPais.DAOPais;
import mbPais.dominio.Pais;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import mbEstado.MbEstado;

/**
 *
 * @author clog10
 */
@Named(value = "mbPais")
@Stateless
public class MbPais implements Serializable {

    @ManagedProperty(value = "#{mbEstado}")
    private MbEstado mbEstado = new MbEstado();
    private ArrayList<SelectItem> cmb1 = null;

    private int idpais = 0;
    private Pais p = new Pais();

    public MbEstado getMbEstado() {
        return mbEstado;
    }

    public void setMbEstado(MbEstado mbEstado) {
        this.mbEstado = mbEstado;
    }

    public ArrayList<SelectItem> getCmb1() {
        if (cmb1 == null) {
            cmb1 = new ArrayList<>();
            DAOPais dao = new DAOPais();
            try {
                for (Pais p : dao.damePaises()) {
                    SelectItem s = new SelectItem(p.getIdpais(), p.getPais());
                    cmb1.add(s);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(MbPais.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cmb1;
    }

    public void setCmb1(ArrayList<SelectItem> cmb1) {
        this.cmb1 = cmb1;
    }

    public MbPais() {
    }

    public Pais getP() {
        return p;
    }

    public void setP(Pais p) {
        this.p = p;
    }

    public int getIdpais() {
        return idpais;
    }

    public void setIdpais(int idpais) {
        this.idpais = idpais;
    }

    public void buscarPais() {
        System.out.println("Hizo clic en pais");
        DAOPais dao = new DAOPais();
        try {
            System.out.println(p.getIdpais());
            Pais pais2 = dao.damePaises(p.getIdpais());
            p = pais2;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MbPais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerInformacion() {
        System.out.println(idpais);
        mbEstado.cargarEstado(idpais);

    }

}
