import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class DFA {
    
    private ArrayList<State> states;
    private ArrayList<String> alphabet;
    private String startState;
    private ArrayList<String> acceptState;

    public DFA(ArrayList<State> states) {
        this.states = states;
    }

    public DFA() {
        this.states = new ArrayList<State>();
        this.alphabet = new ArrayList<String>();
        this.acceptState = new ArrayList<String>();
    }

    public DFA(String fileName) {
        Scanner dfa1;
        this.states = new ArrayList<State>();
        this.alphabet = new ArrayList<String>();
        this.acceptState = new ArrayList<String>();

        try{
            dfa1 = new Scanner(new File(fileName));
            Scanner stateScanner = new Scanner(dfa1.nextLine());
            Scanner alphabetScanner = new Scanner(dfa1.nextLine());
            Scanner transitionScanner = new Scanner(dfa1.nextLine());
            this.startState = dfa1.nextLine();
            Scanner acceptScanner = new Scanner(dfa1.nextLine());

            stateScanner.useDelimiter(",");
            while(stateScanner.hasNext()) {
               this.addState(new State(stateScanner.next()));
            }
            
            alphabetScanner.useDelimiter(",");
            while(alphabetScanner.hasNext()) {
               this.addAlphabet(alphabetScanner.next());
            }

            acceptScanner.useDelimiter(",");
            while (acceptScanner.hasNext()) {
                this.acceptState.add(acceptScanner.next());
            }

            transitionScanner.useDelimiter(";");
            while(transitionScanner.hasNext()) {
                Scanner transitionDecoder = new Scanner(transitionScanner.next());
                String sState = transitionDecoder.next();
                String sAlpha = transitionDecoder.next();
                String eState = transitionDecoder.next();
                
                for (State state : this.getStates()) {
                    if (state.getName().equals(sState)) {
                        State endRefState = this.findStateByName(eState);
                        Edge newEdge = new Edge(sAlpha, endRefState);
                        state.addEdge(newEdge);
                    }
                }
            }
            dfa1.close();
        }catch (FileNotFoundException fnfe) {
            System.out.println("File not Found");
        }
    }
    

    public void addState(State state) {

        this.states.add(state);
    }

    public void addAlphabet(String alpha) {
        this.alphabet.add(alpha);
    }

    public ArrayList<State> getStates() {
        return this.states;
    }

    public ArrayList<String> getAlphabet() {
        return this.alphabet;
    }

    public String toString(PrintWriter fileWriter) {
            for (State state : this.states) {
                System.out.print(state.getName() + " ");
                fileWriter.append(state.getName() + " ");
    
            }
            System.out.println();
            fileWriter.append("\n");
            for (String string : this.alphabet) {
                System.out.print(string + " ");
                fileWriter.append(string + " ");
            }
            System.out.println();
            fileWriter.append("\n");
            for (State state : this.states) {
                for (Edge edge : state.getEdge()) {
                    System.out.print(state.getName() + " x " + edge.getInput() + " = " + edge.getTransition().getName()+ "; ");
                    fileWriter.append(state.getName() + " x " + edge.getInput() + " = " + edge.getTransition().getName()+ "; ");
                }
            }
            System.out.println();
            fileWriter.append("\n");

            System.out.println(this.startState);
            fileWriter.append(this.startState);
            fileWriter.append("\n");

            for (String accepString : this.acceptState) {
                System.out.print(accepString + " ");
                fileWriter.append(accepString + " ");
            }        
            System.out.println();
            fileWriter.append("\n");

        return "";
    }

    public State findStateByName(String name) {
        State newState = null;
        for (State state : this.states) {
            if(state.getName().equals(name)) {
                newState = state;
            }
        }
        if (newState != null) {
            return newState;
        } else {
            return null;            
        }
    }
    public String getStartState() {
        return startState;
    }
    public void setStartState(String state) {
        this.startState = state;
    }
    public ArrayList<String> getAcceptStates() {
        return this.acceptState;
    }
    public void addAcceptState(String state) {
        this.acceptState.add(state);
    }
}
