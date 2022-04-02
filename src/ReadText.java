
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadText {
    public ArrayList<Integer> hasil;

    ReadText(){
        this.hasil = new ArrayList<>();
    }

    public void readPuzzleFile(String file_name) {
        String line = "";
        Path file_path = FileSystems.getDefault().getPath("..","test", file_name);
        try {
            BufferedReader br = new BufferedReader((new FileReader(file_path.toString())));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                for (int j = 0; j < 4; j++) {
                    if (!values[j].equals("-")){
                        hasil.add(Integer.parseInt(values[j]));
                    }
                    else{
                        hasil.add(16);
                    } 
                }
            }
        } catch (FileNotFoundException e) { // error handling
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Integer> getHasil() {
        return hasil;
    }

}


