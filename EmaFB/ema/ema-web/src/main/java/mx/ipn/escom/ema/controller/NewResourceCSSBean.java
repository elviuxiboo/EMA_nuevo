package mx.ipn.escom.ema.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;



@ManagedBean
@SessionScoped


public class NewResourceCSSBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3953132193325589822L;
    
    private String Nombre_css;
    private String NameProject;

    public String getNombre_css() {
        return Nombre_css;
    }

    public void setNombre_css(String nombre_css) {
        Nombre_css = nombre_css;
    } 
    
    public String getNameProject() {
        return NameProject;
    }

    public void setNameProject(String nameProject) {
        NameProject = nameProject;
    } 
    
    public List<String> complete (String query){
        List<String>  values = new ArrayList<String>();
        
            values.add("Alejandra");
            values.add("elva");
        
            return values;
        
        
    }

}
