import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	
//	HashMap<Integer, Node> openList = new HashMap<Integer, Node>();
	Queue<Node> openListQueue = new LinkedList();
	Hashtable<Integer, Node> previousStates; //closed-list
	Hashtable<Integer, Node> openListHash;
	/*
	 * BFS(Node start, Vector Goals)
		1. L <- make_queue(start) and make_hash_table
		2. C <- make_hash_table
		3. While L not empty loop
			1. n <- L.remove_front()
			2. C <- n
			3. For each allowed operator on n
				1. g <- operator(n)
				2. If g not in C and not in L
					1. If goal(g) return path(g)
					2. L.insert(g)
		4. Return false
	 * */

	public void BFS_Algorithm(Node startingBoard,Node goalStage){
		//	  HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		Integer key = openListHash.hashCode();
		openListHash.put(key,startingBoard);
		openListQueue.add(startingBoard);
//		while(!openList.isEmpty()){
//			Node n = openList.remove(key)
//		}
	}


}
