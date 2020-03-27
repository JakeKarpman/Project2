import java.util.*;

//Class Graph is called TraverseTown

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
public class TraverseTown {
	public HashSet<GraphNode>nodeList;
	
	public TraverseTown(){
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
		try{
			first.neighbors.remove(second);
			second.neighbors.remove(first);
		}
		catch(Exception e){
			return;
		}
	}
	HashSet<GraphNode> getAllNodes(){
		return nodeList;
	}
}
