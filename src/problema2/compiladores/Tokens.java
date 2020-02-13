/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema2.compiladores;

/**
 *
 * erros termina com um - aritmetico(duplo), relacional(duplo), logico(todos)
 * @author Mille
 */
public class Tokens {
    
    private final String tipo;
    private final int linha;
    private final char[] lexema;
    private final String lexemaString;

    public Tokens(int linha, String tipo, char[] lexema) {
        this.tipo = tipo;
        this.linha = linha;
        this.lexema = lexema;
        this.lexemaString = new String();
    }
    public String toString(){
        String linhaSaida;
        linhaSaida= "linha "+ linha+" - "+tipo+" - "+String.valueOf(lexema);
        return linhaSaida;
    
    }

    public String getTipo() {
        return tipo;
    }
    public int getLinha() {
	return linha;
    }
    public char[] getLexema() {
	return lexema;
    }

}
