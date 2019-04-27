import java.util.Comparator;

public class AStarComparator implements Comparator<Node>{
	@Override
	public int compare(Node a, Node b){
		return ((a.movementCost + a.heuristicFunctionValue) - (b.movementCost + b.heuristicFunctionValue));
	}
}
