import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class GridNode{
	public String data;
	public List<GridNode> adjNeighbors;
	public List<GridNode> edges; 
	private boolean visited;
	int x_coord, y_coord;

	public GridNode(String data, int x_coord, int y_coord){
		this.data = data;
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.adjNeighbors = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.visited = false;

	}

	public void setVisited(){
		visited = true;
	}
	public boolean getVisited(){
		return visited;
	}
} 

public class GridGraph {
	public HashSet<GridNode>nodeList;
	public GridGraph(){
		this.nodeList = new HashSet<GridNode>();
	}
	public void addGridNode(final int x, final int y, final String nodeName){
		GridNode gridNode;
		gridNode = new GridNode(nodeName, x, y);
		nodeList.add(gridNode);
		for(GridNode g: nodeList){
			if((g.x_coord == (gridNode.x_coord -1) && g.y_coord == gridNode.y_coord )|| 
					(g.x_coord == (gridNode.x_coord+1) && g.y_coord == gridNode.y_coord)||
						(g.y_coord == (gridNode.y_coord -1) && g.x_coord == gridNode.x_coord)|| 
							(g.y_coord == (gridNode.y_coord+1) && g.x_coord == gridNode.x_coord)){
				gridNode.adjNeighbors.add(g);
				g.adjNeighbors.add(gridNode);  
			}
		}
	}
	public void addUndirectedEdge(final GridNode first, final GridNode second){
		if(second.adjNeighbors.contains(first) || first.adjNeighbors.contains(second)){
			first.edges.add(second);
			second.edges.add(first);
		}
	}
	public void removeUndirectedEdge(final GridNode first, final GridNode second){
		try{
			first.edges.remove(second);
			second.edges.remove(first);
		}
		catch(Exception e){
			return;
		}
	}
	HashSet<GridNode> getAllNodes(){
		return nodeList;
	}
	public GridNode getNode(int x, int y){
		for(GridNode i: nodeList){
			if(i.x_coord == x && i.y_coord == y){
				return i;
			}
		}
		return null;
	}
}
