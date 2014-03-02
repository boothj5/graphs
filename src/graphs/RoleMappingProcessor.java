package graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;

public class RoleMappingProcessor {

	String intRulesFile;
	Graph intRulesGraph;
	
	public RoleMappingProcessor(String intRules, String roleRules) {
		intRulesFile = intRules;
		intRulesGraph = new DefaultGraph("intRules");
	}
	
	public void loadRules() throws Exception {
	    FileSource fs = FileSourceFactory.sourceFor(intRulesFile);
	    fs.addSink(intRulesGraph);
	    fs.readAll(intRulesFile);
	}

	public List<String> getInts(List<String> props) {
		Set<String> resultSet = new HashSet<String>();
		List<String> resultList = new ArrayList<String>();
		
		for (String prop: props) {
			Node propNode = intRulesGraph.getNode(prop);
			Collection<Edge> leavingEdges = propNode.getLeavingEdgeSet();
			
			for (Edge leavingEdge : leavingEdges) {
				Node targetNode = leavingEdge.getTargetNode();
				
				if (isInterimNode(targetNode)) {
					resultSet.add(leavingEdge.getTargetNode().getId());
				
				} else if (isAndNode(targetNode)) {
					handleAndNode(props, resultSet, leavingEdge, targetNode);
				
				} else if (isOrNode(targetNode)) {
					handleOrNode(props, resultSet, leavingEdge, targetNode);
				}
			}
		}
		
		resultList.addAll(resultSet);
		
		return resultList;
	}

	private void handleOrNode(List<String> props, Set<String> resultSet,
			Edge leavingEdge, Node targetNode) {
		Collection<Edge> enteringEdges = targetNode.getEnteringEdgeSet();
		
		for (Edge enteringEdge : enteringEdges) {
			Node requiredNode = enteringEdge.getSourceNode();
			if (isPropertyNode(requiredNode)) {
				if (props.contains(requiredNode.getId())) {
					resultSet.add(leavingEdge.getTargetNode().getLeavingEdge(0).getTargetNode().getId());
					break;
				}
			}
		}
	}

	private void handleAndNode(List<String> props, Set<String> resultSet,
			Edge leavingEdge, Node targetNode) {
		Collection<Edge> enteringEdges = targetNode.getEnteringEdgeSet();
		Set<String> requiredProperties = new HashSet<String>();
		
		for (Edge enteringEdge : enteringEdges) {
			Node requiredNode = enteringEdge.getSourceNode();
			if (isPropertyNode(requiredNode)) {
				requiredProperties.add(requiredNode.getId()); 
			}
		}
		
		if (props.containsAll(requiredProperties)) {
			resultSet.add(leavingEdge.getTargetNode().getLeavingEdge(0).getTargetNode().getId());
		}
	}

	private boolean isPropertyNode(Node node) {
		return "property".equals(node.getAttribute("type"));
	}

	private boolean isAndNode(Node node) {
		return "and".equals(node.getAttribute("type"));
	}

	private boolean isOrNode(Node node) {
		return "or".equals(node.getAttribute("type"));
	}

	private boolean isInterimNode(Node node) {
		return "interim".equals(node.getAttribute("type"));
	}
}
