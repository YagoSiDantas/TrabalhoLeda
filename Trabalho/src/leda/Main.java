package leda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.file;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String caminho = "C:/Users/yyago/Desktop/random_numbers3.txt"
        string linha = ""
        File file = new File(caminho);
        Scanner scan = new Scanner(File)
            
        if (args.length != 1) {
            System.out.println("Uso: java Main <caminho_do_arquivo>");
            return;
        }

        String filePath = args[0];
        int tableSize = 10; // Tamanho da tabela hash
        FoldingMethod foldingMethod = new FoldingMethod(tableSize);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

           
            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
            }

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
