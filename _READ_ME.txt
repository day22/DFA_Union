@authors Aidan Day and Noah Lafave

This program takes reads two DFAs from separate input files and writes the union to an output file. 
The main method is located within the class DFA_UNION. All the java files within this package must be 
compiled in order for the program to run functionally. 

Input files take the form of the classic DFA fivetuple:
The first line should contain states separate with commas. No spaces should be placed after commas. Ex: q1,q2,q3 
Second line should contain the alphabet separate with commas. Ex: 0,1
Third line should contain the delta table of the DFA. Connections take the following form (originState) (input) (endState). Example: q0 0 q1 
Forth line should contain soly the starting state. EX q1
Fifth line should contain the accept states of the DFA separated with commas. No spaces should be placed after commas. Ex: q1, q2

Example file:
q1,q2
0,1
q1 0 q1;q1 1 q2;q2 0 q2; q2 1 q1
q1
q1

To run your own DFAs, format files as show and then make the following changes to main:

DFA dfa1 = new DFA("(insert your file name here)");

DFA dfa2 = new DFA("(insert your file name here)");

After you have made those changes the resultant union will be written to the output file in the same fivetuple form