import javafx.scene.layout.Priority;

import java.util.PriorityQueue;
import java.util.Random;

public class Test {
    static int n;

    public static void main(String[] args) {
//        PriorityQueue<State> queue = new PriorityQueue<>();
//        Random rand = new Random();
//
//        for(int i=0; i<5; i++){
//            State s = new State();
//            s.totalCost = (Math.abs(rand.nextInt()%10));
//            System.out.println(s.getTotalCost());
//            queue.add(s);
//        }
//        System.out.println("After queuing");
//
//        for(int i=0; i<5; i++){
//            State s = queue.remove();
//            System.out.println(s.getTotalCost());
//        }





        n = 3;
        int[][] matt = new int[n][n];
        System.out.println(matt.length+" "+matt[0].length+" "+matt[1].length);
        Random rand = new Random();

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                matt[i][j] = 1+ Math.abs(rand.nextInt())%9;
               // System.out.println("I'm here");
            }
        }
//
//
//
        printMat(matt);
        rotate(matt, Direction.left, 0);
        rotate(matt, Direction.right, 1);
        rotate(matt, Direction.up, 2);
        rotate(matt, Direction.down, 1);
//
//
//
//
//
//        System.out.println("Yes, it is running fine");
    }
    private static void rotate(int[][] mat, Direction dir, int position) {

        System.out.println(dir+" "+ position);

        if(dir==Direction.left) leftRotate(mat, position);
        if(dir==Direction.right) rightRotate(mat, position);
        if(dir==Direction.up) upRotate(mat, position);
        if(dir==Direction.down) downRotate(mat, position);

        printMat(mat);
    }

    private static void leftRotate(int[][] mat, int position) {
        int temp = mat[position][0];
        for(int j = 0; j<n-1; j++){
            mat[position][j] = mat[position][j+1];
        }
        mat[position][n-1] = temp;
    }
    private static void rightRotate(int[][] mat, int position) {
        int temp = mat[position][n-1];
        for(int j = n-1; j>0; j--){
            mat[position][j] = mat[position][j-1];
        }
        mat[position][0] = temp;
    }
    private static void upRotate(int[][] mat, int position) {
        int temp = mat[0][position];
        for(int i = 0; i<n-1; i++){
            mat[i][position] = mat[i+1][position];
        }
        mat[n-1][position] = temp;
    }
    private static void downRotate(int[][] mat, int position) {
        int temp = mat[n-1][position];
        for(int i = n-1; i>0; i--){
            mat[i][position] = mat[i-1][position];
        }
        mat[0][position] = temp;
    }


    public static void printMat(int[][] mat){
        System.out.println("The matrix is:");
        for(int[] x:mat){
            for(int y:x){
                System.out.print(y+" ");
            }
            System.out.println();
        }
    }



}