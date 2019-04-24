import java.util.ArrayList;
import java.util.Hashtable;

public class Node {
	Integer [][] stage;
	Node father;
	String move;
	Cell empty1;
	Cell empty2;
	int movementCost;
	
	public ArrayList<Node> generateMovement(){
		ArrayList<Node> possibleMoves = new ArrayList<Node>();
		for (int i = 0; i < this.stage.length; i++) {
			for (int j = 0; j < this.stage[0].length; j++) {
//				Node n = neighborToMoveTo(i, j);
				if(n != null){
					possibleMoves.add(n);
				}
			}
		}
		return  null;
	}
	
	//returns node by one left  movement
	private ArrayList<Node> oneMoveLeft(){
		ArrayList<Node> result = new ArrayList<>();
		if(this.empty1.j+1  < this.stage.length){
			if(this.stage[this.empty1.i][this.empty1.j+1] != null){
				Node currentMove = new Node();
				currentMove.stage = this.stage.clone();
				int i = this.empty1.i;
				int j =this.empty1.j;
				//making move and updating empty cell
				int tmp = currentMove.stage[i][j+1];
				currentMove.stage[i][j] = null;
				currentMove.stage[i][j+1] = tmp;
				
				//updating cost
				this.movementCost +=5;
				//
				
				
			}
		}
		
		return  result;
	}
}
