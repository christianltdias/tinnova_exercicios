package com.christianltdias.tinnova_exerc.domain;

/*
    Classe para conter os valores pedidos no Exercicio 1
*/
public class CalculadoraEleitores {

    private int totalVotos = 1000;

    private int votosValidos = 800;

    private int votosBrancos = 150;

    private int votosNulos = 50;

    // Calculo percentual de votos validos
    public double getPercentualValidos() {
        return ((double) votosValidos / totalVotos) * 100;
    }

    // Calculo percentual de votos em branco
    public double getPercentualBrancos() {
        return ((double) votosBrancos / totalVotos) * 100;
    }

    // Calculo percentual de votos nulos
    public double getPercentualNulos() {
        return ((double) votosNulos / totalVotos) * 100;
    }

    @Override
    public String toString() {
        return "Percentual de votos: \nVálido: " + getPercentualValidos() + "% \nBrancos: " + getPercentualBrancos()
                + "% \nNulos: " + getPercentualNulos() + "%";
    }
}