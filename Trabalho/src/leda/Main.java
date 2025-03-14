package leda;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int capacidadeInicial = getProximoPrimo(1000);

        FoldingMethod hashTable = new FoldingMethod(capacidadeInicial);

        String arquivo = "random_numbers3.txt";

        File file = new File(arquivo);
        
        if (!file.exists()) {
            System.out.println("O arquivo não foi encontrado: " + file.getAbsolutePath());
            return;
        }

        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(arquivo)));
            StringBuffer elementos = new StringBuffer(conteudo);
            
            String[] numeros = elementos.toString().split("\\s+");
            
            for (String num : numeros) {
                try {
                    int key = Integer.parseInt(num.trim());
                    hashTable.insert(key);
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido encontrado e ignorado: " + num);
                } catch (FoldingMethod.CollisionException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
        }

        System.out.println("Número de colisões: " + hashTable.getCollisions());
    }

    public static int getProximoPrimo(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}


