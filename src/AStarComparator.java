import java.util.Comparator;

public class AStarComparator implements Comparator<Node>{
	@Override
	public int compare(Node a, Node b){
		int manDisA = a.manhattanDistanceSum();
		int	manDisB = b.manhattanDistanceSum();
		return ((a.movementCost + manDisA) - (b.movementCost + manDisB));
	}
}
