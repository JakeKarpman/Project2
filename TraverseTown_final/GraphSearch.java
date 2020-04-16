import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class GraphSearch{
	public ArrayList<GraphNode> DFSIter(final GraphNode start, final GraphNode end){
		Stack<GraphNode> stack = new Stack<GraphNode>();
		ArrayList<GraphNode> dfs_path = new ArrayList<>();
		HashSet<GraphNode> visited = new HashSet<>();
		stack.push(start);
		while(!stack.isEmpty()){
			GraphNode curr = stack.pop();
			if(visited.contains(curr)){
				continue;
			}
			else{
				visited.add(curr);
			}
			dfs_path.add(curr);
			if(curr.equals(end)){
				break;
			}
			for(GraphNode i:curr.neighbors){
				stack.push(i);
			}
		}
		if(visited.contains(end)){
			return dfs_path;
		}
		return null;
	}
	public ArrayList<GraphNode> DFSRec(final GraphNode start, final GraphNode end){
		ArrayList<GraphNode> list = new ArrayList<>();
		if(start == end){
			list.add(end);
			return list;
		}
		list = DFSRecHelp(start, end, list);
		return list;
	}
	private ArrayList<GraphNode> DFSRecHelp(GraphNode curr, GraphNode end, ArrayList<GraphNode> list){
		list.add(curr);
		if(curr == end){
			return list;
		}
		for(int i=0; i<curr.neighbors.size(); i++){
			if(!list.contains(curr.neighbors.get(i))){
				return DFSRecHelp(curr.neighbors.get(i), end, list);
			}
		}
		return list;
	}
	public ArrayList<GraphNode> BFTIter(final Graph graph){
		ArrayList<GraphNode> queue = new ArrayList<GraphNode>();
		ArrayList<GraphNode> visitedNodes = new ArrayList<GraphNode>();
		for(GraphNode i : graph.getAllNodes()){
			if(!(visitedNodes.contains(i))){
				visitedNodes.add(i);
				queue.add(i);
			}
			while(!queue.isEmpty()){
				GraphNode curr = queue.remove(0);
				for(GraphNode j: curr.neighbors){
					if(!(visitedNodes.contains(j))){
						queue.add(j);
						visitedNodes.add(j);
					}
				}
			}
		}
		return visitedNodes;
	}
	public ArrayList<GraphNode> BFTRec(final Graph graph){
		ArrayList<GraphNode> list = new ArrayList<>();
		HashSet<GraphNode> nodes = graph.getAllNodes();
		for(GraphNode i : nodes){
			if(!(list.contains(i))){
				list = BFTRecHelp(i, list);
			}
		}
		return list;
	}
	private ArrayList<GraphNode> BFTRecHelp(GraphNode curr, ArrayList<GraphNode> list){
		for(int i =0; i<curr.neighbors.size(); i++){
			if(!(list.contains(curr.neighbors.get(i)))){
				list.add(curr.neighbors.get(i));
				list = BFTRecHelp(curr.neighbors.get(i), list);
			}
		}
		return list;
	}
	
}
