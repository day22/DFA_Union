/**
 * @author: Aidan Day, Noah Lafave
 * Theory of Computation Coding Project
 * 
 */

import java.util.ArrayList;

public class State {
    
    private String name;
    private ArrayList<Edge> edges;

    public State(String name, ArrayList<Edge> edges) {
        this.name = name;
        this.edges = edges;
    }

    public State(String name) {
        this.name = name;
        this.edges = new ArrayList<Edge>();
    }

    public String getName() {
        return this.name;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public ArrayList<Edge> getEdge() {
        return edges;
    }

    /**
     * when called on state returns the state that would result from input on current state 
     * @param input
     * @return
     */
    public State getTransitionOn(String input) {
        for (Edge edge : this.getEdge()) {
            if(edge.getInput().equals(input)) {
                return edge.getTransition();
            }
        }
        return null;
    }

}
