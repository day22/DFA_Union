/**
 * @author: Aidan Day, Noah Lafave
 * 
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class DFA_Union {

    
    public static void main(String[] args) {
        
        DFA dfa1 = new DFA("DFA1.txt");
        dfa1.toString();
        System.out.println();
        DFA dfa2 = new DFA("DFA2.txt");
        dfa2.toString();
        System.out.println();

        DFA unionDFA = union(dfa1, dfa2);

        FileOutputStream fos;
        try {
           fos = new FileOutputStream("output.txt");
           PrintWriter pw = new PrintWriter(fos);
           unionDFA.toString(pw);
           pw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Fail failed to be opened");
        }

        

    }

    public static DFA union(DFA dfa1, DFA dfa2) {

        DFA union = new DFA();

        for (State dfa1State: dfa1.getStates()) {
            for (State dfa2State : dfa2.getStates()) {
                String stateName = "" + dfa1State.getName() + dfa2State.getName();
                union.addState(new State(stateName));
            }
        }
       
        for (String alphabeString : dfa1.getAlphabet()) {
            union.addAlphabet(alphabeString);
        }

        union.setStartState("" + dfa1.getStartState() + dfa2.getStartState());
        

        for (String dfa1Accept : dfa1.getAcceptStates()) {
            for (State dfa2State : dfa2.getStates()) {
                union.addAcceptState(dfa1Accept + dfa2State.getName());
        
            }
        }

        for (String dfa2Accept : dfa2.getAcceptStates()) {
            for (State dfa1State : dfa1.getStates()) {
                if (!union.getAcceptStates().contains(dfa1State.getName() + dfa2Accept))
                union.addAcceptState(dfa1State.getName() + dfa2Accept);
            }
        }

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
        

        union.toString();
        return union;
    }
}