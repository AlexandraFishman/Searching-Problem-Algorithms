
public class DFBnB {
	public Node dfbnb_Algorithm() {

	}
}

/**
 * DFBnB(Node start, Vector Goals)
		1. L <- make_stack(start) and H <- make_hash_table(start)
		2. result <- null, t <- Infinity // should be set to a strict upper bound in an infinite graph
		3. While L is not empty

			1. n <- L.remove_front()
			2. If n is marked as “out”
				1. H.remove(n)
			3. Else
				1. mark n as “out” and L.insert(n)
				2. N <- apply all of the allowed operators on n
				3. sort the nodes in N according to their f values (increasing order)
				4. For each node g from N according to the order of N
					1. If f(g) >= t
						1. remove g and all the nodes after it from N
						2. Else If H contains g’=g and g’ is marked as “out”
							1. remove g from N
						3. Else If H contains g’=g and g’ is not marked as “out”
							1. If f(g’)<=f(g)
								1. remove g from N
							2. Else
								1. remove g’ from L and from H
						4. Else If goal(g) // if we reached here, f(g) < t
							1. t <- f(g)
							2. result <- path(g) // all the “out” nodes in L
							3. remove g and all the nodes after it from N

						5. insert N in a reverse order to L and H

		4. Return result/
 */