package mx.ipn.escom.ema.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOcss;
import mx.ipn.escom.ema.model.resources.DAO.impl.CSSResourcesDAOimpl;
import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public class CSSResourceServiceimpl implements CSSResourceService{
	
	
	/*Agregar css*/
	  public void addCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO){
	  Date date = new Date();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  CSSResources css = new CSSResources();
	  css.setName(cssTO.getName());
	  css.setDate(date);
	  ResourceDAOcss resourceDAOcss = new CSSResourcesDAOimpl();
	  resourceDAOcss.existingCSSinProject(css, project, user);
	  }
	
	/*Eliminar css*/
	  public void deleteCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO){
	  Date date = new Date();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  CSSResources css = new CSSResources();
	  css.setName(cssTO.getName());
	  ResourceDAOcss resourceDAOcss = new CSSResourcesDAOimpl();
	   resourceDAOcss.deleteResourceCSS(css, project, user);
	  }
	
	/*
	 * Mostrar recursos css de proyecto*/
	 public List<CSSResourceTO> showCSSResources(ProjectsTO projectTO, UsersTO userTO){
	  ResourceDAOcss cssDAO = new CSSResourcesDAOimpl();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  List<CSSResourceTO> listCSSTO = new ArrayList<CSSResourceTO>();
	  List<CSSResources> listResources = new ArrayList<CSSResources>();
	  CSSResourceTO cssTO = null;
	  listResources = cssDAO.showCSSResourcesFromProject(project, user);
	  	for(int i =0; i<listResources.size(); i++){
	  	CSSResources css = listResources.get(i);
	  	cssTO.setName(css.getName());
	  	cssTO.setCode(css.getCode());
	  	cssTO.setDate(css.getDate());
	  	listCSSTO.add(cssTO);
	  	}
	  return listCSSTO;
	  }

	
	/*Actualizar css*/
	  public void updateCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO, String newCode){
	  ResourceDAOcss cssDAO = new CSSResourcesDAOimpl();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  CSSResources css = new CSSResources();
	  css.setName(cssTO.getName());
	  cssDAO.updateResourceCSS(css, project, user, newCode);
	  }

	public void addStyleSheet(CSSResourceTO cssTO, ProjectsTO projectTO,
			UsersTO userTO) {
		  Date date = new Date();
		  Users user = new Users();
		  user.setUser(userTO.getUser());
		  Projects project = new Projects();
		  project.setName(projectTO.getName());
		  CSSResources css = new CSSResources();
		  css.setName(cssTO.getName());
		  css.setDate(date);
		  ResourceDAOcss resourceDAOcss = new CSSResourcesDAOimpl();
		  resourceDAOcss.addResourceCSStoProject(css, project, user);
		
	}


	 
}
