import java.util.*;
class GraphNode{
	public String data;
	public List<GraphNode> neighbors;
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
public class Graph {
	public HashSet<GraphNode>nodeList;
	
	public Graph(){
		this.nodeList = new HashSet<GraphNode>();
	}
	void addNode(final String nodeVal){
		nodeList.add(new GraphNode(nodeVal));
	}
	void addUndirectedEdge(final GraphNode first, final GraphNode second){
		if(second.neighbors.contains(first) || first.neighbors.contains(second)){
			return;
		}
		first.neighbors.add(second);
		second.neighbors.add(first);
	}
	void removeUndirectedEdge(final GraphNode first, final GraphNode second){
		if(second.neighbors.contains(first) || first.neighbors.contains(second)){
			first.neighbors.remove(second);
			second.neighbors.remove(first);
		}
	}
	HashSet<GraphNode> getAllNodes(){
		return nodeList;
	}
}
