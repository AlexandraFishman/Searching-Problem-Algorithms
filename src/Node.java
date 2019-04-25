import java.util.ArrayList;

public class Node {
	Integer [][] stage;
	Node father;
	String move;
	Cell empty1;
	Cell empty2;
	int movementCost;

	public Node(){
		this.move ="";
		this.stage = new Integer [128][128];
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
		ArrayList<Node> currentMove = new ArrayList<Node>();

		//double moves
		currentMove = this.doubleMoveLeft();
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.doubleMoveUp();
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.doubleMoveRight();
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.doubleMoveDown();
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);

		//single moves for first  empty cell
		currentMove = this.singleMoveLeft(this.empty1, this.empty2);
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.singleMoveUp(this.empty1, this.empty2);
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.singleMoveRight(this.empty1, this.empty2);
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.singleMoveDown(this.empty1, this.empty2);
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);

		//single moves for second  empty cell
		currentMove = this.singleMoveLeft(this.empty2, this.empty1);
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.singleMoveUp(this.empty2, this.empty1);
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.singleMoveRight(this.empty2, this.empty1);
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);
		currentMove = this.singleMoveDown(this.empty2, this.empty1);
		if(!currentMove.isEmpty())
			possibleMoves.addAll(currentMove);

		return  possibleMoves;
	}

	//returns node by one left  movement
	private ArrayList<Node> singleMoveLeft(Cell empty1, Cell empty2){
		ArrayList<Node> result = new ArrayList<>();

		if(empty1.j+1  < this.stage.length){
			Node a = shiftSingleTile(0,1,empty1,empty2);
			if(a!=null) result.add(a);
		}

		String msg = "singleLeft:\n";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "+i;
		}
		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> singleMoveRight(Cell empty1, Cell empty2){
		ArrayList<Node> result = new ArrayList<>();

		if(empty1.j-1  < this.stage.length){
			Node a = shiftSingleTile(0,-1,empty1,empty2);
			if(a!=null) result.add(a);
		}

		String msg = "\nSingle right:\n";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "+i;
		}
		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> singleMoveDown(Cell empty1, Cell empty2){
		ArrayList<Node> result = new ArrayList<>();

		if(this.empty1.i-1  >= 0){
			Node a = shiftSingleTile(-1,0,empty1,empty2);
			if(a!=null) result.add(a);
		}

		String msg = "\nSingle down:\n";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "+i;
		}
		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> singleMoveUp(Cell empty1, Cell empty2){
		ArrayList<Node> result = new ArrayList<>();

		if(this.empty1.i+1  < this.stage.length){
			Node a = shiftSingleTile(1,0,empty1,empty2);
			if(a!=null) result.add(a);
		}

		String msg = "\nSingle up:\n";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "; //+i
		}
		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> doubleMoveLeft(){
		ArrayList<Node> result = new ArrayList<>();

		if((this.empty1.i+1  < this.stage.length) && (this.empty2.i+1  < this.stage.length) && this.sameColumnOrRow()){
			Node a = shiftDoubleTile(1, 0, this.empty1, this.empty2);
			if(a!=null) result.add(a);
		}

		String msg = "\nDouble Left:\n";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "; //+i
		}
		System.out.println(msg);

		return result;
	}

	private ArrayList<Node> doubleMoveRight(){
		ArrayList<Node> result = new ArrayList<>();

		if((this.empty1.j-1  >= 0) && (this.empty2.j-1 >= 0) && this.sameColumnOrRow()){
			Node a = shiftDoubleTile(0,-1,this.empty1,this.empty2);
			if(a!=null) result.add(a);
		}

		String msg = "\nDouble Right:\n";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "+i;
		}
		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> doubleMoveDown(){
		ArrayList<Node> result = new ArrayList<>();

		if((this.empty1.i-1  >= 0) && (this.empty2.i-1  >= 0) && this.sameColumnOrRow()){
			Node a = shiftDoubleTile(-1,0,this.empty1,this.empty2);
			if(a!=null) result.add(a);
		}

		String msg = "\nDouble Down:\n";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "+i;
		}
		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> doubleMoveUp(){
		ArrayList<Node> result = new ArrayList<>();

		if((this.empty1.i+1  < this.stage.length) && (this.empty2.i+1  < this.stage.length) && this.sameColumnOrRow()){
			Node a = shiftDoubleTile(1,0,this.empty1,this.empty2);
			if(a!=null) result.add(a);
		}

		String msg = "\nDouble up:\n";
		for (int i = 0; i < result.size(); i++) {
			msg += result.get(i).toString() +"    \n "; //+i
		}
		System.out.println(msg);
		return  result;
	}

	private Node shiftSingleTile(int directionI, int directionJ, Cell emptyToMove, Cell otherEmpty){
		if(this.stage[emptyToMove.i+directionI][emptyToMove.j+directionJ] != null){
			Node currentMove = new Node();
			currentMove.stage = Utils.deepCopy(this.stage);
			int i = emptyToMove.i;
			int j = emptyToMove.j;
			//making move and updating empty cell
			int tmp = currentMove.stage[i+directionI][j+directionJ];
			currentMove.stage[i][j] = tmp;
			currentMove.stage[i+directionI][j+directionJ] = null;

			//updating cost
			currentMove.movementCost +=5;

			//updating empty
			currentMove.empty1 = new Cell();
			currentMove.empty1.i = i+directionI;
			currentMove.empty1.j = j+directionJ;

			currentMove.empty2 = new Cell();
			currentMove.empty2.i = otherEmpty.i;
			currentMove.empty2.j = otherEmpty.j;

			//updating father
			currentMove.father = this;

			currentMove.move = pathString(directionI, directionJ, tmp);
			return currentMove;
		}
		return null;
	} 

	private Node shiftDoubleTile(int directionI, int directionJ, Cell empty1ToMove, Cell empty2ToMove){
		if((this.stage[empty1ToMove.i+directionI][empty1ToMove.j+directionJ] != null) &&
				this.stage[empty2ToMove.i+directionI][empty2ToMove.j+directionJ] != null){
			Node currentMove = new Node();
			currentMove.stage = Utils.deepCopy(this.stage);
			//first empty cell move
			int i = empty1ToMove.i;
			int j = empty1ToMove.j;
			//making move and updating empty1 cell
			int tmp = currentMove.stage[i+directionI][j+directionJ];
			currentMove.stage[i][j] = tmp;
			currentMove.stage[i+directionI][j+directionJ] = null;

			//updating cost
			currentMove.movementCost +=6;

			//updating empty
			currentMove.empty1 = new Cell();
			currentMove.empty1.i = i+directionI;
			currentMove.empty1.j = j+directionJ;

			//updating father ONLY ONCE on empty1 cell update
			currentMove.father = this;

			currentMove.move +="-" +pathString(directionI, directionJ, tmp);

			//second empty cell move
			int k = empty2ToMove.i;
			int l = empty2ToMove.j;
			//making move and updating empty1 cell
			tmp = currentMove.stage[k+directionI][l+directionJ];
			currentMove.stage[k][l] = tmp;
			currentMove.stage[k+directionI][l+directionJ] = null;

			//updating empty
			//			currentMove.empty1 = new Cell();
			//			currentMove.empty1.i = k+directionI;
			//			currentMove.empty1.j = l+directionJ;

			currentMove.empty2 = new Cell();
			currentMove.empty2.i = k+directionI;
			currentMove.empty2.j = l+directionJ;

			currentMove.move += "-"+pathString(directionI, directionJ, tmp);

			return currentMove;
		}
		return null;
	}

	private String pathString(int directionI, int directionJ, Integer value) {
		String result = value.toString();
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

	private boolean sameColumnOrRow() {
		return (((this.empty1.i == this.empty2.i) || (this.empty1.j == this.empty2.j))
				&& ((Math.abs(this.empty1.i - this.empty2.i) == 1) && (Math.abs(this.empty1.j - this.empty2.j) == 1)));
	}

	@Override
	public String toString(){
		String msg;
		msg = "cost: "+this.movementCost+"\n"+ "move: "+this.move+ "\nfather: \n";
		for(int i=0; i<this.father.stage.length; i++){
			for(int j=0; j<this.father.stage[0].length; j++){
				msg += this.father.stage[i][j]+" ";
			}
			msg += "\n";
		}
		msg += "empty1= "+this.father.empty1.toString();
		msg += "empty2= "+this.father.empty2.toString();

		msg += "current board: \n";
		for(int i=0; i<this.stage.length; i++){
			for(int j=0; j<this.stage[0].length; j++){
				msg += this.stage[i][j]+" ";
			}
			msg += "\n";
		}
		msg += "empty1= "+this.empty1.toString();
		msg += "empty2= "+this.empty2.toString();
		return  msg;

	}
}
