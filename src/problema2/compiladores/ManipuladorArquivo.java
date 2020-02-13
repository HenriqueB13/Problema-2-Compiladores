/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema2.compiladores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mille
 */
public class ManipuladorArquivo {

    /**
     * @param args the command line arguments
     */
    private String input;
    private String output;

    public ManipuladorArquivo() {
        this.input = "input/";
        this.output = "output/";
    }
   
    
    public  ArrayList<String> leitor(String nome) throws IOException {
        input = input + nome;
        System.out.println(nome);
        System.out.println(input);
        BufferedReader buffRead = new BufferedReader(new FileReader(this.input));
        String linha = "";
        ArrayList<String> arquivo = new ArrayList<String>();

        linha = buffRead.readLine();
        while (linha != null) {
            if (linha != null) {
                arquivo.add(linha);
            }  
            else{ 
                buffRead.close();
                return null;
            }
            linha = buffRead.readLine();
        }
            return arquivo;
    }
 
    public void escritor(String nome, ArrayList<String> tabela) throws IOException {
        output.concat(nome);
         // Cria arquivo
            File file = new File(nome);

            // Se o arquivo nao existir, ele gera
            if (!file.exists()) {
                file.createNewFile();
            }

            // Prepara para escrever no arquivo
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
        //BufferedWriter buffWrite = new BufferedWriter(new FileWriter(output));
        String escrito = "";
        
        for(int i = 0; i < tabela.size(); i++){
            escrito = tabela.get(i);
            bw.append(escrito + "\n");
        }
        bw.flush();
        bw.close();
    }

}
