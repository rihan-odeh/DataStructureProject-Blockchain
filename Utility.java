package datastructure.blockchain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Utility {

    public static void generateChainsResultsFile(ArrayList<LinkedList> allChains){
        Path path = Paths.get("resources/", "chainsresult.txt");
        try {
            Files.deleteIfExists(path);
        }catch (Exception e){}
        for(int i=0;i<allChains.size();i++) {
            LinkedList<Block> chain = allChains.get(i);

            String line="";
            Iterator it = chain.iterator();
            // Iterating the list in forward direction
            while(it.hasNext()){
                Block block= (Block) it.next();
                line+=block.getPrev_hash()+","+block.getHash_transactions()+"|";
            }
            try {
                try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
                    writer.write(line+"\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static Queue<String> readTransactionsFile(){

        //Initialize the queue
        Queue<String> fileLines= new LinkedList<String>();
        try {
            File myObj = new File("./resources/blockstransactions.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                System.out.println(line);
                fileLines.add(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileLines;
    }
}
