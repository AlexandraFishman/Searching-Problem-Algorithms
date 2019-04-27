import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Queue;

public abstract class frontieerSearch {

	Queue<Node> openListQueue;
	Hashtable<String, Node> previousStates = new Hashtable<>(); //closed-list
	Hashtable<String, Node> openListHash = new Hashtable<>();
	int numberOfNodesCreated = -1;

	public Node searchAlgorithm(Node startingBoard,Node goalStage){
		String openListHashkey = boardToString(startingBoard);
		openListHash.put(openListHashkey,startingBoard);
		openListQueue.add(startingBoard);

		previousStates = new Hashtable<>();
		while(!openListQueue.isEmpty()){
			Node currentNode = openListQueue.remove();
			numberOfNodesCreated++;
			openListHash.remove(boardToString(currentNode));
			String closedListHashkey = boardToString(currentNode) ;
			previousStates.put(closedListHashkey, currentNode);
			ArrayList<Node> generatedMovesOnStage = currentNode.generateMovement(); 
			/////
			System.out.println("generatedMoves array list: \n");
			for (int i = 0; i < generatedMovesOnStage.size(); i++) {
				System.out.println(generatedMovesOnStage.get(i).toString());
			}
			////
			if(Arrays.deepEquals(currentNode.stage, goalStage.stage)){ //if  started with a wining board
				System.out.println("moves: "+currentNode.move);
				return currentNode;
			}
			for (Node nextMove : generatedMovesOnStage) {
				openListHashkey = boardToString(nextMove);
				if(!openListHash.containsKey(openListHashkey) && !previousStates.containsKey(openListHashkey)){
					if(Arrays.deepEquals(nextMove.stage, goalStage.stage)){
						return nextMove;
					}
					openListHash.put(openListHashkey, nextMove);
					openListQueue.add(nextMove);
				}
			}
		}
		return null;
	}

	public String boardToString(Node n) {
		String msg ="";
		for (int i = 0; i < n.stage.length; i++) {
			for (int j = 0; j < n.stage[0].length; j++) {
				msg += n.stage[i][j];
			}
		}
		return msg;
	}
}
