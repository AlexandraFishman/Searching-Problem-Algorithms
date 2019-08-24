import java.util.ArrayList;
import java.util.LinkedList;

public class Node {
	Integer [][] stage;
	Node father;
	String move;
	Cell emptyCell;
	int movementCost;
	int heuristicFunctionValue;
	boolean  isVisited = false;
	Directions direction;
	Integer nodeSerialNumber = 0;
	static Integer nodeCounter = 0;

	public Node(Integer [][] board){
		this.emptyCell = new Cell();
		this.stage = board.clone();
		this.father = null;
		this.direction = Directions.none;
		this.heuristicFunctionValue = manhattanDistanceSum();
		for (int i = 0; i < this.stage.length; i++) {
			for (int j = 0; j < this.stage[0].length; j++) {
				if(board[i][j] == null){
					this.emptyCell.i = i;
					this.emptyCell.j = j;
				}
			}
		}
		this.movementCost =0;
		this.move ="";
		this.nodeSerialNumber = nodeCounter++;
	}

	public ArrayList<Node> generateMovement(){
		ArrayList<Node> possibleMoves = new ArrayList<Node>();

		//single moves for the empty cell
		generateSingleMoves(possibleMoves);

		return  possibleMoves;
	}

	private void generateSingleMoves(ArrayList<Node> possibleMoves){
		ArrayList<Node> currentMoves;

		currentMoves = this.singleMoveLeft();
		if (this.direction == Directions.right && currentMoves.size() > 0){
			currentMoves.remove(0);
		}
		
		if(!currentMoves.isEmpty()){
			possibleMoves.addAll(currentMoves);
		}


		currentMoves = this.singleMoveUp();
		if (this.direction == Directions.down && currentMoves.size() > 0){
			currentMoves.remove(0);
		}
		
		if(!currentMoves.isEmpty()){
			possibleMoves.addAll(currentMoves);
		}

		currentMoves = this.singleMoveRight();
		if (this.direction == Directions.left && currentMoves.size() > 0){
			currentMoves.remove(0);
		}
		
		if(!currentMoves.isEmpty()){
			possibleMoves.addAll(currentMoves);
		}

		currentMoves = this.singleMoveDown();
		if (this.direction == Directions.up && currentMoves.size() > 0){
			currentMoves.remove(0);
		}
		
		if(!currentMoves.isEmpty()){
			possibleMoves.addAll(currentMoves);
		}

	}

	//returns node by one left  movement
	private ArrayList<Node> singleMoveLeft(){
		ArrayList<Node> result = new ArrayList<>();

		if(this.emptyCell.j+1  < this.stage[0].length){
			Node a = shiftSingleTile(0,1);
			if(a!=null){
				a.direction = Directions.left;
				result.add(a);
			}
		}

		return  result;
	}

	private ArrayList<Node> singleMoveRight(){
		ArrayList<Node> result = new ArrayList<>();

		if(this.emptyCell.j-1  >= 0){
			Node a = shiftSingleTile(0,-1);
			if(a!=null){
				a.direction = Directions.right;
				result.add(a);
			}
		}
		return  result;
	}

	private ArrayList<Node> singleMoveDown(){
		ArrayList<Node> result = new ArrayList<>();

		if(this.emptyCell.i-1  >= 0){
			Node a = shiftSingleTile(-1,0);
			if(a!=null){
				a.direction = Directions.down;
				result.add(a);
			}
		}
		return  result;
	}

	private ArrayList<Node> singleMoveUp(){
		ArrayList<Node> result = new ArrayList<>();

		if(this.emptyCell.i+1  < this.stage.length){
			Node a = shiftSingleTile(1,0);
			if(a!=null){
				a.direction = Directions.up;
				result.add(a);
			}
		}
		return  result;
	}

	private Node shiftSingleTile(int directionI, int directionJ){
		int i = this.emptyCell.i;
		int j = this.emptyCell.j;

		if(Board.blackTileNumber.contains(stage[i][j])){
			return null;
		}
		if(this.stage[i+directionI][j+directionJ] != null){
			Node currentMove = new Node(Utils.deepCopy(this.stage));
			//making move and updating empty cell
			//can't do anything
			int tmp = currentMove.stage[i+directionI][j+directionJ];
			currentMove.stage[i][j] = tmp;
			currentMove.stage[i+directionI][j+directionJ] = null;


			//updating cost
			//if tile is red add 30
			//if tile is black - it can't move
			//else 1
			if(Board.redTileNumber.contains(currentMove.stage[i][j])){
				currentMove.movementCost += 30 + this.movementCost;
			}

			if (Board.greenTileNumber.contains(currentMove.stage[i][j])) {
				currentMove.movementCost += 1 + this.movementCost;
			}

			//updating empty
			currentMove.emptyCell = new Cell();
			currentMove.emptyCell.i = i+directionI;
			currentMove.emptyCell.j = j+directionJ;

			//updating father
			currentMove.father = this;

			if(!Board.blackTileNumber.contains(stage[i][j])){
				currentMove.move = singleMovePathString(directionI, directionJ, tmp);
			}

			// update heuristic function value
			currentMove.heuristicFunctionValue = currentMove.manhattanDistanceSum();

			return currentMove;
		}
		return null;
	} 

	private String singleMovePathString(int directionI, int directionJ, Integer value) {
		String result ="";
		if(move!= null && move.length() > 0){
			result += move+"-"+value.toString();
		}
		else 
			result = value.toString();
		if(directionI == 1){
			result += "U";
		}
		else if(directionI == -1){
			result += "D";
		}
		else if(directionJ == 1){
			result += "L";
		}
		else if(directionJ == -1){
			result += "R";
		}
		return result;
	}

	@Override
	public String toString(){

		String msg;
		msg = "move: "+this.move+ "\n";
		for(int i=0; i<this.stage.length; i++){
			for(int j=0; j<this.stage[0].length; j++){
				msg += this.stage[i][j]+" ";
			}
			msg += "\n";
		}
		return  msg;

	}

	public int manhattanDistanceSum() {
		int row = 0, column = 0;
		int sum = 0;
		for (int i = 0; i < stage.length; i++) {
			for (int j = 0; j < stage[0].length; j++) {
				if(this.stage[i][j] != null){
					row = (this.stage[i][j]-1)/stage[0].length;
					column = (this.stage[i][j]-1)%stage[0].length;
					int distanceX = Math.abs(j-column);
					int distanceY = Math.abs(i-row);
					sum += (distanceX + distanceY) * colorOfTile(this.stage[i][j]); //need to mult by 1or30
				}
			}
		}
		if(sum == 0)
			return 0;
		//		sum*=5;//cost single move
		return sum;
	}

	private int colorOfTile(int value) {
		if(Board.redTileNumber.contains(value))
			return 30;
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!Node.class.isAssignableFrom(obj.getClass())) {
			return false;
		}

		final Node other = (Node) obj;

		for (int i = 0; i < stage.length; i++) {
			for (int j = 0; j < stage[0].length; j++) {
				if(this.stage[i][j] != other.stage[i][j])
					return false;
			}
		}
		return true;
	}

	public String boardToString() {
		String msg ="";
		for (int i = 0; i < this.stage.length; i++) {
			for (int j = 0; j < this.stage[0].length; j++) {
				msg += this.stage[i][j];
			}
		}
		return msg;
	}
}
