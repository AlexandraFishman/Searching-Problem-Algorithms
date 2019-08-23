import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class InputFile {
	String algorithmToUse;
	String printTime;
	boolean printOpenList;
	int rows;
	int columns;
	Board board;
	
	InputFile(){
		try (BufferedReader br = Files.newBufferedReader(Paths.get("input.txt"))) {

			// read line by line
			String line = br.readLine();
			if(line != null){
				this.algorithmToUse = line;

				line = br.readLine();
				this.printTime = line;

				line = br.readLine();
				this.printOpenList = printOpenList(line);

				line = br.readLine();
				LinkedList<Integer> blackTileNumber = new LinkedList<>();
				if(!line.equals("Black:")){
					String [] blackTiles = line.substring(6).trim().split(",");
					for(String s : blackTiles){
						blackTileNumber.add(Integer.parseInt(s));
					}
				}

				line = br.readLine();
				LinkedList<Integer> redTileNumber = new LinkedList<>();
				if(!line.equals("Red:")){
					String [] redTiles = line.substring(4).trim().split(",");
					for(String s : redTiles){
						redTileNumber.add(Integer.parseInt(s));
					} 
				}

				line = br.readLine();
				String boardSize = line;
				int x = boardSize.indexOf("x");
				this.rows = Integer.parseInt(boardSize.substring(0, x));
				this.columns = Integer.parseInt(boardSize.substring(x+1, boardSize.length()));
				Integer[][] board = new Integer[this.rows][this.columns];

				for(int i=0; i<rows; i++){
					line = br.readLine();
					String [] items = line.split(",");
					for(int j=0; j<columns; j++){           			
						if(!items[j].equals("_")){
							board[i][j] = Integer.parseInt(items[j]);
						}
					}
				}
				this.board = new Board(board, blackTileNumber, redTileNumber);
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

//				Node n = new Node(this.board.clone());
//				BFS bfs = new BFS();
		//		AStar astr = new AStar();
		//		IDAStar idasrt = new IDAStar();
		//		DFID dfid = new DFID();
//				Node asd = this.endBoard();
		//		long start = System.nanoTime();
//				Node s = bfs.searchAlgorithm(n, asd);
		//		Node s = astr.searchAlgorithm(n, asd);
		//		Node s = idasrt.ida_star_algorithm(n, asd);
		//		Node s = dfid.dfid(n, asd);
		//		long elapsedTime = System.nanoTime() - start;
		//		System.out.println(elapsedTime/1e9);
//				System.out.println("s="+s+"\n");
		//		System.out.println("wining moves: "+s.move);
		//		System.out.println("\ncost: "+s.movementCost);
//				System.out.println("\nOriginal board:");
//				System.out.println(this.toString()+"\n");
		//		System.out.println("Num:"+bfs.numberOfNodesCreated);
		//		System.out.println("Num:"+astr.numberOfNodesCreated);
		//		DFBnB dfbnb = new DFBnB();
		//		System.out.println(dfbnb.dfbnb_Algorithm(n, asd));
	}

	public boolean checkForImpositionedBlackTiles() {
		for(Integer i : Board.blackTileNumber){
			int row = (i-1)/board.board[0].length;
			int column = (i-1)%board.board[0].length;
			if(i != board.board[row][column])
				return false;
		}
		return true;
	}

	private boolean printOpenList(String line) {
		if(line.equals("no open"))
			return false;
		return true;
	}


	@Override
	public String toString(){
		String msg;
		msg = this.algorithmToUse+"\n"+this.printTime+"\n"+this.columns+" "+this.rows+"\n";

		msg = this.board.toString();
		return  msg;
	}
}
