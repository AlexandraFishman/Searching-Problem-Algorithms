import java.util.PriorityQueue;

public class AStar extends frontieerSearch {
	
	public AStar(){
		openListQueue = new PriorityQueue<Node>(new AStarComparator());
	}
}
