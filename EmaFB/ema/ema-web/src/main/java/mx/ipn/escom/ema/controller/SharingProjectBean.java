

package mx.ipn.escom.ema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



/**
 *
 * @author Alejandra
 */
@ManagedBean
@SessionScoped
public class SharingProjectBean implements Serializable {

/*    
    private static final long serialVersionUID = 2264980296154279701L;

private String NameProject;
private String NameResourceHTML;

public String getNameResourceHTML() {
    return NameResourceHTML;
}

public void setNameResourceHTML(String nameResourceHTML) {
    NameResourceHTML = nameResourceHTML;
}

public static long getSerialversionuid() {
    return serialVersionUID;
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
*/
  //  public class SharingProjectBean implements Serializable {

    
    private static final long serialVersionUID = 2264980296154279701L;

    
    private String project;
    private String txt;
    
    public String getProject() {
        return project;
    }


    public void setProject(String project) {
        this.project = project;
    }


    public String getTxt() {
        return txt;
    }


    public void setTxt(String txt) {
        this.txt = txt;
    }

    
   public List<String> getProjects(){
        List<String>  values = new ArrayList<String>();
        
            values.add("Alejandra");
            values.add("elva");
        
            return values;
   }
}
