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
//		Cell c1 = new Cell();
//		Cell c2 = new Cell();
//		n.singleMoveLeft(c1,c2);
//		n.singleMoveRight(c1,c2);
//		n.singleMoveDown();
//		n.singleMoveUp();
//		
//		n.doubleMoveLeft();
//		n.doubleMoveRight();
//		n.doubleMoveDown();
//		n.doubleMoveUp();
		BFS bfs = new BFS();
		Node asd = this.endBoard();
		String s = bfs.BFS_Algorithm(n, asd);
		System.out.println(s);
		System.out.println("\n\n\n\nOriginal board:");
		System.out.println(this.toString()+"\n\n\n\n");
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
