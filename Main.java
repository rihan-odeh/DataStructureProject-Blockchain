package datastructure.blockchain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        BitcoinSystem bitcoinSystem=new BitcoinSystem();

        /// The system starts from here
        Queue<String> fileLines = Utility.readTransactionsFile();

        //TODO task-2 go to BitcoinSystem class and do the needed in parseTransactionsLine() method
        for(String str:fileLines){
            LinkedList<Block> chain= bitcoinSystem.parseTransactionsLine(str);
            bitcoinSystem.getAllChains().add(chain);
            bitcoinSystem.draw(false);
        }
        Utility.generateChainsResultsFile(bitcoinSystem.getAllChains());
        bitcoinSystem.draw(true);
    }







}
