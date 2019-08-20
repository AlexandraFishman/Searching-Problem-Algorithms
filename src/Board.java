import java.util.LinkedList;

public class Board {
	
	static LinkedList<Integer> blackTileNumber = new LinkedList<>();
	static LinkedList<Integer> redTileNumber = new LinkedList<>();
	static LinkedList<Integer> greenTileNumber = new LinkedList<>();
	Integer[][] board;
	
	public Board(Integer[][] board, LinkedList<Integer> blackTileNumber, LinkedList<Integer> redTileNumber) {
		this.board = board;
		Board.blackTileNumber = blackTileNumber;
		Board.redTileNumber = redTileNumber;
		
	}
	
//	public Node endBoard() {
//		Node n = new Node(Utils.deepCopy(this.board.clone()));
//		int d=1;
//		int length = this.board.length;
//		for (int i = 0; i < length; i++) {
//			int width = this.board[0].length;
//			for (int j = 0; j < width; j++) {
//				n.stage[i][j] = d;
//				Board.greenTileNumber.add(d);
//				if(d > (length*width-1)){
//					n.stage[i][j] = null;
//				}
//				d++;
//			}
//		}
//		return n;
//	}

	@Override
	public String toString(){
		String msg = "";
		for(int i=0; i<board[0].length; i++){
			for(int j=0; j<board.length; j++){
				msg += this.board[i][j]+" ";
			}
			msg += "\n";
		}
		return  msg;
	}
}
