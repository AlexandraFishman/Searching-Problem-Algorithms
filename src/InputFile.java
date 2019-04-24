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
							board[i][j] = Integer.parseInt(items[j]);
						}
						System.out.print(board[i][j] + " ");
					}
					System.out.println("");
				}
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		System.out.println(this.toString());

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
