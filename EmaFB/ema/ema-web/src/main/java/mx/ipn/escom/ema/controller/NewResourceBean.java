package mx.ipn.escom.ema.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.projects.DAO.ProjectsDAO;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.ProjectsService;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.ProjectServiceimpl;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;



@ManagedBean(name="htmlResource")
@SessionScoped
public class NewResourceBean implements Serializable {

    /**
     * 
     */
	private Date date = new Date();
	private HTMLResourceTO html = new HTMLResourceTO();
	private HTMLResourceService htmlService = new HTMLResourceServiceimpl();
	private ProjectsTO projectTO = new ProjectsTO();
	private ProjectsService projectService = new ProjectServiceimpl();
	private UsersTO userTO = new UsersTO();
	private List<String> projectList = new ArrayList<String>();
	
    private static final long serialVersionUID = 2617813082214149080L;
    
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
    
 /*   public List<String> complete (String query){
        List<String>  values = new ArrayList<String>();
        
            values.add("Alejandra");
            values.add("elva");
        
            return values;
        
        
    }*/
    
    public List<String> complete(String query){
    	UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google*/
	    userTO.setUser(user.getEmail());
    	//Users user = new Users();
    	System.out.println("antes de set to");
    	//userTO.setUser("andrea@example.com");
    	System.out.println("antes de la lista " + userTO);
    	List<ProjectsTO> listProjects = projectService.showProjects(userTO);
    	System.out.println(listProjects);
    	//userTO.setUser("andrea@example.com");
    	/*for(ProjectsTO projectResult: listProjects){
    		if(projectResult.getName().startsWith(query)){
    			projectList.add(projectResult.getName());
    		}
    	}*/
    	for(int i =0; i<listProjects.size(); i++){
    		ProjectsTO projectResult = listProjects.get(i);
    		projectList.add(projectResult.getName());
    	}
    	return projectList;
    }
    
    public HTMLResourceTO createHTML(){
    	System.out.println("metodo");
    	FacesContext context = FacesContext.getCurrentInstance();
    	UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google*/
	//    userTO.setUser("andrea@example.com");
	    userTO.setUser(user.getEmail());
	   // NameProject = context.getExternalContext().getRequestParameterMap().get("html:force_proyect");
	    System.out.println(NameProject);
	    NameResourceHTML = context.getExternalContext().getRequestParameterMap().get("html:regex");
	    System.out.println(NameResourceHTML);
	    projectTO.setName(NameProject);
	    html.setName(NameResourceHTML);
	    html.setDate(date);
	    htmlService.addHTML(html, projectTO, userTO);
	    return html;
    }

}
