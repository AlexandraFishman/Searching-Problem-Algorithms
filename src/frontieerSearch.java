import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Queue;

public abstract class frontieerSearch {

	Queue<Node> openListQueue;
	Hashtable<String, Node> previousStates = new Hashtable<>(); //closed-list
	Hashtable<String, Node> openListHash = new Hashtable<>();
	int numberOfNodesCreated = 0;

	public Node searchAlgorithm(Node startingBoard,Node goalStage, Boolean printOpenListFlag){
		String openListHashkey = startingBoard.boardToString();
		openListHash.put(openListHashkey,startingBoard);
		openListQueue.add(startingBoard);

		previousStates = new Hashtable<>();
		while(!openListQueue.isEmpty()){
			Node currentNode = openListQueue.remove();
//			numberOfNodesCreated++;
			openListHash.remove(currentNode.boardToString());
			String closedListHashkey = currentNode.boardToString() ;
			previousStates.put(closedListHashkey, currentNode);
			ArrayList<Node> generatedMovesOnStage = currentNode.generateMovement(); 
			numberOfNodesCreated += generatedMovesOnStage.size();
			if(Arrays.deepEquals(currentNode.stage, goalStage.stage)){ //if  started with a wining board
//				System.out.println("moves: "+currentNode.move);
				return currentNode;
			}
			for (Node nextMove : generatedMovesOnStage) {
				openListHashkey = nextMove.boardToString();
				if(!openListHash.containsKey(openListHashkey) && !previousStates.containsKey(openListHashkey)){
					if(Arrays.deepEquals(nextMove.stage, goalStage.stage)){
						return nextMove;
					}
					openListHash.put(openListHashkey, nextMove);
					openListQueue.add(nextMove);
				}
			}
			/////
			if(printOpenListFlag){
				//						System.out.println("generatedMoves array list: \n");
				System.out.println("=====================================");
				for (Node node : openListQueue) {
					System.out.println(node.toString());
				}
				System.out.println("=====================================");
			}
			////
		}
		return null;
	}

//	public String boardToString(Node n) {
//		String msg ="";
//		for (int i = 0; i < n.stage.length; i++) {
//			for (int j = 0; j < n.stage[0].length; j++) {
//				msg += n.stage[i][j];
//			}
//		}
//		return msg;
//	}
}
