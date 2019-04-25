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
			Node currentNode = openListQueue.remove();
			openListHash.remove(currentNode.stage);
			Integer [][] closedListHashkey =currentNode.stage ;
			previousStates.put(closedListHashkey, currentNode);
			ArrayList<Node> generatedMovesOnStage = currentNode.generateMovement(); 
			/////
			System.out.println("generatedMoves array list: \n");
			for (int i = 0; i < generatedMovesOnStage.size(); i++) {
				System.out.println(generatedMovesOnStage.get(i).toString());
				System.out.println("\n\n");
			}
			////
			if(Arrays.deepEquals(currentNode.stage, goalStage.stage)){
				System.out.println("moves: "+currentNode.move);
				return currentNode.move;
			}
			int  i=0;
			for (Node nextMove : generatedMovesOnStage) {
				if(!openListHash.containsKey(nextMove.stage) && !previousStates.containsKey(nextMove.stage)){
					if(Arrays.deepEquals(nextMove.stage, goalStage.stage)){
						return nextMove.move;
					}
					openListHashkey = nextMove.stage;
					openListHash.put(openListHashkey, nextMove);
					openListQueue.add(nextMove);
				}
			}
//			while(!generatedMovesOnStage.isEmpty() && i < generatedMovesOnStage.size()){
//				if((openListHash.get(generatedMovesOnStage.get(i).stage) == null) && (previousStates.get(generatedMovesOnStage.get(i).stage) == null)){
//					if(Arrays.deepEquals(generatedMovesOnStage.get(i).stage, goalStage.stage)){
//						return generatedMovesOnStage.get(i).move;
//					}
//					openListHashkey = currentNode.stage;
//					openListHash.put(openListHashkey, currentNode);
//					openListQueue.add(currentNode);
//				}
//				i++;
//			}
//			if(!openListQueue.isEmpty())
//				n = openListQueue.remove();
		}
		
		return null;
	}
}
