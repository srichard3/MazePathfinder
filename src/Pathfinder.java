package main.pathfinder.informed;

import java.util.*;
import java.util.PriorityQueue;


/**
 * Samuel Richard
 * 
 * 
 * Maze Pathfinding algorithm that implements a basic, uninformed, breadth-first tree search.
 */
public class Pathfinder {
    
    /**
     * Given a MazeProblem, which specifies the actions and transitions available in the
     * search, returns a solution to the problem as a sequence of actions that leads from
     * the initial to a goal state.
     * 
     * @param problem A MazeProblem that specifies the maze, actions, transitions.
     * @return An ArrayList of Strings representing actions that lead from the initial to
     * the goal state, of the format: ["R", "R", "L", ...]
     */
    public static ArrayList<String> solve (MazeProblem problem) {

    	Boolean keyFound = false;
        ArrayList<String> solution = new ArrayList<String>();
        List<MazeState> graveyard= new ArrayList<MazeState>();
    	PriorityQueue<SearchTreeNode> frontier = new PriorityQueue<>();
    	SearchTreeNode initialNode = new SearchTreeNode(problem.getInitialState(), null, null, 
        		problem.getCost(problem.getInitialState()));
    	
        frontier.add(initialNode);

        while(frontier.size() != 0) {
        	SearchTreeNode parentNode = frontier.poll();
        	
        	if(!keyFound) {
	        	if (parentNode.state.equals(problem.getKeyState())) {	
	    			keyFound = true;
	    			graveyard.clear();
	    			frontier.clear();
	    			frontier.add(parentNode);
	    		}
	        	
        	} else {
        		
        		if (problem.isGoalState(parentNode.state)) {
        			while (parentNode.state != initialNode.state) {
        				solution.add(parentNode.action);
        				parentNode = parentNode.parent;
        			}
        			Collections.reverse(solution);
        			return solution;
        		}
        		
        	}
        	
        	
        	if (!(graveyard.contains(parentNode.state))) {
        		
	        	Map<String,MazeState> children = problem.getTransitions(parentNode.state);
	        	
	        	for (Map.Entry<String,MazeState> entry : children.entrySet()) {
	        		SearchTreeNode child = new SearchTreeNode(entry.getValue(), 
	        				entry.getKey(), parentNode, (parentNode.cost + problem.getCost(entry.getValue())));
	        		frontier.add(child);
	        		
	        	}
	        	
	        	graveyard.add(parentNode.state);
	        	
        	}
        	
        }
        
        return null;
        
    }
    
}
