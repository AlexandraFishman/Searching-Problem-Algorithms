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
	public ArrayList<Node> singleMoveLeft(){
		ArrayList<Node> result = new ArrayList<>();

		if(this.empty1.j+1  <= this.stage.length){
			Node a = shiftSingleTile(0,1,this.empty1);
			if(a!=null) result.add(a);
		}
		if(this.empty2.j+1  <= this.stage.length){
			Node b = shiftSingleTile(0,1,this.empty2);
			if(b!=null) result.add(b);
		}

//		String msg = "";
//		for (int i = 0; i < result.size(); i++) {
//			msg += result.get(i).toString() +"    \n "+i;
//		}
//		System.out.println(msg);
		return  result;
	}

	public ArrayList<Node> singleMoveRight(){
		ArrayList<Node> result = new ArrayList<>();

		if(this.empty1.j-1  <= this.stage.length){
			Node a = shiftSingleTile(0,-1,this.empty1);
			if(a!=null) result.add(a);
		}
		if(this.empty2.j-1  <= this.stage.length){
			Node b = shiftSingleTile(0,-1,this.empty2);
			if(b!=null) result.add(b);
		}

//		String msg = "";
//		for (int i = 0; i < result.size(); i++) {
//			msg += result.get(i).toString() +"    \n "+i;
//		}
//		System.out.println(msg);
		return  result;
	}

	public ArrayList<Node> singleMoveDown(){
		ArrayList<Node> result = new ArrayList<>();

		if(this.empty1.i-1  >= 0){
			Node a = shiftSingleTile(-1,0,this.empty1);
			if(a!=null) result.add(a);
		}
		if(this.empty2.i-1  >= 0){
			Node b = shiftSingleTile(-1,0,this.empty2);
			if(b!=null) result.add(b);
		}

//		String msg = "";
//		for (int i = 0; i < result.size(); i++) {
//			msg += result.get(i).toString() +"    \n "+i;
//		}
//		System.out.println(msg);
		return  result;
	}
	
	public ArrayList<Node> singleMoveUp(){
		ArrayList<Node> result = new ArrayList<>();

		if(this.empty1.i+1  <= this.stage[0].length){
			Node a = shiftSingleTile(1,0,this.empty1);
			if(a!=null) result.add(a);
		}
		if(this.empty2.i+1  <= this.stage[0].length){
			Node b = shiftSingleTile(1,0,this.empty2);
			if(b!=null) result.add(b);
		}

		String msg = "";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "+i;
		}
		System.out.println(msg);
		return  result;
	}
	
	private Node shiftSingleTile(int directionI, int directionJ, Cell empty){
		if(this.stage[empty.i+directionI][empty.j+directionJ] != null){
			Node currentMove = new Node();
			currentMove.stage = Utils.deepCopy(this.stage);
			int i = empty.i;
			int j = empty.j;
			//making move and updating empty cell
			int tmp = currentMove.stage[i+directionI][j+directionJ];
			currentMove.stage[i][j] = tmp;
			currentMove.stage[i+directionI][j+directionJ] = null;

			//updating cost
			currentMove.movementCost +=5;
			//
			return currentMove;
		}
		return null;
	} 

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
