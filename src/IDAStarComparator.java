import java.util.Comparator;

public class IDAStarComparator implements Comparator<Node>{
	@Override
	public int compare(Node a, Node b){
		int difference = (-1)*((a.movementCost + a.heuristicFunctionValue) - (b.movementCost + b.heuristicFunctionValue));
		if(difference != 0)
			return difference;
		return (-1)*(a.nodeSerialNumber - b.nodeSerialNumber);
	}
}
