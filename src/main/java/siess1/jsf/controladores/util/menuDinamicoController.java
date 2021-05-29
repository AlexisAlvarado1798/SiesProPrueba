/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jsf.controladores.util;

import siess.dto.menu.ItemMenu;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.inject.Named;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;
import siess1.ejb.fachadas.AccionpermitidaFacade;

@Named("menuDinamicoController")
@SessionScoped
public class menuDinamicoController implements Serializable{
    @EJB
    private AccionpermitidaFacade oAcciopermitidaFacade;
    private Menubar model;
    private Session oSession;
    
    private void createModel(){
        
    }
    public Session getoSession(){
        return oSession;
    }
    
    public Menubar getModel() throws IOException{
        BigDecimal idUsuario = null;
        oSession= new Session();
        if(oSession.obtenerUsuarioLogueado() != null){
            idUsuario=oSession.obtenerUsuarioLogueado().getPkUsuario();
        }
        if (idUsuario != null){
            List<ItemMenu> listaAcciones =
                    oAcciopermitidaFacade.obtenerArbolAPPorUsuario(idUsuario);
            int level;
            Menubar tempMenu = new Menubar();
            UIComponent hijo = tempMenu;
            for(ItemMenu accionpermitida : listaAcciones){
                level = accionpermitida.getLevel();
                if(level==1){
                    hijo= tempMenu;
                }else{
                    hijo=ObtenerUltimoHijo(tempMenu);
                }
                while(level > 2){
                    hijo=ObtenerUltimoHijo(hijo);
                    level--;
                }
                hijo.getChildren().add(CrearItemMenu(accionpermitida));
            }
            this.model=tempMenu;
        }
        return model;
    }
    
    public UIComponent ObtenerUltimoHijo(UIComponent menu){
        int tamanio = menu.getChildren().size();
        if (tamanio == 0){
            return menu;
        }else{
            UIComponent hijo = menu.getChildren().get(tamanio - 1);
            return hijo;
        }
    }
    
    public UIComponent CrearItemMenu(ItemMenu accion){
        String descripcion = accion.getDescripcion();
        String ruta = accion.getRuta();
        String nombreProyecto = "siessPro-1.0.0/";
        String rutaCompleta = nombreProyecto.concat(ruta);
        if (ruta == null || ruta.equals("") || ruta.equals("#") ){
            UISubmenu padreItem = new UISubmenu();
            padreItem.setLabel(descripcion);
            padreItem.setStyle("color: #036fab; font-size: 10pt; font-weight: bold;");
            return padreItem;
        }else{
            UIMenuItem hijo = new UIMenuItem();
            hijo.setValue(descripcion);
            hijo.setUrl(ruta);
            hijo.setStyle("color: #036fab; font-size: 10pt; font-weight: bold;");
            return hijo;
        }
    }
    public void setModel(Menubar model){
        this.model=model;
    }
    public menuDinamicoController() throws IOException{
        createModel();
    }

}
