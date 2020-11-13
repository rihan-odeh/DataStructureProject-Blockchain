package datastructure.blockchain;

import java.util.Date;
import java.util.Queue;

public class Block {
    /**
     * Header of the block contains[ prev_hash, hash_transactions, timeStamp, nonce ]
     *
     */
    private String prev_hash;
    //this used to hash the transactions
    private String hash_transactions;
    //time when the block is created
    private Date timeStamp;
    private int nonce=0;
    private Queue<String> transactions;

    public String getPrev_hash() {
        return prev_hash;
    }

    public void setPrev_hash(String prev_hash) {
        this.prev_hash = prev_hash;
    }

    public String getHash_transactions() {
        return hash_transactions;
    }

    public void setHash_transactions(String hash_transactions) {
        this.hash_transactions = hash_transactions;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public Queue<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(Queue<String> transactions) {
        this.transactions = transactions;
    }
}
