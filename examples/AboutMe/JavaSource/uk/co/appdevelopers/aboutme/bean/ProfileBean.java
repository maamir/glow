package uk.co.appdevelopers.aboutme.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import uk.co.slc.component.pane.IComponentPane;
import uk.co.slc.component.pane.QuestionFlow;
import uk.co.slc.main.Customer;

/**
 * File: ProfileBean.java
 * 
 * @author aamirm
 *
 */
@ManagedBean
@ViewScoped
public class ProfileBean extends AbstractApplicationBean<Customer> {
	
	//Questions
	private IComponentPane q1;
	private IComponentPane q2;
	private IComponentPane q3;
	
	/*
	 * (non-Javadoc)
	 * @see uk.co.appdevelopers.aboutme.bean.AbstractApplicationBean#createFlow()
	 */
	@Override
	public QuestionFlow createFlow() {
		setModel(new Customer());
		QuestionFlow flow = new QuestionFlow.FlowBuilder("q1", getModel(), "customerName")
							    .addNextquestion("q2", getModel(), "userName")
							    .addNextquestion("q3", getModel(), "age")
							    .build();
		return flow;
	}
	
	public IComponentPane getQ1() {
		if(q1 == null) {
			q1 = getNextQuestion();
		}
		return q1;
	}
	
	public IComponentPane getQ2() {
		if(q2 == null) {
			q2 = getNextQuestion();
		}
		return q2;
	}
	
	public IComponentPane getQ3() {
		if(q3 == null) {
			q3 = getNextQuestion();
		}
		return q3;
	}
}
