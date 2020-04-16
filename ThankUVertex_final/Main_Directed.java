import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
public class Main_Directed {


	public static DirectedGraph createRandomDAGIter(final int n){
		DirectedGraph graph = new DirectedGraph();
		for(int i=0; i<n; i++){
			graph.addNode(""+i);
		}
		HashSet<GraphNode> nodes = graph.getAllNodes();
		HashSet<GraphNode> visited = new HashSet<>();
		Iterator<GraphNode> iter = nodes.iterator();
		Iterator<GraphNode> iter2 = nodes.iterator();
		while(iter.hasNext()){
			GraphNode i = iter.next();
			visited.add(i);
			while(iter2.hasNext()){
				GraphNode j = iter2.next();
				if(visited.contains(j)){
					continue;
				}
				if(Math.random()>.5){
					graph.addDirectedEdge(i, j);
				}
			}
		}
		return graph;
	}
	
	public static void main(String [] args){
		DirectedGraph graph = new DirectedGraph();
		ArrayList<GraphNode> test= new ArrayList<GraphNode>();
		graph = createRandomDAGIter(1000);
		test = TopSort.kahns(graph);
		final StringBuilder string = new StringBuilder("Output of Kahn's: ");
		for (final GraphNode node : test) {
			string.append("->" + node.data);
		}
		System.out.println(string.toString());

		test = TopSort.mDFS(graph);
		final StringBuilder string2 = new StringBuilder("Output of Modified DFS: ");
		for(GraphNode node:test){
			string2.append("->"+node.data);
		}
		System.out.println(string2.toString());

	}


}

