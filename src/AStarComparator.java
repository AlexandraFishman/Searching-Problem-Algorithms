import java.util.Comparator;

public class AStarComparator implements Comparator<Node>{
	@Override
	public int compare(Node a, Node b){
		int difference = (a.movementCost + a.heuristicFunctionValue) - (b.movementCost + b.heuristicFunctionValue);
		if(difference != 0)
			return difference;
		return (a.nodeSerialNumber - b.nodeSerialNumber);
	}
}
