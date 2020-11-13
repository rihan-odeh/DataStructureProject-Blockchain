package datastructure.blockchain;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BitcoinSystem {

    ArrayList<LinkedList> allChains;
    JFrame window;
    Container container;

    public BitcoinSystem(){
        allChains= new ArrayList<LinkedList>();
        window = new JFrame();
        container=window.getContentPane();
    }


    //TODO task-2
    /** Each line contains many blocks and each block is surrounded with |, so first we need to tokenizer the line into many blocks and insert the line per blocks, as each block is surrounded with | **/
         /* here we need to parse the line as it contains list of blocks, each block is separated by |
         note that when parsing the the block we need to know the previous block has header to insert it in the current block
         insert the block in the chain after it filled with all needed data, then continue in the next token which contains the next block
        */
    public LinkedList<Block> parseTransactionsLine(String line){

        //**TODO** 1- initialize the below linkedlist
        LinkedList<Block> chain = new LinkedList<Block>(); 

         //this field will be true only in the first block of the line else it should be set to zero, this field will help to say we need to add the previous has to the next block or not,
        // the first block will be with null hash number
        boolean isFirst=true;
        //this will contain the current block that we need to  fill the information of it.
        Block block= null;

        //**TODO** 2- create a new tokenizer to parse the input line into string block information, the separator is |
         StringTokenizer st = new StringTokenizer(line, "|");
            while (st.hasMoreTokens()){
            line = (st.nextToken());
           
        
        
      
            // here we filled the below code to add the first block to the chain linkedlist
            if(isFirst){
                block=parseBlockTransactions(null,st.nextToken());
                isFirst=false;
                chain.add(block);
            }
          
            //get the hash function for the previous block header which is set to field called block
            //**TODO** 3- inside the function getBlockHeaderHash(), update it's body to do the needed, look inside it.
            String prevHash=getBlockHeaderHash(block);

            //**TODO** 4- now we need to parse the new string that is coming from tokenizer and convert it into block via using parseBlockTransactions function as above
            block=parseBlockTransactions(prevHash, line);

            //**TODO** 5- add this block to the chain
            chain.add(block);
        }
        return chain;
    }



    public String getBlockHeaderHash(Block block){
        //TODO 3- update the below field (blockheader) as a string concatenated for all header fields prev_hash, hash_transactions, timeStamp, nonce
        String blockHeader=block.getPrev_hash() + block.getHash_transactions() + block.getTimeStamp() + block.getNonce();
        return sha256(blockHeader);
    }
    public Block parseBlockTransactions(String prev_hash,String blockLine){
        Block block=new Block();
        block.setTimeStamp(new Date());
        block.setNonce(0);
        block.setPrev_hash(prev_hash);

        StringTokenizer st = new StringTokenizer(blockLine,"-");
        while (st.hasMoreTokens()) {
            String trans= st.nextToken();
            System.out.println("trans:"+trans);

            if(block.getTransactions() ==null){
                block.setTransactions(new LinkedList<String>() );
            }
            block.getTransactions().add(trans);
        }
        //Second step hashing
        ///Now we need to hash the transactions and set the value of them in the block
        System.out.println(block.getTransactions().toString());
        String hash_transactions= sha256(block.getTransactions().toString());
        block.setHash_transactions(hash_transactions);
        //System.out.println(hash_transactions);
        return block;
    }



    //This function used to hash a string and return a hash number via using has256 mechanism
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    
        //This function will draw the blocks in a JFrame
    public void draw(boolean isDoneProcessing){
        Container container = window.getContentPane();
        container.removeAll();
        container.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
             for(int i=0;i<allChains.size();i++){
              LinkedList<Block> chainObj = allChains.get(i);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            for(Block block: chainObj)
            {
                JPanel panelBlock = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton button;
                if(block.getPrev_hash()==null){
                    button=new JButton("first block");
                    button.setBackground(Color.LIGHT_GRAY);
                }else{
                    button=new JButton(block.getPrev_hash().substring(0,10)+", "+getBlockHeaderHash(block).substring(0,10));
                }
                button.setFont(new Font("Arial", Font.PLAIN, 10));
                panelBlock.add(button);
                panel.add(panelBlock);

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                }catch (Exception e){}
            }
            centerPanel.add(panel);
            }

        container.add(centerPanel, BorderLayout.CENTER);
        JPanel topPanel = new JPanel();
        JLabel label =new JLabel("Blockchain Project - Data Structure");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(label);
        container.add(topPanel, BorderLayout.NORTH);
        if(isDoneProcessing){
            JPanel panelEnd = new JPanel(new FlowLayout(FlowLayout.CENTER));
             label =new JLabel("Done processing");
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            panelEnd.add(label);
            container.add(panelEnd, BorderLayout.SOUTH);
        }
        if(!window.isActive()){
            window.setSize(1200, 800);
            window.setLocationRelativeTo(null);
        }
        window.setVisible(true);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        }catch (Exception e){}

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(window,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }

    public ArrayList<LinkedList> getAllChains() {
        return allChains;
    }

    public void setAllChains(ArrayList<LinkedList> allChains) {
        this.allChains = allChains;
    }
}
