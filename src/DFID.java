import java.util.ArrayList;
import java.util.List;

public class DFID {

	/**
	 * 
	 */
	
	boolean maxDepth = false;
	List<String> results = new ArrayList<String>();

	public Node dfid(Node start, Node goal)
	{
	    int depth = 0;
	    Node n = null;
	    while (!maxDepth)
	    {
//	        System.out.println(results);
	        maxDepth = true;
	        n = dls(start, goal, depth);
	        depth += 1;
	    }
	    return n;
	}

	public Node dls(Node node, Node goal, int depth)
	{
//	    System.out.println(depth);
	    if (node.equals(goal)) //depth == 0 || 
	    {
	        //set maxDepth to false if the node has children
	        if (node!=null)
	        {
	            maxDepth = true;
	            return node;
	        }
//	        results.add(node);??????????
	    }
	    else if (depth > 0)
	    {
	    	ArrayList<Node> generatedMoves = node.generateMovement();
	        for(Node g : generatedMoves)
	        {
	            return dls(g, goal, depth-1);
	        }
	    }
	    else 
	    	maxDepth = false;
	    return node;
	}
}
