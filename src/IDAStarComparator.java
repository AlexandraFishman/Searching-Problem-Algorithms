import java.util.Comparator;

public class IDAStarComparator implements Comparator<Node>{
	@Override
	public int compare(Node a, Node b){
		return (-1)*((a.movementCost + a.heuristicFunctionValue) - (b.movementCost + b.heuristicFunctionValue));
	}
}
