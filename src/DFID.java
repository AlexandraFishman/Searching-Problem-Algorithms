import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DFID extends frontieerSearch {

	/**
	 * 
	 */

	boolean maxDepth = false;
	List<String> results = new ArrayList<String>();
	Hashtable<String, Node> openList = new Hashtable<>();


	public Node dfid(Node start, Node goal)
	{
		int depth = 0;
		Node n = null;
		while (!maxDepth)
		{
			maxDepth = true;
			n = dls(start, goal, depth);
			depth += 1;
		}
		return n;
	}

	public Node dls(Node node, Node goal, int depth)
	{
		openList.put(boardToString(node), node);
		node.isVisited = true;
		if(depth == 7)
			return null;
		if(depth == 0)
		{
			maxDepth = false;
			return null;
		}
		else if (node.equals(goal)) 
		{
			//set maxDepth to false if the node has children
			maxDepth = true;
			return node;
		}
		else 
		{
			ArrayList<Node> generatedMoves = node.generateMovement();
			for(Node g : generatedMoves)
			{
				if(!openList.contains(boardToString(g)) || !g.isVisited){
					Node n = dls(g, goal, depth-1);
					if(n != null && n.equals(goal)){
						return n;
					}
				}
			}
		}
		return null;
	}
}
