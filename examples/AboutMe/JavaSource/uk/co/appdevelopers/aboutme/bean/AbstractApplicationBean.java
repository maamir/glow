package uk.co.appdevelopers.aboutme.bean;

import javax.annotation.PostConstruct;

import uk.co.appdevelopers.aboutme.flow.exception.FlowInitialisationException;
import uk.co.slc.component.pane.Flow;
import uk.co.slc.component.pane.IComponentPane;
import uk.co.slc.component.pane.QuestionFlow;

/**
 * File: AbstractApplicationBean.java
 * 
 * Using GLOW (Question Framework) - Component based expand and collapse accordian style, provide
 * specific implmention using {@link QuestionFlow}, in which components acts as
 * questions.
 * 
 * @author aamirm
 *
 */
public abstract class AbstractApplicationBean<T> {

	/** The question flow  **/
	private Flow<IComponentPane> flow;
	
	/** The Model (T) - representing entities of DTO's **/
	private T model;
	
	/**
	 * Create flow and perform validation.
	 */
	@PostConstruct
	protected void initBean() {
		flow = createFlow();
		postCreateValidation();
	}
	
	/**
	 * Validate if flow is not created, hault further processing.
	 */
	protected void postCreateValidation() {
		if(flow == null) {
			throw new FlowInitialisationException("Cannot initialised flow, try creating flow before initialisation.");
		}
	}
	
	/**
	 * {@link QuestionFlow#getNextQuestion()}
	 * 
	 * @return
	 */
	protected IComponentPane getNextQuestion(){
		return flow.getNextQuestion();
	}
	
	/**
	 * The flow {@link QuestionFlow}, created by imeplementing entity.
	 * 
	 * @return
	 */
	public abstract Flow<IComponentPane> createFlow();
	
	/**
	 * @return the flow
	 */
	public Flow<IComponentPane> getFlow() {
		return flow;
	}

	/**
	 * @return the model
	 */
	public T getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(T model) {
		this.model = model;
	}
}
