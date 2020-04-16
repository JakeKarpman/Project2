import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;
public class TopSort {
	//Used repl.it for much of the code below
	public static ArrayList<GraphNode> kahns(final DirectedGraph graph){
		final HashMap<GraphNode, Integer> inDegree = initializeInDegreeMap(graph);
		final ArrayList<GraphNode> topSort = new ArrayList<GraphNode>();
		final Deque<GraphNode> queue = new ArrayDeque<GraphNode>();
		addNodesWithoutDependenciesToQueue(inDegree, queue);
		while (!queue.isEmpty()) {
			final GraphNode curr = (GraphNode) queue.pop();
			topSort.add(curr);
			for (GraphNode neighbor : curr.neighbors) {
				inDegree.replace(neighbor, inDegree.get(neighbor) - 1);
			}
			addNodesWithoutDependenciesToQueue(inDegree, queue);
		}
		return topSort;
	}
	private static HashMap<GraphNode, Integer> initializeInDegreeMap(final DirectedGraph g) {
		final HashMap<GraphNode, Integer> inDegree = new HashMap<GraphNode, Integer>();
		for (GraphNode node : g.nodeSet) {
			inDegree.put(node, 0);
		}
		for (GraphNode node : g.nodeSet) {
			for (GraphNode neighbor : node.neighbors) {
				inDegree.replace(neighbor, inDegree.get(neighbor) + 1);
			}
		}
		return inDegree;
	}
	private static void addNodesWithoutDependenciesToQueue(HashMap<GraphNode, Integer> inDegree, Deque<GraphNode> queue) {
		for (GraphNode curr : inDegree.keySet()) {
			if (inDegree.get(curr) == 0) {
				queue.add(curr);
				inDegree.replace(curr, inDegree.get(curr) - 1);
			}
		}
	}
	static ArrayList<GraphNode> mDFS(final DirectedGraph graph){
		final Stack<GraphNode> stack = new Stack<GraphNode>();
		final ArrayList<GraphNode> topSort = new ArrayList<GraphNode>();
		for (GraphNode node : graph.nodeSet) {
			if (!node.getVisited()) {
				modifiedDfsHelper(node, stack);
			}
		}
		while (!stack.empty()) {
			final GraphNode currNode = (GraphNode) stack.pop();
			topSort.add(currNode);

		}
		if(topSort.isEmpty()){
			System.out.println("flag");
		}
		return topSort;
	}
	private static void modifiedDfsHelper(final GraphNode node, final Stack<GraphNode> stack) {
		node.setVisited();
		for (GraphNode neighbor : node.neighbors) {
			if (!neighbor.getVisited()) {
				modifiedDfsHelper(neighbor, stack);
			}
		}
		stack.push(node);
	}
}

