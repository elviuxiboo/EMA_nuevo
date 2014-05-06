package mx.ipn.escom.ema.services.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.SharedProjects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOcss;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOhtml;
import mx.ipn.escom.ema.model.resources.DAO.SharedProjectsDAO;
import mx.ipn.escom.ema.model.resources.DAO.impl.CSSResourcesDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.impl.HTMLResourcesDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.impl.SharedProjectsDAOimpl;
import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.ShareProjectsService;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public class SharedProjectsServiceimpl implements ShareProjectsService{
	
	
	
	/*Compartir Proyecto*/
	  public void shareProject(UsersTO userTO, ProjectsTO projectTO, UsersTO userReceiver){
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  	SharedProjects sharedProject = new SharedProjects();
		SharedProjectsDAOimpl srd = new SharedProjectsDAOimpl();
		SharedProjectsDAO sharedProjectsDAO = new SharedProjectsDAOimpl();
		sharedProjectsDAO.shareProject(sharedProject, project, user);
		Users userReceiveProject = new Users();
		userReceiveProject.setUser(userReceiver.getUser());
		sharedProjectsDAO.addUsersReceivers(userReceiveProject, sharedProject);
		sharedProjectsDAO.addReferenceOfProjectReceived(sharedProject, userReceiveProject);
	  }
	
	/*Mostrar proyectos compartidos*/
	  public List<ProjectsTO> showSharedProjects(UsersTO userTO){
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects projectResult = new Projects();
      ProjectsTO projectTO = null;
	  List<ProjectsTO> listSharedProjectTO = new ArrayList<ProjectsTO>(); 
	  SharedProjectsDAO sharedProjectsDAO = new SharedProjectsDAOimpl();
	  List<Projects> list = sharedProjectsDAO.showProjects(user);
	  List<CSSResourceTO> cssList = new ArrayList<CSSResourceTO>();
	  List<HTMLResourceTO> htmlList = new ArrayList<HTMLResourceTO>();
	  CSSResourceService cssService = new CSSResourceServiceimpl();
	  HTMLResourceService htmlService = new HTMLResourceServiceimpl();
	  for(int i=0; i< list.size(); i++){
	   	 Projects sharedProject = list.get(i);
	   	 projectTO.setName(sharedProject.getName());
	   	 projectTO.setUser(userTO);
	   	 cssList = cssService.showCSSResources(projectTO, userTO);
	   	 htmlList = htmlService.showHTMLResources(projectTO, userTO);
	   	 projectTO.setCss(cssList);
	   	 projectTO.setHtml(htmlList);
	   	 listSharedProjectTO.add(projectTO);
	   }
	  return listSharedProjectTO;
	  }

}
