import java.util.ArrayList;

public class Node {
	Integer [][] stage;
	Node father;
	String move;
	Cell empty1;
	Cell empty2;
	int movementCost;
	int heuristicFunctionValue;

//	public Node(){
//		this.move ="";
//		this.stage = new Integer [128][128];
//
//	}

	public Node(Integer [][] board){
		this.empty1 = new Cell();
		this.empty2 = new Cell();
		this.stage = board.clone();
		this.father = null;
		this.heuristicFunctionValue = manhattanDistanceSum();
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

		Cell firstEmpty, secondEmpty;
		if(this.empty1.i < this.empty2.i){ 
			firstEmpty = this.empty1;
			secondEmpty = this.empty2;
		}
		else if(this.empty1.i > this.empty2.i){
			firstEmpty = this.empty2;
			secondEmpty = this.empty1;
		}
		else if(this.empty1.j < this.empty2.j){ //same row
			firstEmpty = this.empty1;
			secondEmpty = this.empty2;
		}
		else{
			firstEmpty = this.empty2;
			secondEmpty = this.empty1;
		}
		//single moves for first  empty cell
		generateSingleMoves(possibleMoves, firstEmpty, secondEmpty);

		generateSingleMoves(possibleMoves, secondEmpty, firstEmpty);

		return  possibleMoves;
	}

	private void generateSingleMoves(ArrayList<Node> possibleMoves, Cell firstEmpty, Cell secondEmpty) {
		ArrayList<Node> currentMoves;
		currentMoves = this.singleMoveLeft(firstEmpty, secondEmpty);
		if(!currentMoves.isEmpty())
			possibleMoves.addAll(currentMoves);
		currentMoves = this.singleMoveUp(firstEmpty, secondEmpty);
		if(!currentMoves.isEmpty())
			possibleMoves.addAll(currentMoves);
		currentMoves = this.singleMoveRight(firstEmpty, secondEmpty);
		if(!currentMoves.isEmpty())
			possibleMoves.addAll(currentMoves);
		currentMoves = this.singleMoveDown(firstEmpty, secondEmpty);
		if(!currentMoves.isEmpty())
			possibleMoves.addAll(currentMoves);
	}

	//returns node by one left  movement
	private ArrayList<Node> singleMoveLeft(Cell empty1, Cell empty2){
		ArrayList<Node> result = new ArrayList<>();

		if(empty1.j+1  < this.stage[0].length){
			Node a = shiftSingleTile(0,1,empty1,empty2);
			if(a!=null) result.add(a);
		}

		//		String msg = "singleLeft:\n";
		//		for (int i = 0; i < result.size(); i++) {
		//			msg += result.get(i).toString() +"    \n "+i;
		//		}
		//		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> singleMoveRight(Cell empty1, Cell empty2){
		ArrayList<Node> result = new ArrayList<>();

		if(empty1.j-1  >= 0){
			Node a = shiftSingleTile(0,-1,empty1,empty2);
			if(a!=null) result.add(a);
		}

		//		String msg = "\nSingle right:\n";
		//		for (int i = 0; i < result.size(); i++) {
		//			msg += result.get(i).toString() +"    \n "+i;
		//		}
		//		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> singleMoveDown(Cell empty1, Cell empty2){
		ArrayList<Node> result = new ArrayList<>();

		if(empty1.i-1  >= 0){
			Node a = shiftSingleTile(-1,0,empty1,empty2);
			if(a!=null) result.add(a);
		}

		//		String msg = "\nSingle down:\n";
		//		for (int i = 0; i < result.size(); i++) {
		//			msg += result.get(i).toString() +"    \n "+i;
		//		}
		//		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> singleMoveUp(Cell empty1, Cell empty2){
		ArrayList<Node> result = new ArrayList<>();

		if(empty1.i+1  < this.stage.length){
			Node a = shiftSingleTile(1,0,empty1,empty2);
			if(a!=null) result.add(a);
		}

		//		String msg = "\nSingle up:\n";
		//		for (int i = 0; i < result.size(); i++) {
		//			msg += result.get(i).toString() +"    \n "; //+i
		//		}
		//		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> doubleMoveLeft(){
		ArrayList<Node> result = new ArrayList<>();

		if((this.empty1.i+1  < this.stage.length) && (this.empty2.i+1  < this.stage.length) && this.sameColumn()){
			Node a = shiftDoubleTile(1, 0, this.empty1, this.empty2);
			if(a!=null) result.add(a);
		}

		//		String msg = "\nDouble Left:\n";
		//		for (int i = 0; i < result.size(); i++) {
		//			msg += result.get(i).toString() +"    \n "; //+i
		//		}
		//		System.out.println(msg);

		return result;
	}

	private ArrayList<Node> doubleMoveRight(){
		ArrayList<Node> result = new ArrayList<>();

		if((this.empty1.j-1  >= 0) && (this.empty2.j-1 >= 0) && this.sameColumn()){
			Node a = shiftDoubleTile(0,-1,this.empty1,this.empty2);
			if(a!=null) result.add(a);
		}

		//		String msg = "\nDouble Right:\n";
		//		for (int i = 0; i < result.size(); i++) {
		//			msg += result.get(i).toString() +"    \n "+i;
		//		}
		//		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> doubleMoveDown(){
		ArrayList<Node> result = new ArrayList<>();

		if((this.empty1.i-1  >= 0) && (this.empty2.i-1  >= 0) && this.sameRow()){
			Node a = shiftDoubleTile(-1,0,this.empty1,this.empty2);
			if(a!=null) result.add(a);
		}

		//		String msg = "\nDouble Down:\n";
		//		for (int i = 0; i < result.size(); i++) {
		//			msg += result.get(i).toString() +"    \n "+i;
		//		}
		//		System.out.println(msg);
		return  result;
	}

