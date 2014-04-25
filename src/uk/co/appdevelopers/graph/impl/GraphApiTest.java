package uk.co.appdevelopers.graph.impl;

/**
 * @author aamirm
 *
 */
public class GraphApiTest {
	static LabelledGraph questions = new LabelledGraph();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node node1 = new Node("q1", "Question 1");
		
		questions.add(node1);
		
		Node node2 = new Node("q2", "Question 2");
		Node node3 = new Node("q3", "Question 3");
		
		questions.add(node1, node2, "y");
		questions.add(node1, node3, "n");
		
		questions.add(node2,node3,"x");
		
		Edge edge1 =questions.get(node1, "y");
		Edge edge2 =questions.get(node2, "x");
		
		edge1.getId();
		edge2.getId();
		
		/*************************************
		 * 		Questions wrapper            *
		 ************************************/
		
		Question q1 = new Question("q1", "q1");
		Question q2 = new Question("q2", "q2");
		Question q3 = new Question("q3", "q3");
		
		q1.addNextQuestion(q2, "y");
		q1.addNextQuestion(q3, "n");
		
		Question nextQuestionId = q1.getQuestion("n");
		
		System.out.println(nextQuestionId.getQuestionId());
	}

}
