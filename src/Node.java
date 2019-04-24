import java.util.ArrayList;
import java.util.Hashtable;

public class Node {
	Integer [][] stage;
	Node father;
	String move;
	Cell empty1;
	Cell empty2;
	int movementCost;
	
	public Node(){
		
	}

	public Node(Integer [][] board){
		this.empty1 = new Cell();
		this.empty2 = new Cell();
		this.stage = board.clone();
		this.father = null;
		boolean foundFirstEmpty = false;
		for (int i = 0; i < this.stage.length; i++) {
			for (int j = 0; j < this.stage[0].length; j++) {
				if(board[i][j] == null){
					if(!foundFirstEmpty){
						this.empty1.i = i;
						this.empty1.j = j;
						foundFirstEmpty = true;
					}
					else{
						this.empty2.i = i;
						this.empty2.j =j;
					}
					//board[i][j] = 0;
				}
			}
		}
		this.movementCost =0;
		this.move ="";
	}

	public ArrayList<Node> generateMovement(){
		ArrayList<Node> possibleMoves = new ArrayList<Node>();
		for (int i = 0; i < this.stage.length; i++) {
			for (int j = 0; j < this.stage[0].length; j++) {
				//				Node n = neighborToMoveTo(i, j);
				//				if(n != null){
				//					possibleMoves.add(n);
				//				}
			}
		}
		return  null;
	}

	//returns node by one left  movement
	public ArrayList<Node> oneMoveLeft(){
		ArrayList<Node> result = new ArrayList<>();
		if(this.empty1.j+1  < this.stage.length){
			if(this.stage[this.empty1.i][this.empty1.j+1] != null){
				Node currentMove = new Node();
				currentMove.stage = Utils.deepCopy(this.stage);
				int i = this.empty1.i;
				int j =this.empty1.j;
				//making move and updating empty cell
				int tmp = currentMove.stage[i][j+1];
				currentMove.stage[i][j] = tmp;
				currentMove.stage[i][j+1] = null;

				//updating cost
				currentMove.movementCost +=5;
				//
				result.add(currentMove);
			}
		}
		String msg = "";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "+i;
		}
		System.out.println(msg);
		return  result;
	}

//	public Integer[][] deepCopy(Integer [][] orig){
//		Integer[][] copy = new Integer[orig.length][orig[0].length];
//		for(int i=0; i<copy.length; i++){
//			for(int j=0; j<copy[0].length; j++){
//				copy[i][j] = orig[i][j];
//			}
//		}
//		return copy;
//	}

	@Override
	public String toString(){
		String msg;
		msg = this.movementCost+"\n";

		for(int i=0; i<this.stage.length; i++){
			for(int j=0; j<this.stage[0].length; j++){
				msg += this.stage[i][j]+" ";
			}
			msg += "\n";
		}
		return  msg;

	}
}
