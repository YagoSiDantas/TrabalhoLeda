package leda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FoldingMethod {
    private int tableSize;
    private Map<Integer, Integer> hashTable;
    private int collisions;

    public FoldingMethod(int tableSize) {
        this.tableSize = tableSize;
        this.hashTable = new HashMap<>();
        this.collisions = 0;
    }

    private int foldingHash(int key) {
        String keyStr = String.valueOf(key);
        int partSize = 3; // Definindo o tamanho das partes
        int hashValue = 0;

        for (int i = 0; i < keyStr.length(); i += partSize) {
            int endIndex = Math.min(i + partSize, keyStr.length());
            int part = Integer.parseInt(keyStr.substring(i, endIndex));
            hashValue += part;
        }

        return hashValue % tableSize;
    }

    public void insert(int key) {
        int hash = foldingHash(key);
        if (hashTable.containsKey(hash)) {
            collisions++;
        } else {
            hashTable.put(hash, key);
        }
    }

    public int getCollisions() {
        return collisions;
    }
