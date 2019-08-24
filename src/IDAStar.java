import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * IDA*(Node start, Vector Goals)
	1. L <- make_stack and H <- make_hash_table
	2. t <- h(start)
	3. While t != Infinity
		1. minF <- Infinity
		2. L.insert(start)
		3. H.insert(start)
		4. While L is not empty
			1. n <- L.remove_front()
			2. If n is marked as “out”
				1. H.remove(n)
			2. Else
				2. mark n as “out” and L.insert(n)
				3. For each allowed operator g on n
					1. If f(g) > t
						1. minF <- min(minF, f(g))
						2. continue with the next operator
					2. If H contains g’=g and g’ marked “out”
						1. continue with the next operator
					3. If H contains g’=g and g’ not marked “out”
						1. If f(g’)>f(g)
							1. remove g’ from L and H
						2. Else
							1. continue with the next operator

					4. If goal(g) then return path(g) //all the “out” nodes in L
					5. L.insert(g)
					6. H.insert(g)

	5. t <- minF
4. Return false
 * */

public class IDAStar{
	
	int numberOfNodesCreated = 0;
	
	public Node ida_star_algorithm(Node start, Node goals, Boolean printOpenListFlag) {
		if(start.equals(goals)){
			return start;
		}
		Stack<Node> openListStack = new Stack<>();
		Hashtable<String, Node> openList = new Hashtable<>();
		Integer threshold = start.heuristicFunctionValue + start.movementCost;
		while (threshold != Integer.MAX_VALUE) {
			
			Integer minF = Integer.MAX_VALUE;
			openListStack.push(start);
			openList.put(start.boardToString(), start);
//			if(printOpenListFlag){
//				PriorityQueue<Node> priorityQueueForPrinting = new PriorityQueue<Node>(new IDAStarComparator());
//				priorityQueueForPrinting.addAll(priorityQueue);
////				System.out.println("generatedMoves array list: \n");
//				for (int i = 0; i < priorityQueueForPrinting.size(); i++) {
//					System.out.print(priorityQueueForPrinting.peek().toString());
//					System.out.print("heuristicFunctionValue = ");
//					System.out.println(priorityQueueForPrinting.remove().heuristicFunctionValue + "\n");
//				}
//			}
			while (!openListStack.isEmpty()) {
				Node n = openListStack.pop();
//				numberOfNodesCreated++;
				if(n.isVisited){
					openList.remove(n.boardToString());
				}
				else{
					n.isVisited = true;
					openListStack.push(n);
					PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(new IDAStarComparator());
					ArrayList<Node> generatedMovesOnStage = n.generateMovement();
					numberOfNodesCreated += generatedMovesOnStage.size();	
					if(printOpenListFlag){
//						System.out.println("generatedMoves array list: \n");
						for (int i = 0; i < generatedMovesOnStage.size(); i++) {
							System.out.print(generatedMovesOnStage.get(i).toString());
							System.out.print("heuristicFunctionValue = ");
							System.out.println(generatedMovesOnStage.get(i).heuristicFunctionValue + "\n");
						}
					}
					priorityQueue.addAll(generatedMovesOnStage);
					for (Node g : priorityQueue) {
						Integer currentNodeStageValue = g.heuristicFunctionValue + g.movementCost;
						if(currentNodeStageValue > threshold){
							minF = Math.min(minF, currentNodeStageValue);
							continue; //continue with next operator
						}
						Node gPrime = openList.get(g.boardToString());
						if(gPrime != null && gPrime.isVisited){
							continue; //continue with next operator
						}
						if(gPrime != null && !gPrime.isVisited){ 
							if(gPrime.heuristicFunctionValue + gPrime.movementCost > currentNodeStageValue){
								openListStack.remove(gPrime); //removes ONLY FIRST occurance
								openList.remove(gPrime.boardToString());
								System.out.println(numberOfNodesCreated);
								numberOfNodesCreated++;
							}
							else{
								continue; //continue with next operator
							}
						}
						if(g.equals(goals)){
							return g;
						}
						openListStack.push(g);
						openList.put(g.boardToString(), g);
					}
				}
			}
			threshold = minF;
		}
		return null;
	}
}