	private ArrayList<Node> doubleMoveUp(){
		ArrayList<Node> result = new ArrayList<>();

		if((this.empty1.i+1  < this.stage.length) && (this.empty2.i+1  < this.stage.length) && this.sameRow()){
			Node a = shiftDoubleTile(1,0,this.empty1,this.empty2);
			if(a!=null) result.add(a);
		}

		//		String msg = "\nDouble up:\n";
		//		for (int i = 0; i < result.size(); i++) {
		//			msg += result.get(i).toString() +"    \n "; //+i
		//		}
		//		System.out.println(msg);
		return  result;
	}

	private Node shiftSingleTile(int directionI, int directionJ, Cell emptyToMove, Cell otherEmpty){
		if(this.stage[emptyToMove.i+directionI][emptyToMove.j+directionJ] != null){
			Node currentMove = new Node(Utils.deepCopy(this.stage));
			int i = emptyToMove.i;
			int j = emptyToMove.j;
			//making move and updating empty cell
			int tmp = currentMove.stage[i+directionI][j+directionJ];
			currentMove.stage[i][j] = tmp;
			currentMove.stage[i+directionI][j+directionJ] = null;

			//updating cost
			currentMove.movementCost += 5 + this.movementCost;

			//updating empty
			currentMove.empty1 = new Cell();
			currentMove.empty1.i = i+directionI;
			currentMove.empty1.j = j+directionJ;

			currentMove.empty2 = new Cell();
			currentMove.empty2.i = otherEmpty.i;
			currentMove.empty2.j = otherEmpty.j;

			//updating father
			currentMove.father = this;

			currentMove.move = singleMovePathString(directionI, directionJ, tmp);

			// update heuristic function value
			currentMove.heuristicFunctionValue = currentMove.manhattanDistanceSum();
			
			return currentMove;
		}
		return null;
	} 

	private Node shiftDoubleTile(int directionI, int directionJ, Cell empty1ToMove, Cell empty2ToMove){
		if((this.stage[empty1ToMove.i+directionI][empty1ToMove.j+directionJ] != null) &&
				this.stage[empty2ToMove.i+directionI][empty2ToMove.j+directionJ] != null){
			Node currentMove = new Node(Utils.deepCopy(this.stage));
			//first empty cell move
			int i = empty1ToMove.i;
			int j = empty1ToMove.j;
			//making move and updating empty1 cell
			int tmp = currentMove.stage[i+directionI][j+directionJ];
			currentMove.stage[i][j] = tmp;
			currentMove.stage[i+directionI][j+directionJ] = null;

			//updating cost
			if((directionI == 1) || (directionI == -1))
				currentMove.movementCost += 7 + this.movementCost;
			if((directionJ == 1) || (directionJ == -1))
				currentMove.movementCost += 6 + this.movementCost;

			//updating empty
			currentMove.empty1 = new Cell();
			currentMove.empty1.i = i+directionI;
			currentMove.empty1.j = j+directionJ;

			//updating father ONLY ONCE on empty1 cell update
			currentMove.father = this;

			int firstCellValue = tmp;

			//second empty cell move
			int k = empty2ToMove.i;
			int l = empty2ToMove.j;
			//making move and updating empty1 cell
			tmp = currentMove.stage[k+directionI][l+directionJ];
			int secondCellValue = tmp;
			currentMove.stage[k][l] = tmp;
			currentMove.stage[k+directionI][l+directionJ] = null;

			//updating empty
			currentMove.empty2 = new Cell();
			currentMove.empty2.i = k+directionI;
			currentMove.empty2.j = l+directionJ;

			currentMove.move += doubleMovePathString(directionI, directionJ, firstCellValue, secondCellValue);

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

	private String doubleMovePathString(int directionI, int directionJ, Integer value1, Integer value2) {
		String result ="";
		if(move!= null && move.length() > 0){
			result += move+"-"+value1.toString()+"&"+value2.toString();
		}
		else 
			result = value1.toString()+"&"+value2.toString();
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

	private boolean sameColumn() {
		return ((this.empty1.j == this.empty2.j) && (Math.abs(this.empty1.i - this.empty2.i) == 1));
	}

	private boolean sameRow() {
		return ((this.empty1.i == this.empty2.i) && (Math.abs(this.empty1.j - this.empty2.j) == 1));
	}

	@Override
	public String toString(){
		//		String msg;
		//		msg = "cost: "+this.movementCost+"\n"+ "move: "+this.move+ "\nfather: \n";
		//		for(int i=0; i<this.father.stage.length; i++){
		//			for(int j=0; j<this.father.stage[0].length; j++){
		//				msg += this.father.stage[i][j]+" ";
		//			}
		//			msg += "\n";
		//		}
		//		msg += "empty1= "+this.father.empty1.toString();
		//		msg += "empty2= "+this.father.empty2.toString();
		//
		//		msg += "current board: \n";
		//		for(int i=0; i<this.stage.length; i++){
		//			for(int j=0; j<this.stage[0].length; j++){
		//				msg += this.stage[i][j]+" ";
		//			}
		//			msg += "\n";
		//		}
		//		msg += "empty1= "+this.empty1.toString();
		//		msg += "empty2= "+this.empty2.toString();
		//		return  msg;


		String msg;
		msg = "move: "+this.move+ "\n";
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
					int distanceX = Math.abs(i-column);
					int distanceY = Math.abs(j-row);
					sum += distanceX + distanceY;
				}
			}
		}
		sum*=5;//cost single move
		if(this.sameColumn() || this.sameRow()){
			sum -= 3;//estimated savings for double moves
		}
		return sum;
	}
}
