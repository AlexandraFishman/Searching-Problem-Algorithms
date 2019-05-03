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
				if(n.isOut){
					openList.remove(n);
				}
				else{
					n.isOut = true;
					openListStack.push(n);
					ArrayList<Node> generatedMovesOnStage = n.generateMovement();
					//					Node gPrev = null;
					//					for (Node g : generatedMovesOnStage){
					for (int i = generatedMovesOnStage.size()-1; i >= 0; i--) {
						Node g = generatedMovesOnStage.get(i);
						Integer currentNodeStageValue = g.heuristicFunctionValue + g.movementCost;
						if(currentNodeStageValue > threshold){
							minF = Math.min(minF, currentNodeStageValue);
							//continue with next operator
						}
						//						if(gPrev != null){
						if(openList.containsKey(boardToString(g)) && g.isOut){//gPrev.equals(g)&&
							//continue with next operator
						}
						if(openList.containsKey(boardToString(g)) && !g.isOut){//gPrev.equals(g) && 
							if(g.heuristicFunctionValue > currentNodeStageValue){
								openListStack.remove(g); //removes ONLY FIRST occurance
								openList.remove(boardToString(g));
							}
							else{
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
