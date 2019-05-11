import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class DFBnB{
	
	int numberOfNodesCreated = 0;
	
	public Node dfbnb_Algorithm(Node start,Node goals) {
		Node result = null;
		if(start.equals(goals)){
			return result;
		}
		Integer threshold = start.heuristicFunctionValue + start.movementCost;
		Stack<Node> openListStack = new Stack<>();
		openListStack.push(start);
		Hashtable<String, Node> openList = new Hashtable<>();
		openList.put(start.boardToString(), start);
		
		long startTime = System.nanoTime();
		long elapsedTime = 0;
		
		while(!openListStack.isEmpty() && elapsedTime/1e9 < 2){ //DFBnB is time limited - gave him 2 seconds  to search
			Node  n = openListStack.pop();

			if(n.isVisited){
				openList.remove(n.boardToString());
			}
			else{
				n.isVisited = true;
				openListStack.push(n);
				PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(new AStarComparator());
				ArrayList<Node> generatedMovesOnStage = n.generateMovement();
				priorityQueue.addAll(generatedMovesOnStage);
				PriorityQueue<Node> iterablePriorityQueue = new PriorityQueue<Node>(new AStarComparator());
				iterablePriorityQueue.addAll(priorityQueue);
				for (Node g : iterablePriorityQueue) {
					Node gPrime = openList.get(g.boardToString());
					numberOfNodesCreated++;

					Integer currentNodeStageValue = g.heuristicFunctionValue + g.movementCost;
					if(currentNodeStageValue > threshold){
						//remove g and all the nodes after it from N
						List<Node> forRemoval = removeUnwantedElemnts(generatedMovesOnStage, g);
						priorityQueue.removeAll(forRemoval);
						break;
						//removing all the nodes "at once"
					}
					else if(gPrime != null && gPrime.isVisited){
						priorityQueue.remove(g);
					}
					else if(gPrime != null && !gPrime.isVisited){
						if(gPrime.heuristicFunctionValue + gPrime.movementCost > currentNodeStageValue){
							priorityQueue.remove(g);
						}
						else{
							openListStack.remove(gPrime);
							openList.remove(gPrime.boardToString());
						}
					}
					else if(g.equals(goals)){ // if we reached here, f(g) < t
						threshold = currentNodeStageValue;
						result = g;
						//remove g and all the nodes after it from N
						List<Node> forRemoval = removeUnwantedElemnts(generatedMovesOnStage, g);
						priorityQueue.removeAll(forRemoval);
						//removing all the nodes "at once"
					}
					PriorityQueue<Node> reversePriorityQueue = new PriorityQueue<Node>(new IDAStarComparator());
					reversePriorityQueue.addAll(priorityQueue);
					openListStack.addAll(reversePriorityQueue);
					for (Node r : reversePriorityQueue) {
						openList.put(r.boardToString(), r);
					}
				}
			}
			 elapsedTime = System.nanoTime() - startTime;
		}
		return result;
	}
	
	private List<Node> removeUnwantedElemnts(ArrayList<Node> generatedMovesOnStage, Node g) {
		List<Node> forRemoval = new ArrayList<Node>();
		boolean afterG = false;
		for(Node r : generatedMovesOnStage){
			if(r.equals(g) || afterG){
				afterG = true;
				forRemoval.add(r);
			}
		}
		return forRemoval;
	}
}

/**
 * DFBnB(Node start, Vector Goals)
		1. L <- make_stack(start) and H <- make_hash_table(start)
		2. result <- null, t <- Infinity // should be set to a strict upper bound in an infinite graph
		3. While L is not empty

			1. n <- L.remove_front()------
			2. If n is marked as “out”
				1. H.remove(n)
			3. Else
				1. mark n as “out” and L.insert(n)----
				2. N <- apply all of the allowed operators on n-----
				3. sort the nodes in N according to their f values (increasing order)--------
				4. For each node g from N according to the order of N--------------
					1. If f(g) >= t----------
						1. remove g and all the nodes after it from N-----------------
						2. Else If H contains g’=g and g’ is marked as “out”-----------
							1. remove g from N------
						3. Else If H contains g’=g and g’ is not marked as “out”---------
							1. If f(g’)<=f(g)----------
								1. remove g from N-----------
							2. Else-------------
								1. remove g’ from L and from H--------------
						4. Else If goal(g) // if we reached here, f(g) < t------------
							1. t <- f(g)-----------------------
							2. result <- path(g) // all the “out” nodes in L-----
							3. remove g and all the nodes after it from N-----------

						5. insert N in a reverse order to L and H-------------------

		4. Return result/-----------
 */