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
	public HashSet<GraphNode>nodeList;
	public DirectedGraph(){
		this.nodeList = new HashSet<GraphNode>();
	}
	void addNode(final String nodeVal){
		nodeList.add(new GraphNode(nodeVal));
	}
	void addDirectedEdge(final GraphNode first, final GraphNode second){
		if(second.neighbors.contains(first)){
			return;
		}
		first.neighbors.add(second);
	}
	void removeDirectedEdge(final GraphNode first, final GraphNode second){
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
