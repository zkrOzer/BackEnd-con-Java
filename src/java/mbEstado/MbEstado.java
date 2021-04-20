package mbEstado;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
import mbEstado.DAOEstado.DAOEstado;
import mbEstado.dominio.Estado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author clog10
 */
@Named(value = "mbEstado")
@SessionScoped
public class MbEstado implements Serializable {

    /**
     * Creates a new instance of MbEstado
     */
    private ArrayList<SelectItem> lst = null;
    private ArrayList<Estado> lstEstado;
    //private ArrayList<Estado> lstEstadoPais ;
    private Estado seleccion;
    private Estado estadoNuevo;

    public MbEstado() {
        estadoNuevo = new Estado();
        seleccion = new Estado();
    }

    public ArrayList<SelectItem> getLst() {
        return lst;
    }

    public void setLst(ArrayList<SelectItem> lst) {
        this.lst = lst;
    }

    public ArrayList<Estado> getLstEstado() {
        DAOEstado dao = new DAOEstado();
        if (lstEstado == null) {
            try {
                lstEstado = new ArrayList<>();
                for (Estado estado : dao.dameEstado()) {
                    lstEstado.add(estado);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MbEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstEstado;
    }

    public ArrayList<Estado> getLstEstadoPais() {
        DAOEstado dao = new DAOEstado();
        if (lstEstado == null) {
            try {
                lstEstado = new ArrayList<>();

                for (Estado estado : dao.dameEstadoPais2()) {
                    lstEstado.add(estado);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MbEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstEstado;
    }

    public void setLstEstado(ArrayList<Estado> lstEstado) {
        this.lstEstado = lstEstado;
    }

    public Estado getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Estado seleccion) {
        this.seleccion = seleccion;
    }

    public Estado getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(Estado estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public void cargarEstado(int idpais) {
        DAOEstado dao = new DAOEstado();
        lst = new ArrayList<>();
        try {

            for (Estado estado : dao.dameEstado(idpais)) {
                SelectItem s = new SelectItem(estado.getIdestado(), estado.getEstado());
                System.out.println("estado:" + estado.getEstado());
                lst.add(s);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MbEstado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imprimirEstado() {
        estadoNuevo = seleccion;
        System.out.println(seleccion.getIdpais());
        System.out.println(seleccion.getIdestado());
        System.out.println(seleccion.getEstado());
    }

    public void limpiarEstado() {
        lstEstado = null;
        estadoNuevo = new Estado();
        seleccion = null;
    }

    public void guardarEstado() {
        DAOEstado dao = new DAOEstado();
        if (seleccion == null) {
            try {
                dao.guardarEstado(estadoNuevo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Nuevo estado agregado exitosamente"));
            } catch (SQLException ex) {
                System.out.println("Error al insertar estado");
                Logger.getLogger(MbEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                dao.actualizarEstado(estadoNuevo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Estado actualizado exitosamente"));
            } catch (SQLException ex) {
                System.out.println("Error al actualizar estado");
                Logger.getLogger(MbEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstEstado = null;
        estadoNuevo = new Estado();
        seleccion = null;
    }
}
