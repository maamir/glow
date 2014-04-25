package uk.co.slc.component.pane;

import java.util.LinkedList;

import uk.co.slc.component.model.ComponentModel;
import uk.co.slc.component.model.ComponentModel.State;

/**
 * File: ComponentFactory.java
 * 
 * @author aamirm
 *
 */
public class ComponentFactory {

	/** static Instance of {@link ComponentFactory} **/
	private static ComponentFactory factory = new ComponentFactory();
	
	/** List of {@link ComponentPane} **/
	private LinkedList<ComponentPane<ComponentModel>> components;

	private ComponentFactory() {
		components = new LinkedList<ComponentPane<ComponentModel>>();
	}
	
	public static ComponentFactory getInstance() {
		return factory;
	}
	
	/**
	 * Create new component {@link ComponentPane} and add to list.
	 * 
	 * {@link ComponentModel}
	 * 
	 * @return
	 */
	public IComponentPane createComponent() {
		ComponentPane<ComponentModel> component = getComponentPane(null, null);
		postCreationComponent(component);
		
		return component;
	}
	
	/**
	 * Create new component {@link ComponentPane} and add to list.
	 * 
	 * @param objectModel
	 * @return
	 */
	public IComponentPane createComponent(Object objectModel, String propertyName){
		ComponentPane<ComponentModel> component = getComponentPane(objectModel, propertyName);
		postCreationComponent(component);
		
		return component;
	}
	
	/**
	 * Initialise component list and add first element as render able and initialise it model.  
	 *  
	 * @param component
	 */
	private void postCreationComponent(ComponentPane<ComponentModel> component) {
		ComponentModel model = new ComponentModel();
		
		if(components.isEmpty()) {
			model.setState(State.ADD);
		}
		component.setModel(model);
		
		components.add(component);
	}
	
	/**
	 * Create instance of {@link ComponentPane}
	 * @param objectModel
	 * @return
	 */
	private ComponentPane<ComponentModel> getComponentPane(Object objectModel, String propertyName) {
		ComponentPane<ComponentModel> component = new ComponentPane<ComponentModel>();
		
		if(objectModel != null) {
			component.setObjectModel(objectModel);
		}
		
		if(propertyName != null) {
			component.setPropertyName(propertyName);
		}
		
		return component;
	}
	
	/**
	 * Package protected list of components.
	 * 
	 * @return
	 */
	LinkedList<ComponentPane<ComponentModel>> getComponentPaneList() {
		return components;
	}
}
