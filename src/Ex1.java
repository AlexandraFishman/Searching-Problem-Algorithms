

public class Ex1 {

	public static void main(String[] args) {
		InputFile f= new InputFile();
		Node startingBoard = new Node(Utils.deepCopy(f.board));
		Node endBoard = f.endBoard();
		Node answer = null;
        Object algorithm = null;
        long start = System.nanoTime();
		//do factory here for algorithms in inputFile
		if(f.algorithmToUse.equals("BFS")){
			algorithm = new BFS();
			answer = ((frontieerSearch) algorithm).searchAlgorithm(startingBoard, endBoard);
		}
		else if(f.algorithmToUse.equals("A*"))
			algorithm = new AStar();
		else if(f.algorithmToUse.equals("IDA*"))
			algorithm = new IDAStar();
		else if(f.algorithmToUse.equals("DFBnB"))
			algorithm = new DFBnB();
		else if(f.algorithmToUse.equals("DFID"))
			algorithm = new DFID();
		long elapsedTime = System.nanoTime() - start;
		
		if(answer != null){
			OutputFile o = new OutputFile(answer.move, "retert", Integer.toString(answer.movementCost), Long.toString((long)(elapsedTime/1e9)));
		}
		
	}

}
