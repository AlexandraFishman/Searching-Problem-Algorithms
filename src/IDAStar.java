import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

public class IDAStar extends frontieerSearch{
	public Node ida_star_algorithm(Node start, Node goals) {
		if(start.equals(goals)){
			return start;
		}
		Stack<Node> openListStack = new Stack<>();
		Hashtable<String, Node> openList = new Hashtable<>();
		Integer threshold = start.heuristicFunctionValue + start.movementCost;
		while (threshold != Integer.MAX_VALUE) {
			Integer minF = Integer.MAX_VALUE;
			openListStack.push(start);
			openList.put(boardToString(start), start);
			while (!openListStack.isEmpty()) {
				Node n = openListStack.pop();
				if(n.isVisited){
					openList.remove(boardToString(n));
				}
				else{
					n.isVisited = true;
					openListStack.push(n);
					ArrayList<Node> generatedMovesOnStage = n.generateMovement();
					for (int i = generatedMovesOnStage.size()-1; i >= 0; i--) {
						Node g = generatedMovesOnStage.get(i);
						System.out.println("inside generatedMovesOnStage FOR: "+g.toString()+"\n");
						Integer currentNodeStageValue = g.heuristicFunctionValue + g.movementCost;
						if(currentNodeStageValue > threshold){
							minF = Math.min(minF, currentNodeStageValue);
							continue;
							//continue with next operator
						}
						Node gPrime = openList.get(boardToString(g));
						if(gPrime != null && gPrime.isVisited){//gPrev.equals(g)&&
							continue;//continue with next operator
						}
						if(gPrime != null && !gPrime.isVisited){//gPrev.equals(g) && 
							if(gPrime.heuristicFunctionValue + gPrime.movementCost > currentNodeStageValue){
								openListStack.remove(gPrime); //removes ONLY FIRST occurance
								openList.remove(boardToString(gPrime));
							}
							else{
								continue;
								//continue with next operator
							}
						}
						if(g.equals(goals)){
							return g;
						}
						openListStack.push(g);
						openList.put(boardToString(g), g);
					}
				}
			}
			threshold = minF;
		}
		return null;
	}

	/*
	 * IDA*(Node start, Vector Goals)
		1. L <- make_stack and H <- make_hash_table-------------------
		2. t <- h(start)----------------------------
		3. While t != Infinity-----------------------
			1. minF <- Infinity----------------------
			2. L.insert(start)-------------------
			3. H.insert(start)-----------------
			4. While L is not empty--------
				1. n <- L.remove_front()----------
				2. If n is marked as “out”--------
					1. H.remove(n)---------
				2. Else-------------------------
					2. mark n as “out” and L.insert(n)------------
					3. For each allowed operator g on n-----------------
						1. If f(g) > t---------------------------
							1. minF <- min(minF, f(g))--------------------
							2. continue with the next operator
						2. If H contains g’=g and g’ marked “out”-----------------??????????
							1. continue with the next operator
						3. If H contains g’=g and g’ not marked “out”----------------????????
							1. If f(g’)>f(g)-----------------------
								1. remove g’ from L and H-----------------
							2. Else
								1. continue with the next operator

						4. If goal(g) then return path(g) //all the “out” nodes in L----------
						5. L.insert(g)-------
						6. H.insert(g)----------

		5. t <- minF
	4. Return false
	 * */
}
