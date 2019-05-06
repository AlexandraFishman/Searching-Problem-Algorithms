import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputFile {
	String algorithmToUse;
	String  printTime;
	int rows;
	int columns;
	Integer[][] board;

	InputFile(){
		try (BufferedReader br = Files.newBufferedReader(Paths.get("input.txt"))) {

			// read line by line
			String line = br.readLine();
			if(line != null){
				this.algorithmToUse = line;
				line = br.readLine();
				this.printTime = line;
				line = br.readLine();
				String boardSize = line;
				int x = boardSize.indexOf("x");
				this.rows = Integer.parseInt(boardSize.substring(0, x));
				this.columns = Integer.parseInt(boardSize.substring(x+1, boardSize.length()));
				this.board = new Integer[this.rows][this.columns];

				for(int i=0; i<rows; i++){
					line = br.readLine();
					String [] items = line.split(",");
					for(int j=0; j<columns; j++){           			
						if(!items[j].equals("_")){
							this.board[i][j] = Integer.parseInt(items[j]);
						}
					}
				}
			}
			
			

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
		Node n = new Node(this.board.clone());
		BFS bfs = new BFS();
		AStar astr = new AStar();
		IDAStar idasrt = new IDAStar();
		Node asd = this.endBoard();
		long start = System.nanoTime();
//		Node s = bfs.searchAlgorithm(n, asd);
//		Node s = astr.searchAlgorithm(n, asd);
//		Node s = idasrt.ida_star_algorithm(n, asd);
		long elapsedTime = System.nanoTime() - start;
		System.out.println(elapsedTime/1e9);
//		System.out.println("s="+s+"\n");
//		System.out.println("wining moves: "+s.move);
//		System.out.println("\ncost: "+s.movementCost);
//		System.out.println("\nOriginal board:");
		System.out.println(this.toString()+"\n");
//		System.out.println("Num:"+bfs.numberOfNodesCreated);
//		System.out.println("Num:"+astr.numberOfNodesCreated);
		DFBnB dfbnb = new DFBnB();
		System.out.println(dfbnb.dfbnb_Algorithm(n, asd));
	}

	public Node endBoard() {
		Node n= new Node(Utils.deepCopy(this.board.clone()));
		int d=1;
		int length = this.board.length;
		for (int i = 0; i < length; i++) {
			int width = this.board[0].length;
			for (int j = 0; j < width; j++) {
				n.stage[i][j] = d;
				if(d > (length*width-2)){
					n.stage[i][j] = null;
				}
//				System.out.print(n.stage[i][j]+" ");
				d++;
			}
//			System.out.println("");
		}
		return n;
	}
	
	@Override
	public String toString(){
		String msg;
		msg = this.algorithmToUse+"\n"+this.printTime+"\n"+this.columns+" "+this.rows+"\n";
		
		for(int i=0; i<rows; i++){
			for(int j=0; j<columns; j++){
				msg += this.board[i][j]+" ";
			}
			msg += "\n";
		}
		return  msg;
	}
}
