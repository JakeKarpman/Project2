import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
public class DirectedGraph {
	public HashSet<GraphNode>nodeSet;
	public DirectedGraph(){
		this.nodeSet = new HashSet<GraphNode>();
	}
	void addNode(final String nodeVal){
		nodeSet.add(new GraphNode(nodeVal));
	}
	void addDirectedEdge(final GraphNode first, final GraphNode second){
		if(!first.neighbors.contains(second)){
			first.neighbors.add(second);
		}	
	}
	void removeDirectedEdge(final GraphNode first, final GraphNode second){
		if(first.neighbors.contains(second)){
			first.neighbors.remove(second);
		}
	}
	HashSet<GraphNode> getAllNodes(){
		return nodeSet;
	}
}
