package uk.co.appdevelopers.main;

import uk.co.appdevelopers.component.pane.IComponentPane;
import uk.co.appdevelopers.component.pane.QuestionFlow;

/**
 * @author aamirm
 *
 */
public class TestApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Customer customer = new Customer();
		QuestionFlow flow = new QuestionFlow.FlowBuilder("q1", customer,"customerName")
								.addNextquestion("q2", customer, "userName")
								.addNextquestion("q3", customer, "age")
								.build();
		
		IComponentPane q1 = flow.getNextQuestion();
		IComponentPane q2 = flow.getNextQuestion();
		IComponentPane q3 = flow.getNextQuestion();
		
		printSystem(customer, q1, q2, q3);
		
		System.out.println("=============================================");
		System.out.println(flow.getPreviousQuestion().getSubmittedValue());
		System.out.println(flow.getPreviousQuestion().getSubmittedValue());
	}

	private static void printSystem(Customer customer, IComponentPane q1, IComponentPane q2, IComponentPane q3) {
		System.out.println("Cus name:" + customer.getCustomerName());
		System.out.println(q1.isRender());
		System.out.println(q2.isRender());
		System.out.println(q3.isRender());
		
		q1.getActionListener().submitValue("aamir"); //q1.actionListener.submitValue
		
		System.out.println("*************************************************");
		System.out.println(q1.getSubmittedValue() + " State: " + q1.getState());
		System.out.println("Cus name: " + customer.getCustomerName());
		System.out.println(q2.getState());
		System.out.println(q3.isRender());
		
		System.out.println("*************************************************");
		q1.getActionListener().edit();
		System.out.println(q1.getSubmittedValue() + " State: " + q1.getState());
		System.out.println(q2.getSubmittedValue() + " State: " + q2.getState());
		System.out.println(q3.isRender());
	}

}
