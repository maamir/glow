package uk.co.slc.component.pane;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aamirm
 *
 */
public class QuestionFlow implements Flow<IComponentPane> {

	private List<IComponentPane> questions;
	private int questionIndex = -1;
	
	private QuestionFlow(List<IComponentPane> questionHolder) {
		this.questions = questionHolder;
	}
	
	/*
	 * (non-Javadoc)
	 * @see uk.co.slc.component.pane.Flow#getNextQuestion()
	 */
	@Override
	public IComponentPane getNextQuestion() {
		questionIndex++;
		return questions.get(questionIndex);
	}
	
	/*
	 * (non-Javadoc)
	 * @see uk.co.slc.component.pane.Flow#getPreviousQuestion()
	 */
	@Override
	public IComponentPane getPreviousQuestion() {
		if(questionIndex > 0) {
			questionIndex--;
			return questions.get(questionIndex);
		}
		return null;
	}
	
	public static class FlowBuilder {
		private ComponentFactory componentCreator = ComponentFactory.getInstance();
		private Map<String, IComponentPane> questionHolder = new LinkedHashMap<String, IComponentPane>();
		
		public FlowBuilder(String id, Object objectModel, String propertyName) {
			revampComponents();
			add(id, componentCreator.createComponent(objectModel, propertyName));
		}
		
		public FlowBuilder addNextquestion(String id, Object objectModel, String propertyName) {
			add(id, componentCreator.createComponent(objectModel, propertyName));
			return this;
		}
		
		private void revampComponents() {
			if(!componentCreator.getComponentPaneList().isEmpty()) {
				componentCreator.getComponentPaneList().clear();
			}
		}
		
		private void add(String id, IComponentPane question) {
			questionHolder.put(id, question);
		}
		
		public QuestionFlow build() {
			List<IComponentPane> questions = new ArrayList<IComponentPane>(questionHolder.values());
			return new QuestionFlow(questions);
		}
	}
}
