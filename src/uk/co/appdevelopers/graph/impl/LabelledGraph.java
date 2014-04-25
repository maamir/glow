package uk.co.appdevelopers.graph.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author aamirm
 * 
 */
public class LabelledGraph implements Graph<Node, Edge> {

	/** The Constant serialVersionUID **/
	private static final long serialVersionUID = 5788179365350560969L;

	private Map<Node, LinkedList<Edge>> edgeLabelledGraph;

	public LabelledGraph() {
		this.edgeLabelledGraph = new HashMap<Node, LinkedList<Edge>>();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.slc.graph.impl.Graph#add(java.lang.Object)
	 */
	@Override
	public void add(Node node) {
		edgeLabelledGraph.put(node, new LinkedList<Edge>());
	}

	public void add(Node start, Node end, String label) {
		add(end);
		Edge edge = buildEdge(start, end, label);
		edgeLabelledGraph.get(start).add(edge);
	}
	
	/**
	 * Return {@link Node} with id.
	 * 
	 * @param nodeId
	 * @return
	 */
	public Node getNode(String nodeId) {
		for(Entry<Node, LinkedList<Edge>> entry : edgeLabelledGraph.entrySet()) {
			Node node = entry.getKey();
			if(node.getId().equals(nodeId)) {
				return node;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.slc.graph.impl.Graph#get(java.lang.Object)
	 */
	@Override
	public Edge get(Node node) {
		List<Edge> edges = edgeLabelledGraph.get(node);

		if (edges.size() > 1) {
			throw new RuntimeException(
					"More than 1 records found, Please specify label");
		}

		return edges.get(0);
	}

	public Edge get(Node node, String label) {
		List<Edge> edges = edgeLabelledGraph.get(node);

		Edge keyCriteria = buildEdgeCriterion(node, label);
		int index = edges.indexOf(keyCriteria);
		Edge edge = edges.get(index);
		return edge;
	}

	private Edge buildEdgeCriterion(Node node, String label) {
		return new Edge(node.getId() + "-" + label, label);
	}

	private Edge buildEdge(Node start, Node end, String label) {
		String edgeId = end.getId();

		Edge edge = new Edge(edgeId, label);
		return edge;
	}
}
