
public class Utils {

	public static Integer[][] deepCopy(Integer [][] orig){
		Integer[][] copy = new Integer[orig.length][orig[0].length];
		for(int i=0; i<copy.length; i++){
			for(int j=0; j<copy[0].length; j++){
				copy[i][j] = orig[i][j];
			}
		}
		return copy;
	}
	
	public static Integer[][] deepCopy(Board orig){
		Integer[][] copy = new Integer[orig.board.length][orig.board[0].length];
		for(int i=0; i<copy.length; i++){
			for(int j=0; j<copy[0].length; j++){
				copy[i][j] = orig.board[i][j];
			}
		}
		return copy;
	}
	
	public static Node endBoard(Integer[][] board) {
		Node n = new Node(Utils.deepCopy(board.clone()));
		int d=1;
		int length = board.length;
		for (int i = 0; i < length; i++) {
			int width = board[0].length;
			for (int j = 0; j < width; j++) {
				n.stage[i][j] = d;
				Board.greenTileNumber.add(d);
				if(d > (length*width-1)){
					n.stage[i][j] = null;
				}
				d++;
			}
		}
		Board.greenTileNumber.removeAll(Board.blackTileNumber);
		Board.greenTileNumber.removeAll(Board.redTileNumber);
		return n;
	}
	
}
