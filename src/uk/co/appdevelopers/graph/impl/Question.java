package uk.co.appdevelopers.graph.impl;

/**
 * @author aamirm
 *
 */
public class Question {
	private LabelledGraph questions = new LabelledGraph();
	private Node node;
	
	//Questions data
	private String questionId;
	private Object questionData;
	
	public Question(String questionId, String questionDescription) {
		node = new Node(questionId, questionDescription);
		questions.add(node);
	}
	
	public Question(String questionId, Object questionData) {
		this.questionId = questionId;
		this.questionData = questionData;
	}
	
	public void addNextQuestion(Question n, String value) {
		questions.add(this.node, n.node, value);
	}
	
	public Question getQuestion(String label) {
		Edge edge = questions.get(this.node, label);
		Node node = questions.getNode(edge.getId());
		
		return new Question(node.getId(), node.getName());
	}

	/**
	 * @return the questionId
	 */
	public String getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the questionData
	 */
	public Object getQuestionData() {
		return questionData;
	}

	/**
	 * @param questionData the questionData to set
	 */
	public void setQuestionData(Object questionData) {
		this.questionData = questionData;
	}	
}
