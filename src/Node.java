public class Node implements Comparable<Node>{

    Node parent;
    Puzzle puzzle;
    Integer level;
    String lastMove;

    //ctor dan cctor
    Node (Puzzle p,Node parent, Integer level, String lastMove){
        this.puzzle = p;
        this.parent = parent;
        this.level = level;
        this.lastMove = lastMove;
    }
    Node (Node n){
        this.puzzle = n.puzzle;
        this.parent = n.parent;
        this.level = n.level;
        this.lastMove = n.lastMove;
    }

    // getter
    public Puzzle getPuzzle(){
        return this.puzzle;
    }

    // menampilkan path solusi
    public static void printPath(Node root){
        if (root == null){
            return;
        }
        printPath(root.parent);
        System.out.println("------------");
        root.puzzle.displayPuzzle();
        System.out.println("------------");
    }

    // impelementasi PrioQueue, berdasarkan cost paling minimal
    @Override
    public int compareTo(Node o) {
        if (puzzle.getCost() == null || o.puzzle.getCost() == null){
            return 0;
        }
        return puzzle.getCost().compareTo(o.puzzle.getCost());
    }

    @Override
    public String toString(){
        return Integer.toString(this.getPuzzle().g());
    }
}