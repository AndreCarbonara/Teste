package main;

import entidades.ProdutoUI;

/**
 * Classe Main que apenas inicia o programa.
 * 
 * Possui uma função simples de iniciar o programa, logo
 * segue o principio do SRP, pois é sua única responsabilidade. 
 * 
 */

public class Main {
    public static void main(String[] args) {
        ProdutoUI produtoUI = new ProdutoUI();
        produtoUI.exibir();
    }
}