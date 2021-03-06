

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Griddle {
    static State initState, goalState;
    static int n;
    static Scanner scn;
    static HashSet<State> visited;
    static boolean anystate = true;

    public static void main(String[] args){

        try {

            scn = new Scanner(new BufferedReader(new FileReader("src/input.txt")));
            while (true) {
                int temp;
                temp = scn.nextInt();
                if(temp==0){
                    System.out.println("Exiting..");
                    break;
                }
                long time = System.currentTimeMillis();
                initState = new State();
                goalState = new State();
                visited = new HashSet<>();
                n = temp;

                initState.mat = new int[n][n];
                goalState.mat = new int[n][n];

                for(int i = 0; i<n; i++){
                    for(int j=0; j<n; j++){
                        initState.mat[i][j] = scn.nextInt();
                    }
                }
                for(int i = 0; i<n; i++){
                    for(int j=0; j<n; j++){
                        goalState.mat[i][j] = scn.nextInt();
                    }
                }

                State.init(n, initState, goalState, anystate);

                play();
                long end = System.currentTimeMillis();
                System.out.println("Time elapsed "+ (end-time) + " ms");
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void play(){

        PriorityQueue<State> Q = new PriorityQueue<>();

        visited.add(initState);
        Q.add(initState);

        while(!Q.isEmpty()){
            State s = Q.remove();
            if(s.isthisgoal){
                goalState = s;
                break;
            }
            else {
                for(int i=0; i<n; i++){
                    State newState = new State(s, Direction.left, i);
                    if(!visited.contains(newState)) {
                        visited.add(s);
                        Q.add(newState);
                    }
                    newState = new State(s, Direction.right, i);
                    if(!visited.contains(newState)) {
                        visited.add(s);
                        Q.add(newState);
                    }
                    newState = new State(s, Direction.up, i);
                    if(!visited.contains(newState)) {
                        visited.add(s);
                        Q.add(newState);
                    }
                    newState = new State(s, Direction.down, i);
                    if(!visited.contains(newState)) {
                        visited.add(s);
                        Q.add(newState);
                    }

                }
            }

        }

        System.out.println("Number of moves: "+ goalState.costFromInit);
        LinkedList<State> sequence = new LinkedList<>();
        State current = goalState;
        sequence.add(current);
        while(current.parentState!=null){
            current = current.parentState;
            sequence.addFirst(current);
        }

        for(State s: sequence){
            s.print();
        }
    }



    public static void griddleTest(){
//        State.printInitState();
//        State.printGoalState();
//
//        State s = new State(initState, Direction.left, 1);
//        //  s.print();
//        s = new State(initState, Direction.right, 0);
//        //  s.print();
//        s = new State(initState, Direction.up, 2);
//        // s.print();
//        s = new State(initState, Direction.down, 1);
//        s.print();
//        System.out.println(s.getTotalCost());
    }
}
