import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {

        Integer simpulDibangkitkan = 0;
        Queue<Node> pq = new PriorityQueue<>();

        Puzzle puzzle = new Puzzle();
        Puzzle target = new Puzzle();

        ReadText fileReader1 = new ReadText();
        ReadText fileReader2 = new ReadText();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name with puzzle: ");
        String filename = scanner.nextLine();

        fileReader1.readPuzzleFile(filename);
        fileReader2.readPuzzleFile("target.txt");

        puzzle.setPuzzle(fileReader1.getHasil());
        target.setPuzzle(fileReader2.getHasil());

        puzzle.setCost(puzzle.g());

        Node root = new Node(puzzle, null, 0,"None");
        pq.add(root);
        System.out.println();
        System.out.println("Initial State: ");
        puzzle.displayPuzzle();
        System.out.println();
        puzzle.displayKurangValue();
        System.out.println("Goal value: " + Puzzle.reachable(puzzle.puzzle));
        System.out.println();
        System.out.println("Path to solution: ");

        if (puzzle.isSolvable()){
            // handle exception out of memory
            // terjadi kalo puzzle nya rumit
            try{
                Long startTime = System.nanoTime();
               while (pq.size() > 0){
                    Node minNode = new Node(pq.remove());

                    // goal state tercapai
                    if (minNode.getPuzzle().targetReached()){
                        Node.printPath(minNode);
                        Long endTime = System.nanoTime();
                        Long totalTime = endTime-startTime;
                        System.out.println("Jumlah total simpul dibangkitkan: " + simpulDibangkitkan);
                        System.out.println("Algorithm total runtime: " + TimeUnit.NANOSECONDS.toMillis(totalTime) + "ms"); 
                        return;
                    }
                    
                    String moves[] = {"up","down","right","left"};
                    for (int i = 0; i < 4; i++){
                        // memeriksa apakah move valid dan tidak redundan
                        // cth. left-right
                        if (minNode.getPuzzle().isMoveSafe(moves[i],minNode.lastMove)){
                            Puzzle tempPuzzle = new Puzzle(minNode.getPuzzle());
                            
                            tempPuzzle.setPuzzle(minNode.getPuzzle().move(moves[i]));
                            // minNode.level + 1 menandakan jarak dari root ke node
                        
                            tempPuzzle.setCost(tempPuzzle.g() + minNode.level + 1);
                            Node child = new Node(tempPuzzle,minNode,minNode.level + 1,moves[i]);
                            simpulDibangkitkan++;
                            pq.add(child);
                        }
                    }
                }
                
            
            }
            catch(OutOfMemoryError e){
                System.out.println("Puzzle terlalu rumit");
            }
        }
        else{
            System.out.println("Puzzle tidak dapat diselesaikan");
        }
    }
}
;