package mx.ipn.escom.ema.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOcss;
import mx.ipn.escom.ema.model.resources.DAO.impl.CSSResourcesDAOimpl;
import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.ProjectsService;
import mx.ipn.escom.ema.services.impl.CSSResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.ProjectServiceimpl;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@ManagedBean
@ViewScoped


public class TreeBean implements Serializable {

    private static final long serialVersionUID = 2417620239014385855L;
    private TreeNode root;
    private TreeNode selectedNode;
    private ProjectsService projectService = new ProjectServiceimpl();
    private HTMLResourceService htmlService = new HTMLResourceServiceimpl();
    private CSSResourceService cssService = new CSSResourceServiceimpl();
    private UsersTO userTO = new UsersTO();
    private List<ProjectsTO> listProjects = new ArrayList<ProjectsTO>();
    private List<HTMLResourceTO> listHtml = new ArrayList<HTMLResourceTO>();
    private List<CSSResourceTO> listCss = new ArrayList<CSSResourceTO>();
    private ResourceDAOcss cssDAO = new CSSResourcesDAOimpl();
    private List<CSSResources> cssList = new ArrayList<CSSResources>();

    /**
     * Default constructor
     */
    public TreeBean() {
        root = new DefaultTreeNode("Root", null);
/*        TreeNode node0 = new TreeNodeImplement("Segment 0", root);
        TreeNode node1 = new TreeNodeImplement("Segment 1", root);
        TreeNode node2 = new TreeNodeImplement("Segment 2", root);
        TreeNode node00 = new TreeNodeImplement("Segment 0.0", node0);
        TreeNode node01 = new TreeNodeImplement("Segment 0.1", node0);
        TreeNode node10 = new TreeNodeImplement("Segment 1.0", node1);
        TreeNode node11 = new TreeNodeImplement("Segment 1.1", node1);
        TreeNode node000 = new TreeNodeImplement("Segment 0.0.0", node00);
        TreeNode node001 = new TreeNodeImplement("Segment 0.0.1", node00);
        TreeNode node010 = new TreeNodeImplement("Segment 0.1.0", node01);
        TreeNode node100 = new TreeNodeImplement("Segment 1.0.0", node10);*/
       UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google
	    userTO.setUser(user.getEmail());
//        userTO.setUser("andrea@example.com");
        listProjects = projectService.showProjects(userTO);
        for(int i=0; i<listProjects.size(); i++){
        	ProjectsTO project = listProjects.get(i);
        	TreeNode node1 = new DefaultTreeNode(project.getName(), root);
        	listHtml = htmlService.showHTMLResources(project, userTO);
        	for(int j=0; j<listHtml.size(); j++){
        		HTMLResourceTO html = listHtml.get(j);
        		TreeNode node11 = new DefaultTreeNode(html.getName(), node1);
        	}
        	listCss = cssService.showCSSResources(project, userTO);
        	for(int k=0; k<listCss.size();k++){
        		//System.out.println(listCss.get(k));
        		CSSResourceTO css = listCss.get(k);
        		TreeNode node12 = new DefaultTreeNode(css.getName(), node1);
        	}

        	
        	
        }
    }
    

    /**
     * This method returns the tree model based on the root node.
     *
     * @return root node.
     */
    public TreeNode getModel() {
        return root;
    }

    /**
     * Gets the selected node in the tree.
     *
     * @return selected node in tree.
     */
    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    /**
     * Sets the selected node in the tree.
     *
     * @param selectedNode node to be set as selected.
     */
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    /**
     * {@inheritDoc }
     *
     * Adds a {@link javax.faces.application.FacesMessage} with event data to
     * the {@link javax.faces.context.FacesContext}.
     */
    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("NodeSelectEvent Fired");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getId(), msg);
    }

    /**
     * {@inheritDoc}
     *
     * Adds a {@link javax.faces.application.FacesMessage} with event data to
     * the {@link javax.faces.context.FacesContext}.
     */
    public void onNodeExpand(NodeExpandEvent event) {

        System.out.println("NodeExpandEvent Fired");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getId(), msg);
    }

    /**
     * {@inheritDoc}
     *
     * Adds a {@link javax.faces.application.FacesMessage} with event data to
     * the {@link javax.faces.context.FacesContext}.
     * @param event
     */
    public void onNodeCollapse(NodeCollapseEvent event) {
        System.out.println("NodeCollapseEvent Fired");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getId(), msg);
    }
}
