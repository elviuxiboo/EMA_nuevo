package mx.ipn.escom.ema.model.persistence;

import mx.ipn.escom.ema.model.attributes.DAO.impl.AttributesDAOimpl;
import mx.ipn.escom.ema.model.tags.DAO.impl.TagsDAOimpl;
import mx.ipn.escom.ema.model.attributes.DAO.AttributesDAO;
import mx.ipn.escom.ema.model.tags.DAO.TagsDAO;
import mx.ipn.escom.ema.model.entities.Attributes;
import mx.ipn.escom.ema.model.entities.Tags;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFService {
    private static EntityManagerFactory emfInstance;
 
    private EMFService() {
    }
 
    public static EntityManagerFactory get() {
	if(emfInstance == null){
	   emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
	   EMFService.iniData();
	}
        return emfInstance;
    }

    private static void iniData(){
	Attributes attribute = new Attributes();
		AttributesDAO adi = new AttributesDAOimpl();
		attribute.setName("href");
		attribute.setDescription("Documento base del URL");
		adi.addAttribute(attribute);
		Attributes attribute2 = new Attributes();
		attribute2.setName("abbr");
		attribute2.setDescription("Abreviatura de celda de cabecera");
		adi.addAttribute(attribute2);

	 Tags tag = new Tags();
		TagsDAO tdi = new TagsDAOimpl();
		tag.setName("html");
		tag.setDescription("Raiz del documento HTML O XHTML. Todos los demas elementos deben estar debajo de este elemento");
		tdi.addTag(tag);
	/*	
		tdi.addAttributeToTag("html", "href");
		adi.addTagToAttribute("html", "href");*/
    }

}
