import java.util.*;


public class Main_GridGraph {
	public static GridGraph createRandomGridGraph(int n){
		GridGraph graph = new GridGraph();
		String nodeName;
		for(int i = 0; i<=n; i++){
			for(int j =0; j<=n; j++){
				nodeName = "("+i+", "+j+")";
				graph.addGridNode(i, j,nodeName);
			}
		}
		HashSet<GridNode> nodes = graph.getAllNodes();
		for(GridNode g: nodes){
			for(GridNode neighbor: g.adjNeighbors){
				if(Math.random() > 0.5){
					graph.addUndirectedEdge(g, neighbor);
				}
			}
		}
		return graph;
	}
	public static ArrayList<GridNode> astar(final GridNode sourceNode, final GridNode destNode){
		Map<GridNode, AstarMap> dict = new HashMap<GridNode, AstarMap>();
		AstarMap start= new AstarMap(0, manhattan(sourceNode, destNode), null);
		HashSet<GridNode> visited = new HashSet<GridNode>();
		ArrayList<GridNode> path = new ArrayList<GridNode>();
		dict.put(sourceNode, start);
		GridNode curr = sourceNode;
		while(curr != null && curr != destNode){
			visited.add(curr);
			for(GridNode edge : curr.edges){
				if(!visited.contains(edge)){
					int dist = dict.get(curr).gval;
					int heur = manhattan(edge, destNode);
					if(!(dict.containsKey(edge)) || (dist+heur<(dict.get(edge).gval + dict.get(edge).hval))){
						dict.put(edge, new AstarMap(dist, heur, curr));
					}
				}
			}
			curr = minNext(dict, visited);
		}
		GridNode next = destNode;
		while(next != null){
			path.add(next);
			try{
				if(dict.get(next).getParent() != null){
					next = dict.get(next).getParent();
				}
				else{
					next = null;
				}
			}
			catch(java.lang.NullPointerException e){
				return null;
			}
		}
		if(path.contains(sourceNode)){
			return path;
		}
		else{
			return null;
		}
	}
	private static int manhattan(GridNode s, GridNode d){
		return Math.abs(s.x_coord - d.x_coord) + Math.abs(s.y_coord - d.y_coord);
	}
	private static GridNode minNext(Map<GridNode, AstarMap> dict, HashSet<GridNode> visited){
		GridNode next = null;
		int visitedMax = Integer.MAX_VALUE;
		for(GridNode i: dict.keySet()){
			if(!(visited.contains(i)) && (dict.get(i).gval+dict.get(i).hval <= visitedMax) ){
				visitedMax = dict.get(i).gval+dict.get(i).hval;
				next = i;
			}
		}
		return next;
	}
	public static void main(String [] args){
		GridGraph graph = new GridGraph();
		graph = createRandomGridGraph(100);
		
		//Use block below to test createRandomGridGraph
		HashSet<GridNode> test = graph.getAllNodes();
		for(GridNode j: test){
			for(GridNode x: j.adjNeighbors){
				System.out.print(x.data + "   ");
			}
			System.out.println(j.data);
		}

		//Use block below to test A*
		GridNode s = graph.getNode(0,0);
		GridNode d = graph.getNode(100, 100);
		ArrayList<GridNode> result = new ArrayList<GridNode>();
		result = astar(s,d);
		if(result != null){
			for(GridNode i: result){
				System.out.println(i.data);
			}
		}
		else{
			System.out.println("null");
		}
	}
}
class AstarMap{
	public int gval;
	public int hval;
	public GridNode parent;

	public AstarMap(int gval, int hval, GridNode parent){
		this.gval = gval;
		this.hval = hval;
		this.parent = parent;
	}
	public GridNode getParent(){
		if(this.parent != null){
			return this.parent;
		}
		else{
			return null;
		}
	}
}
