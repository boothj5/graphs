package graphs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;

public class RoleMappingProcessor {

	private static final String NODE_TYPE = "type";
	private static final String NODE_TYPE_INTERIM = "interim";
	private static final String NODE_TYPE_OR = "or";
	private static final String NODE_TYPE_AND = "and";
	private static final String NODE_TYPE_PROPERTY = "property";

	private String intRulesFile;
	private Graph intRulesGraph;
	private Set<String> ints;
	private Set<String> props;
	
	private Set<String> passedNodes;
	private Set<String> failedNodes;
	
	public RoleMappingProcessor(String intRulesFile, String roleRulesFile) {
		this.intRulesFile = intRulesFile;
		intRulesGraph = new DefaultGraph("intRules");
		passedNodes = new HashSet<String>();
		failedNodes = new HashSet<String>();
		ints = new HashSet<String>();
		props = new HashSet<String>();
		
	}
	
	public void loadRules() throws Exception {
	    FileSource fs = FileSourceFactory.sourceFor(intRulesFile);
	    fs.addSink(intRulesGraph);
	    fs.readAll(intRulesFile);

	    Iterator<Node> nodeIterator = intRulesGraph.getNodeIterator();
		while(nodeIterator.hasNext()) {
			Node node = nodeIterator.next();
			if (NODE_TYPE_INTERIM.equals(node.getAttribute(NODE_TYPE))) {	
				ints.add(node.getId());
			}
			if (NODE_TYPE_PROPERTY.equals(node.getAttribute(NODE_TYPE))) {	
				props.add(node.getId());
			}
		}
	}

	public Set<String> getInts(Set<String> givenProps) {
		Set<String> resultList = new HashSet<String>();

		for (String property : props) {
			if (givenProps.contains(property)) {
				passedNodes.add(property);
			} else {
				failedNodes.add(property);
			}
		}
		
		for (String interim : ints) {
			if (reachable(interim)) {
				resultList.add(interim);
			}
		}
		
		return resultList;
	}

	private boolean reachable(String id) {
		Node node = intRulesGraph.getNode(id);
		
		if (NODE_TYPE_INTERIM.equals(node.getAttribute(NODE_TYPE))) {
			Collection<Edge> enteringEdges = node.getEnteringEdgeSet();
			for (Edge enteringEdge : enteringEdges) {
				Node source = enteringEdge.getSourceNode();
				if (reachable(source.getId())) {
					return true;
				}
			}
			return false;
		}
		if (NODE_TYPE_PROPERTY.equals(node.getAttribute(NODE_TYPE))) {
			return passedNodes.contains(id);
		}
		if (NODE_TYPE_AND.equals(node.getAttribute(NODE_TYPE))) {
			return evaluateAnd(node);
		}
		if (NODE_TYPE_OR.equals(node.getAttribute(NODE_TYPE))) {
			return evaluateOr(node);
		}
		return false;
	}

	private boolean evaluateAnd(Node node) {
		if (passedNodes.contains(node.getId())) {
			return true;
		} else if (failedNodes.contains(node.getId())) {
			return false;
		}
		
		Collection<Edge> enteringEdges = node.getEnteringEdgeSet();
		int numEdges = enteringEdges.size();
		int reachCount = reachCount(enteringEdges);
		
		if (reachCount == numEdges) {
			passedNodes.add(node.getId());
			return true;
		} else {
			failedNodes.add(node.getId());
			return false;
		}
	}

	private boolean evaluateOr(Node node) {
		if (passedNodes.contains(node.getId())) {
			return true;
		} else if (failedNodes.contains(node.getId())) {
			return false;
		}

		Collection<Edge> enteringEdges = node.getEnteringEdgeSet();
		int reachCount = reachCount(enteringEdges);
		
		if (reachCount > 0) {
			passedNodes.add(node.getId());
			return true;
		} else {
			failedNodes.add(node.getId());
			return false;
		}
	}

	private int reachCount(Collection<Edge> enteringEdges) {
		int reachCount = 0;
		for (Edge enteringEdge : enteringEdges) {
			Node source = enteringEdge.getSourceNode();
			if (reachable(source.getId())) {
				reachCount++;
			}
		}
		return reachCount;
	}
}
