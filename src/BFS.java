import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	
//	HashMap<Integer, Node> openList = new HashMap<Integer, Node>();
	Queue<Node> openListQueue = new LinkedList();
	Hashtable<Integer[][], Node> previousStates = new Hashtable<>(); //closed-list
	Hashtable<Integer[][], Node> openListHash = new Hashtable<>();
	
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

	public String BFS_Algorithm(Node startingBoard,Node goalStage){
		//	  HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		Integer [][] openListHashkey = startingBoard.stage;
		openListHash.put(openListHashkey,startingBoard);
		openListQueue.add(startingBoard);
		
		previousStates = new Hashtable<>();
		
		while(!openListQueue.isEmpty()){
			Node n = openListQueue.remove();
			Integer [][] closedListHashkey =n.stage ;
			previousStates.put(closedListHashkey, n);
			ArrayList<Node> generatedMovesOnStage = n.generateMovement(); 
			int  i=0;
			while(generatedMovesOnStage != null){
				if((openListHash.get(generatedMovesOnStage.get(i).stage) != null) && (previousStates.get(generatedMovesOnStage.get(i).stage) != null)){
					if(Arrays.deepEquals(generatedMovesOnStage.get(i).stage, goalStage.stage)){
						return generatedMovesOnStage.get(i).move;
					}
					openListHashkey = n.stage;
					openListHash.put(openListHashkey, n);
					openListQueue.add(n);
				}
				i++;
			}
			if(!openListQueue.isEmpty())
				n = openListQueue.remove();
		}
		
		return null;
	}
}
