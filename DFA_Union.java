/**
 * @author: Aidan Day, Noah Lafave
 * Theory of Computation Coding Project
 * 
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class DFA_Union {

    
    public static void main(String[] args) {
        
        DFA dfa1 = new DFA("DFA1.txt");

        DFA dfa2 = new DFA("DFA2.txt");

        DFA unionDFA = union(dfa1, dfa2);

        FileOutputStream fos;
        try {
           fos = new FileOutputStream("output.txt");
           PrintWriter pw = new PrintWriter(fos);
           unionDFA.write(pw);
           pw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Fail failed to be opened");
        }
    }

    /**
     * Union takes two DFAs as arguments and returns the union of the two 
     * @param dfa1
     * @param dfa2
     * @return DFA
     */
    public static DFA union(DFA dfa1, DFA dfa2) {

        DFA union = new DFA();

        //adds the possible state combinations to new DFA
        for (State dfa1State: dfa1.getStates()) {
            for (State dfa2State : dfa2.getStates()) {
                String stateName = "" + dfa1State.getName() + dfa2State.getName();
                union.addState(new State(stateName));
            }
        }
       
        //since both DFAs must share the same alphabet we can read only from one DFA
        for (String alphabeString : dfa1.getAlphabet()) {
            union.addAlphabet(alphabeString);
        }
        
        //new start state is start state from both DFAs 
        union.setStartState("" + dfa1.getStartState() + dfa2.getStartState());
        
        //add accept states for dfa1
        for (String dfa1Accept : dfa1.getAcceptStates()) {
            for (State dfa2State : dfa2.getStates()) {
                union.addAcceptState(dfa1Accept + dfa2State.getName());
        
            }
        }

        //add accept states from dfa 2
        for (String dfa2Accept : dfa2.getAcceptStates()) {
            for (State dfa1State : dfa1.getStates()) {
                if (!union.getAcceptStates().contains(dfa1State.getName() + dfa2Accept))
                union.addAcceptState(dfa1State.getName() + dfa2Accept);
            }
        }

        //adds new transitions
        for (State dfa1State: dfa1.getStates()) {
            for (State dfa2State : dfa2.getStates()) {
                State orgin = union.findStateByName(dfa1State.getName()+dfa2State.getName());
                for (String alpha : union.getAlphabet()) {
                    State d1tran = dfa1State.getTransitionOn(alpha);
                    State d2tran = dfa2State.getTransitionOn(alpha);
                    Edge newEdge = new Edge(alpha, union.findStateByName(d1tran.getName()+d2tran.getName()));
                    orgin.addEdge(newEdge);
                }
            }
        }
        return union;
    }
}