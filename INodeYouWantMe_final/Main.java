
import java.util.*;


public class Main{

	public static void main(String [] args){
		WeightedGraph graph = new WeightedGraph();
							
		/*Graph below is  (0) -5-(1)- 8- (2)
							\     /\     /
							 1   4  3   6
							  \ /    \ /
							  (3)    (4)*/
		graph.addNode("0");
		graph.addNode("1");
		graph.addNode("2");
		graph.addNode("3");
		graph.addNode("4");
		
		graph.addWeightedEdge(graph.getGraphNode("0"), graph.getGraphNode("1"), 5);
		graph.addWeightedEdge(graph.getGraphNode("0"), graph.getGraphNode("3"), 1);
		graph.addWeightedEdge(graph.getGraphNode("1"), graph.getGraphNode("3"), 4);
		graph.addWeightedEdge(graph.getGraphNode("1"), graph.getGraphNode("4"), 3);
		graph.addWeightedEdge(graph.getGraphNode("1"), graph.getGraphNode("2"), 8);
		graph.addWeightedEdge(graph.getGraphNode("2"), graph.getGraphNode("4"), 6);
		
		//Use result below to test createRandomCompleteWeightedGraph, this is commented out in output
		graph = createRandomCompleteWeightedGraph(10);
		
		HashMap<GraphNode, Integer> dDict = dijkstras(graph.getGraphNode("0"));
		Iterator<GraphNode> iter = dDict.keySet().iterator();
		while(iter.hasNext()){
			GraphNode i = iter.next();
			System.out.println("Node number: "+i.data+" Path cost: "+dDict.get(i));
		}
		
	}
	public static WeightedGraph createRandomCompleteWeightedGraph(final int n){
		WeightedGraph graph = new WeightedGraph();
		HashSet<GraphNode> nodes = graph.getAllNodes();
		Random random = new Random();
		GraphNode mark;
		if(n<1){
			return graph;
		}
		for(int i = 1; i<=n; i++){
			graph.addNode(String.valueOf(n));
		}
		
		for(Iterator<GraphNode> iter = nodes.iterator();iter.hasNext();){
			mark = iter.next();
			Iterator<GraphNode> iter2 = nodes.iterator();
			for(iter2.next(); iter2.hasNext();){
				graph.addWeightedEdge(mark, iter2.next(), random.nextInt(30));
			}
		}
		return graph;
		
	}
	public static WeightedGraph createLinkedList(final int n){
		WeightedGraph graph = new WeightedGraph();
		HashSet<GraphNode> nodes = graph.getAllNodes();
		Iterator<GraphNode> iter = nodes.iterator();
		if(n<1){
			return graph;
		}
		for(int i = 1; i<=n; i++){
			graph.addNode(String.valueOf(n));
		}
		for(GraphNode i,j = iter.next(); iter.hasNext();){
			i = j;
			j = iter.next();
			graph.addWeightedEdge(i, j, 1);
		}
		return graph;
	}
	public static HashMap<GraphNode, Integer> dijkstras(GraphNode start){
		HashMap<GraphNode, Integer> path = new HashMap<>();
		HashSet<GraphNode> visited = new HashSet<GraphNode>();
		Queue<GraphNode> priqueue = new PriorityQueue<>((a, b) -> ((path.containsKey(a) ? path.get(a) : 0) - (path.containsKey(b) ? path.get(b) : 0)));
		path.put(start, 0);
		if(start == null){
			System.out.print("Flag");
		}
		priqueue.add(start);
		while(!priqueue.isEmpty()){
			GraphNode curr = priqueue.poll();
			for(GraphNode i: curr.neighbors.keySet()){
				if(visited.contains(i)){
					continue;
				}
				priqueue.offer(i);
				int currTotal = curr.neighbors.get(i);
				int prevTotal = path.get(curr);
				int total = currTotal + prevTotal;
				if((!path.containsKey(i)) || (total)<path.get(i)){
					path.put(i, total);
				}
			}
			visited.add(curr);
		}
		return path;
	}
}
