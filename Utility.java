package com.alquds.datastructure;

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

        //TODO Task-1 here we need to read the blockstransactions.txt file via using the scanner and inesrt the lines into a queu
        //TODO initialize the queu
        Queue<String> fileLines = null;
        try {
            // read lines from this file "./resources/blockstransactions.txt"
            //TODO implement the read  file here and insert the lines into the queue
        
      File myObj = new File("blockstransactions.txt");
      Scanner readFile = new Scanner(myObj);
      while (readFile.hasNextLine()) {
        String output = readFile.nextLine();
        System.out.println(output);
      

        }
            catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileLines;
    }
}
