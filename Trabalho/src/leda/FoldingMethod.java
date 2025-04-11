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

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java FoldingMethod <caminho_do_arquivo>");
            return;
        }

        String filePath = args[0];
        int tableSize = 10; // Tamanho da tabela hash
        FoldingMethod foldingMethod = new FoldingMethod(tableSize);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            // Lê todo o conteúdo do arquivo
            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
            }

            // Divide os números pelo delimitador de espaço
            String[] numbers = content.toString().trim().split("\\s+");
            for (String numberStr : numbers) {
                try {
                    int key = Integer.parseInt(numberStr);
                    foldingMethod.insert(key);
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido ignorado: " + numberStr);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        System.out.println("Quantidade de colisões: " + foldingMethod.getCollisions());
    }
}
