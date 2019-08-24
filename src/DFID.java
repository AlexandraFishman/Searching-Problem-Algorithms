import java.util.ArrayList;
import java.util.Hashtable;

public class DFID{

	boolean maxDepth = false;
	Hashtable<String, Node> pathUpToMe = new Hashtable<>();
	int numberOfNodesCreated = 1;


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
		pathUpToMe.put(node.boardToString(), node);
		if(depth == 100){
			pathUpToMe.remove(node.boardToString());
			maxDepth = true;
			return null;
		}
		if(depth == 0)
		{
			pathUpToMe.remove(node.boardToString());
			maxDepth = false;
			return null;
		}
		else if (node.equals(goal)) 
		{
			maxDepth = true;
			return node;
		}
		else 
		{
			ArrayList<Node> generatedMoves = node.generateMovement();
			for(Node g : generatedMoves)
			{
				Node gPrime = pathUpToMe.get(g.boardToString());
				numberOfNodesCreated++;
				if(!g.equals(goal)){
					if(gPrime == null){  
						Node n = dls(g, goal, depth-1);
						if(n != null){ 
							maxDepth = true;
							return n;
						}
					}
				}
				else{
					maxDepth = true;
					return g;
				}
			}
		}
		pathUpToMe.remove(node.boardToString());
		return null;
	}
}
