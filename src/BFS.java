import java.util.LinkedList;

public class BFS extends frontieerSearch{

	public BFS(){
		openListQueue = new LinkedList();
	}
	
	/**
	 * BFS(Node start, Vector Goals)
		1. L <- make_queue(start) and make_hash_table
		2. C <- make_hash_table
		3. While L not empty loop
			1. n <- L.remove_front()
			2. C <- n
			3. For each allowed operator on n
				1. g <- operator(n)
				2. If g not in C and not in L
					1. If goal(g) return path(g)
					2. L.insert(g)
		4. Return false
	 * */
}
