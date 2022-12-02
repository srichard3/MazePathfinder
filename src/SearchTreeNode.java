package main.pathfinder.informed;

/**
 * Samuel Richard
 * 
 * SearchTreeNode that is used in the Search algorithm to construct the Search
 * tree.
 */
class SearchTreeNode implements Comparable<SearchTreeNode>{
    
    MazeState state;
    String action;
    SearchTreeNode parent;
    int cost;
    
    /**
     * Constructs a new SearchTreeNode to be used in the Search Tree.
     * 
     * @param state The MazeState (row, col) that this node represents.
     * @param action The action that *led to* this state / node.
     * @param parent Reference to parent SearchTreeNode in the Search Tree.
     * @param visited True if node is visited.
     * @param cost Total cost g(n) + h(n) for a node.
     */
    public SearchTreeNode (MazeState state, String action, SearchTreeNode parent, int cost) {
        this.state = state;
        this.action = action;
        this.parent = parent;
        this.cost = cost;
    }
    
    /**
     * Compares SearchTreeNodes in a PriorityQueue. Will be < 0
     * when this node has less than the other.
     * 
     * @param otherNode Other node for PriorityQueue to compare to.
     */
    public int compareTo (SearchTreeNode otherNode) {
        return this.cost - otherNode.cost;
    }
    
}
