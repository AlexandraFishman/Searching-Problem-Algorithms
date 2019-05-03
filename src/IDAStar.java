import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

public class IDAStar extends frontieerSearch{
	public Node ida_star_algorithm(Node start, Node goals) {
		if(start.equals(goals)){
			return start;
		}
		Stack<Node> L = new Stack<>();
		Hashtable<String, Node> H = new Hashtable<>();
		Integer t = start.heuristicFunctionValue;
		while (t != Integer.MAX_VALUE) {
			Integer minF = Integer.MAX_VALUE;
			L.push(start);
			H.put(boardToString(start), start);
			while (!L.isEmpty()) {
				Node n = L.pop();
				if(n.isOut){
					H.remove(n);
				}
				else{
					n.isOut = true;
					L.push(n);
					ArrayList<Node> generatedMovesOnStage = n.generateMovement();
					Node gPrev = null;
					for (Node g : generatedMovesOnStage){
//					for (Iterator<Node> i = generatedMovesOnStage.iterator(); i.hasNext();){
//						Node g = (Node)i.next();
//						if(i.hasNext()){
							if(g.heuristicFunctionValue > t){
								minF = Math.min(minF, g.heuristicFunctionValue);
//								g = (Node)i.next();
								//continue with next operator
							}
							if(gPrev != null){
								if(gPrev.equals(g) && H.containsKey(boardToString(g)) && gPrev.isOut){
//									g = (Node)i.next();
									//continue with next operator
								}
								if(gPrev.equals(g) && H.containsKey(boardToString(g)) && !gPrev.isOut){
									if(gPrev.heuristicFunctionValue > g.heuristicFunctionValue){
										L.remove(gPrev); //removes ONLY FIRST occurance
										H.remove(boardToString(gPrev));
									}
									else{
//										g = (Node)i.next();
										//continue with next operator
									}
								}
							}
							if(g.equals(goals)){
								return g;
							}
							L.push(g);
							H.put(boardToString(g), g);
							gPrev = g;
						}
//					}
				}
			}
			t = minF;
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
