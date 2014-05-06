package mx.ipn.escom.ema.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOhtml;
import mx.ipn.escom.ema.model.resources.DAO.impl.HTMLResourcesDAOimpl;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public class HTMLResourceServiceimpl implements HTMLResourceService{
	
	
	/*Agregar html*/
	  public void addHTML(HTMLResourceTO htmlTO, ProjectsTO projectTO, UsersTO userTO){
      Date date = new Date();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  HTMLResources html = new HTMLResources();
	  html.setName(htmlTO.getName());
	  html.setDate(date);
	  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
	  htmlDAO.existingHTMLinProject(html, project,user);
	  }
	 
	
	/*Eliminar html*/
	  public void deleteHTML(HTMLResourceTO htmlTO, ProjectsTO projectTO, UsersTO userTO){
	  Date date = new Date();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  HTMLResources html = new HTMLResources();
	  html.setName(htmlTO.getName());
	  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
	  htmlDAO.deleteResourceHTML(html, project, user);
	  }
	  
	
	/*
	 * Mostrar htmls de proyecto*/
	  public List<HTMLResourceTO> showHTMLResources(ProjectsTO projectTO, UsersTO userTO){
	  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  List<HTMLResourceTO> listHTMLTO = new ArrayList<HTMLResourceTO>();
	  List<HTMLResources> listResources = new ArrayList<HTMLResources>();
	  HTMLResourceTO htmlTO = null;
	  listResources = htmlDAO.showHTMLResourcesFromProject(project, user);
	  	for(int i =0; i<listResources.size(); i++){
	  	HTMLResources html = listResources.get(i);
	  	htmlTO.setName(html.getName());
	  	htmlTO.setCode(html.getCode());
	  	htmlTO.setDate(html.getDate());
	  	listHTMLTO.add(htmlTO);
	  	}
	  return listHTMLTO;
	  }
	 

/*	Actualizar html*/
	  public void updateHTML(HTMLResourceTO htmlTO, ProjectsTO projectTO, UsersTO userTO, String newCode){
		ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();	
		Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  HTMLResources html = new HTMLResources();
	  html.setName(htmlTO.getName());
	  htmlDAO.updateResourceHTML(html, project, user, newCode);
	  }


	public void addIndex(HTMLResourceTO htmlTO, ProjectsTO projectTO,
			UsersTO userTO) {
		Date date = new Date();
		  Users user = new Users();
		  user.setUser(userTO.getUser());
		  Projects project = new Projects();
		  project.setName(projectTO.getName());
		  HTMLResources html = new HTMLResources();
		  html.setName(htmlTO.getName());
		  html.setDate(date);
		  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
		  htmlDAO.addResourceHTMLtoProject(html, project, user);
		
	}
	 
	
}
