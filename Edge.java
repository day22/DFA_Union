/**
 * @author: Aidan Day, Noah Lafave
 * Theory of Computation Coding Project
 * 
 */
/**
 * Edge class acts as the connections between states in DFA
 */
public class Edge {
    private String input;
    private State transition;

    public Edge(String input, State transition) {
        this.input = input;
        this.transition = transition;
    }

    public String getInput() {
        return this.input;
    }

    public State getTransition() {
        return this.transition;
    }
}
