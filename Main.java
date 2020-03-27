import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;


public class Main {
	public TraverseTown createRandomUnweightedGraphIter(int n){
		Random random = new Random();
		TraverseTown graph = new TraverseTown();
		if(n<1){
			return graph;
		}
		for(int i = 1; i<=n; i++){
			graph.addNode(String.valueOf(n));
		}
		HashSet<GraphNode> nodes = graph.getAllNodes();
		for(GraphNode i : nodes){
			for(GraphNode j: nodes){
				if(random.nextInt(1) == 0){
					graph.addUndirectedEdge(i, j);
				}
			}
		}
		return graph;
	}
	public static TraverseTown createLinkedList(int n){
		TraverseTown graph = new TraverseTown();
		if(n<1){
			return graph;
		}
		graph.addNode(""+0);
		for(int i = 1; i<=n; i++){
			graph.addNode(""+i);
		}
		HashSet<GraphNode> nodes = graph.getAllNodes();
		Iterator<GraphNode> iter = nodes.iterator();
		for(GraphNode i,j = iter.next(); iter.hasNext();){
			i = j;
			j = iter.next();
			graph.addUndirectedEdge(i, j);
		}
		return graph;
	}
	public static ArrayList<GraphNode> BFTRecLinkedList(final TraverseTown graph){
		GraphSearch g = new GraphSearch();
		return g.BFTRec(graph);
	}
	public static ArrayList<GraphNode> BFTIterLinkedList(final TraverseTown graph){
		GraphSearch g = new GraphSearch();
		return g.BFTIter(graph);
	}
	public static void main(String [] args){
		TraverseTown graph = new TraverseTown();
		graph = createLinkedList(10000);
		ArrayList<GraphNode> test,test2 = new ArrayList<>();
		test = BFTIterLinkedList(graph);
		test2 = BFTRecLinkedList(graph);
		//Use this to test Iterative
		for(GraphNode j:test){
			System.out.println(j.data);
		}
		//Use this to test Recursive
		for(GraphNode k: test2){
			System.out.println(k.data);
		}
	}
}
