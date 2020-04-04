import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

class Edge {
	public GraphNode dest;
	public int weight;
	public Edge(GraphNode d, int w) {
		this.dest = d;
		this.weight = w;
	}
	public GraphNode getDest(){
		return dest;
	}
	public int getWeight(){
		return weight;
	}
}

class GraphNode{
	public String data;
	public List<Edge> neighbors;
	private boolean visited;

	public GraphNode(String data){
		this.data = data;
		this.neighbors = new ArrayList<>();
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
	public HashSet<GraphNode>nodeList;
	public WeightedGraph(){
		this.nodeList = new HashSet<GraphNode>();
	}
	GraphNode getGraphNode(String data){
		Iterator<GraphNode> iter = nodeList.iterator();
		while(iter.hasNext()){
			GraphNode i = iter.next();
			if(i.data.equals(data)){
				return i;
			}
		}
		return null;
	}
	void addNode(final String nodeVal){
		nodeList.add(new GraphNode(nodeVal));
	}
	void addWeightedEdge(final GraphNode first, final GraphNode second, final int weight){
		if(first != second){
			Collections.addAll(first.neighbors, new Edge(second, weight));
		}
	}
	void removeWeightedEdge(final GraphNode first, final GraphNode second){
		try{
			first.neighbors.remove(second);
		}
		catch(Exception e){
			return;
		}
	}
	HashSet<GraphNode> getAllNodes(){
		return nodeList;
	}
}
