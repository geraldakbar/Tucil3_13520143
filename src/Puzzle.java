import java.util.*;

public class Puzzle{

    Integer cost;
    ArrayList<Integer> puzzle;

    // constructor
    Puzzle(){
        this.puzzle = new ArrayList<Integer>();
    }
    Puzzle(Puzzle p){
        this.cost = p.cost;
        this.puzzle = new ArrayList<Integer>(p.puzzle);
    }

    // getter & setter
    public void setPuzzle(ArrayList<Integer> a){
        this.puzzle = new ArrayList<Integer>(a);
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public Integer getCost(){
        return cost;
    }
    public Boolean targetReached() {
        return this.g().equals(0);
    }

    // menampilkan puzzle
    public void displayPuzzle() {
        for (int i = 0; i < 4; i ++){
            for (int j = 0; j < 4; j++){
                System.out.print("[");
                if ( this.puzzle.get((4*i+j)).equals(16)){
                    System.out.print("-");
                }
                else{
                    System.out.print(this.puzzle.get((4*i+j)));
                }
                System.out.print("]");
                
            }
            System.out.println();
        }
    }

    // menampilkan nilai KURANG(i)
    public void displayKurangValue() {
        for (int i = 1; i <= this.puzzle.size(); i++){
            System.out.print("Kurang(" + i + ") = " + kurang(this.puzzle, i) + "\n");
        }
    }

    // kalkulasi nilai g(i)
    public Integer g() {
        Integer res = 0;
        for (int i = 0; i < this.puzzle.size(); i ++){
            if (!(this.puzzle.get(i).equals(i+1)) && !(this.puzzle.get(i).equals(16))){
                res++;
            }
        }
        return res;
    }

    public Boolean isSolvable(){
        return ((reachable(this.puzzle)%2) == 0);
    }

    // memeriksa nilai sigma(kurang(i)) + X
    public static int reachable(ArrayList<Integer> puzzle){
        Integer reachable = 0;
        for (int i = 0; i < puzzle.size(); i++){
            if (!puzzle.get(i).equals(0)){
                reachable += kurang(puzzle, i+1);
            }
        }
        return reachable + getX(puzzle);
    }

    // kalkulasi nilai KURANG(i)
    public static int kurang(ArrayList<Integer> puzzle, Integer i){
        Integer idx = puzzle.indexOf(i);
        Integer res = 0;
        for (int j = idx; j < puzzle.size(); j++){
            if (puzzle.get(j) < i){
                res++;
            }
        }
        return res;
    }

    // menentukan nilai X
    public static int getX(ArrayList<Integer> puzzle){
        ArrayList<Integer> arsir = new ArrayList<Integer>();
        Collections.addAll(arsir,1,3,4,6,9,11,12,14);
        Integer emptyidx = puzzle.indexOf(16);

        if (arsir.contains(emptyidx)){
            return 1;
        }
        else{
            return 0;
        }
    }


    // menentukan apakah move dapat dilakukan dan tidak redundan
    public Boolean isMoveSafe(String direction, String lastMove){
        Integer emptyidx = this.puzzle.indexOf(16);
        if (direction.equals("down")){
            return emptyidx <= 11 && lastMove != "up";
        }
        else if (direction.equals("up")){
            return emptyidx >= 4 && lastMove != "down";
        }
        else if (direction.equals("left")){
            return (emptyidx%4) != 0 && lastMove != "right";
        }
        else{
            return ((emptyidx+1)%4) != 0 && lastMove != "left";
        } 
    }

    // mengembalikan puzzle yang sudah digerakkan tile-nya
    public ArrayList<Integer> move(String direction){
        ArrayList<Integer> temp;
    
        if (direction.equals("down")){
            temp = new ArrayList<Integer>(this.down());
            return temp;
        }
        else if (direction.equals("up")){
            temp = new ArrayList<Integer>(this.up());
            return temp;
        }
        else if (direction.equals("left")){
            temp = new ArrayList<Integer>(this.left());
            return temp;
        }
        else{
            temp = new ArrayList<Integer>(this.right());
            return temp;
        }
        
    }

    // FUNGSI PERGERAKAN 
    public ArrayList<Integer> down() {
       Integer emptyidx = this.puzzle.indexOf(16);
       ArrayList<Integer> temp = new ArrayList<>(this.puzzle);
       Collections.swap(temp, emptyidx, emptyidx+4);
       return temp;
    }
    public ArrayList<Integer> up(){
        Integer emptyidx = this.puzzle.indexOf(16);
        ArrayList<Integer> temp = new ArrayList<>(this.puzzle);
        Collections.swap(temp, emptyidx, emptyidx-4);
        return temp;
    }
    public ArrayList<Integer> left(){
        Integer emptyidx = this.puzzle.indexOf(16);
        ArrayList<Integer> temp = new ArrayList<>(this.puzzle);
        Collections.swap(temp, emptyidx, emptyidx-1);
        return temp;
    }
    public ArrayList<Integer> right(){
        Integer emptyidx = this.puzzle.indexOf(16);
        ArrayList<Integer> temp = new ArrayList<>(this.puzzle);
        Collections.swap(temp, emptyidx, emptyidx+1);
        return temp;
    }
}