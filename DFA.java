/**
 * @author: Aidan Day, Noah Lafave
 * Theory of Computation Coding Project
 * 
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class DFA {
    //set of states
    private ArrayList<State> states;

    //alphabet set
    private ArrayList<String> alphabet;

    //sole start state name
    private String startState;

    //set of accept states 
    private ArrayList<String> acceptState;

    public DFA() {
        this.states = new ArrayList<State>();
        this.alphabet = new ArrayList<String>();
        this.acceptState = new ArrayList<String>();
    }

    /**
     * I/O constructor for new DFA 
     * @param fileName
     */
    public DFA(String fileName) {
        Scanner dfa1;
        this.states = new ArrayList<State>();
        this.alphabet = new ArrayList<String>();
        this.acceptState = new ArrayList<String>();

        try {
            dfa1 = new Scanner(new File(fileName));

            Scanner stateScanner = new Scanner(dfa1.nextLine()); 

            Scanner alphabetScanner = new Scanner(dfa1.nextLine());

            Scanner transitionScanner = new Scanner(dfa1.nextLine());

            this.startState = dfa1.nextLine();

            Scanner acceptScanner = new Scanner(dfa1.nextLine());

            //read states
            stateScanner.useDelimiter(",");
            while (stateScanner.hasNext()) {
               this.addState(new State(stateScanner.next()));
            }
            
            //read alphabet
            alphabetScanner.useDelimiter(",");
            while (alphabetScanner.hasNext()) {
               this.addAlphabet(alphabetScanner.next());
            }

            //read accept state 
            acceptScanner.useDelimiter(",");
            while (acceptScanner.hasNext()) {
                this.acceptState.add(acceptScanner.next());
            }
            
            //read transitions for each state 
            transitionScanner.useDelimiter(";");
            while (transitionScanner.hasNext()) {
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
    
    /**
     * adds state to DFA
     * @param state
     */
    public void addState(State state) {

        this.states.add(state);
    }

    /**
     * adds character to alphabet
     * @param alpha
     */
    public void addAlphabet(String alpha) {
        this.alphabet.add(alpha);
    }

    /**
     * returns state arrayList
     * @return
     */
    public ArrayList<State> getStates() {
        return this.states;
    }

    /**
     * returns alphabet arrayList
     */
    public ArrayList<String> getAlphabet() {
        return this.alphabet;
    }

    /**
     * writes DFA fivetupple to output.txt
     * @param fileWriter
     * @return
     */
    public void write(PrintWriter fileWriter) {
        
        for (State state : this.states) {
            fileWriter.append(state.getName() + " ");

        }
        fileWriter.append("\n");
        for (String string : this.alphabet) {
            fileWriter.append(string + " ");
        }
        fileWriter.append("\n");
        for (State state : this.states) {
            for (Edge edge : state.getEdge()) {
                fileWriter.append(state.getName() + " x " + edge.getInput() + " = " + edge.getTransition().getName()+ "; ");
            }
        }
        fileWriter.append("\n");

        fileWriter.append(this.startState);
        fileWriter.append("\n");

        for (String accepString : this.acceptState) {
            fileWriter.append(accepString + " ");
        }        
        fileWriter.append("\n");
    }

    /**
     * Iterates through states and returns corresponding state with matching name 
     * @param name
     * @return
     */
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
