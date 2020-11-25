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
