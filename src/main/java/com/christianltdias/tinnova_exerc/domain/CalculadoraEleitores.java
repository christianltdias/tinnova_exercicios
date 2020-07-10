package com.christianltdias.tinnova_exerc.domain;

public class CalculadoraEleitores {
    

    private int totalVotos = 1000;

    private int votosValidos = 800;

    private int votosBrancos = 150;

    private int votosNulos = 50;


    public double getPercentualValidos(){
        return ((double) votosValidos/totalVotos)*100;
    }

    
    public double getPercentualBrancos(){
        return ((double) votosBrancos/totalVotos)*100;
    }

    
    public double getPercentualNulos(){
        return ((double) votosNulos/totalVotos)*100;
    }

    @Override
    public String toString(){
        return "Percentual de votos: \nVálido: "+getPercentualValidos()+"% \nBrancos: "+getPercentualBrancos()+"% \nNulos: "+getPercentualNulos()+"%";
    }
}