

public class Ex1 {

	public static void main(String[] args) {
		InputFile f= new InputFile();
		Node startingBoard = new Node(Utils.deepCopy(f.board));
		Node endBoard = Utils.endBoard(f.board.board);
		Node answer = null;
        Object algorithm = null;
        OutputFile o = null;
        int num = 0;
        long start = System.nanoTime();
		//do factory here for algorithms in inputFile
        if(!f.checkForImpositionedBlackTiles()){
        	o = new OutputFile("no path", "", "", "");
        }
        else if(f.algorithmToUse.equals("BFS")){
			algorithm = new BFS();
			answer = ((frontieerSearch) algorithm).searchAlgorithm(startingBoard, endBoard);
			num = ((frontieerSearch) algorithm).numberOfNodesCreated;
		}
		else if(f.algorithmToUse.equals("A*")){
			algorithm = new AStar();
			answer = ((frontieerSearch) algorithm).searchAlgorithm(startingBoard, endBoard);
			num = ((frontieerSearch) algorithm).numberOfNodesCreated;
		}
		else if(f.algorithmToUse.equals("IDA*")){
			algorithm = new IDAStar();
			answer = ((IDAStar) algorithm).ida_star_algorithm(startingBoard, endBoard);
			num = ((IDAStar) algorithm).numberOfNodesCreated;
		}
		else if(f.algorithmToUse.equals("DFBnB")){
			algorithm = new DFBnB();
			answer = ((DFBnB) algorithm).dfbnb_Algorithm(startingBoard, endBoard);
			num = ((DFBnB) algorithm).numberOfNodesCreated;
		}
		else if(f.algorithmToUse.equals("DFID")){
			algorithm = new DFID();
			answer = ((DFID) algorithm).dfid(startingBoard, endBoard);
			num = ((DFID) algorithm).numberOfNodesCreated;
		}
		
		long elapsedTime = System.nanoTime() - start;
		if(answer != null){
			if(f.printTime.equals("with time"))
				o = new OutputFile(answer.move, Integer.toString(num), Integer.toString(answer.movementCost), Double.toString(elapsedTime/1e9));
			else
				o = new OutputFile(answer.move, Integer.toString(num), Integer.toString(answer.movementCost), "");
		}
		else{
			if(f.printTime.equals("with time"))
				o = new OutputFile("no path", "", "", Double.toString(elapsedTime/1e9));
			else
				o = new OutputFile("no path", "", "", "");
		}
		
	}

}
