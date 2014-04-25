package uk.co.appdevelopers.component.pane;

import java.util.LinkedList;

import org.apache.commons.beanutils.BeanUtils;

import uk.co.appdevelopers.component.model.ComponentModel;
import uk.co.appdevelopers.component.model.ComponentModel.State;
import uk.co.appdevelopers.component.pane.listener.ComponentPaneActionListener;


public class ComponentPane<T> implements IComponentPane, ComponentPaneActionListener {
	
	/** The {@link ComponentModel} **/
	private ComponentModel model;
	
	/** The model from UI **/
	private Object objectModel;
	
	/** property to set value in objectModel **/
	private String propertyName;

	/**
	 * @return the model
	 */
	public ComponentModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(ComponentModel model) {
		this.model = model;
	}
	
	/**
	 * @return the objectModel
	 */
	public Object getObjectModel() {
		return objectModel;
	}

	/**
	 * @param objectModel the objectModel to set
	 */
	public void setObjectModel(Object objectModel) {
		this.objectModel = objectModel;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.cl.slc.component.pane.IComponentPane#isRender()
	 */
	@Override
	public boolean isRender() {
		return this.model.getState().equals(State.ADD) || this.model.getState().equals(State.EDIT);
	}
	
	/*
	 * (non-Javadoc)
	 * @see uk.cl.slc.component.pane.listener.ComponentPaneActionListener#submitValue(java.lang.Object)
	 */
	@Override
	public void submitValue(Object value) {
		this.model.setState(State.SUBMIT);
		this.model.setValue(value);
		
		processObjectModel();
		processNextComponent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see uk.cl.slc.component.pane.listener.ComponentPaneActionListener#edit()
	 */
	@Override
	public void edit() {
		processCollapse();
		this.model.setState(State.EDIT);
	}
	
	/*
	 * (non-Javadoc)
	 * @see uk.cl.slc.component.pane.IComponentPane#getSubmittedValue()
	 */
	@Override
	public Object getSubmittedValue() {
		return this.model.getValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see uk.cl.slc.component.pane.IComponentPane#getState()
	 */
	@Override
	public String getState() {
		return this.model.getState().toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see uk.cl.slc.component.pane.IComponentPane#getListener()
	 */
	@Override
	public ComponentPaneActionListener getActionListener() {
		return this;
	}
	
	/**
	 * Set value of object pass into it.
	 */
	private void processObjectModel() {
		if(objectModel != null) {
			try {
				BeanUtils.copyProperty(objectModel, this.propertyName, this.model.getValue());
			} catch (Exception e) {
				throw new RuntimeException("processObjectModel : Unable to copy property");
			}
		}
	}
	
	/**
	 * Allow render next question in a list.
	 */
	private void processNextComponent() {
		LinkedList<ComponentPane<ComponentModel>> components = ComponentFactory.getInstance().getComponentPaneList();
		ComponentPane<ComponentModel> nextComponent = null;
		int currentIndex = components.indexOf(this);
		
		int nextIndex = currentIndex + 1;
		
		if(nextIndex < components.size()) {
			nextComponent = components.get(nextIndex);
			if(nextComponent.model.isSubmitted()) {
				nextComponent.model.setState(State.ADD);
			}
		}
		
		if(nextComponent != null && !nextComponent.model.getState().equals(State.ADD)) {
			processNewComponents();
		}
	}
	
	/**
	 * Open next unanswered question with in components.
	 */
	private void processNewComponents() {
		LinkedList<ComponentPane<ComponentModel>> components = ComponentFactory.getInstance().getComponentPaneList();
				
		for (ComponentPane<ComponentModel> component : components) {
			if (component.model.getState().equals(State.NEW)) {
				component.model.setState(State.ADD);
			}
		}
	}

	/**
	 * Collapse all components by setting state to {@link State#SUBMIT}.
	 */
	private void processCollapse() {
		LinkedList<ComponentPane<ComponentModel>> components = ComponentFactory.getInstance().getComponentPaneList();
		
		for (ComponentPane<ComponentModel> component : components) {
			State newState = component.model.hasValue() ? State.SUBMIT : State.NEW;
			component.model.setState(newState);
		}
	}
}
