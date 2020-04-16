
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;




class GraphNode{
	public String data;
	public HashMap<GraphNode,Integer> neighbors;
	private boolean visited;

	public GraphNode(String data){
		this.data = data;
		this.neighbors = new HashMap<GraphNode, Integer>();
		this.visited = false;

	}

	public void setVisited(){
		visited = true;
	}
	public boolean getVisited(){
		return visited;
	}
} 
public class WeightedGraph {
	public HashSet<GraphNode>nodeSet;
	public WeightedGraph(){
		this.nodeSet = new HashSet<GraphNode>();
	}
	public GraphNode getGraphNode(String data){
		Iterator<GraphNode> iter = nodeSet.iterator();
		while(iter.hasNext()){
			GraphNode i = iter.next();
			if(i.data.equals(data)){
				return i;
			}
		}
		return null;
	}
	void addNode(final String nodeVal){
		nodeSet.add(new GraphNode(nodeVal));
	}
	void addWeightedEdge(final GraphNode first, final GraphNode second, final int weight){
		if(first != second){
			first.neighbors.put(second, weight);
		}
	}
	void removeWeightedEdge(final GraphNode first, final GraphNode second){
		if(first.neighbors.containsKey(second)){
			first.neighbors.remove(second);
		}
	}
	HashSet<GraphNode> getAllNodes(){
		return nodeSet;
	}
}
