package com.christianltdias.tinnova_exerc.domain;


/*
    Classe responsável pelos métodos dos Exercicios 2, 3 e 4
*/
public class AuxMethods {

    // Função para ordernar um vetor (Exercicio 2)
    static public int[] bubbleSort(int[] vector) {
        boolean flag = true;
        int aux = 0;
        while (flag) {
            flag = false;
            for (int i = 0; i < vector.length - 1; i++) {
                if (vector[ i + 1] < vector[i]){
                    flag =true;
                    aux = vector[i];
                    vector[i] = vector[i+1];
                    vector[i+1] = aux;
                }
            }
        }
        return vector;
    }

    
    // Função para calcular o fatorial de um 'value' (Exercicio 3)
    static public int factorial(int value){
        int total = 1;
        while(value > 1){
            total *= value--;
        }
        return total;
    }

    
    // Função para somar todos multiplos de 3 ou 5 MENORES que 'number' (Exercicio 4)
    static public int sumMultiplesOf3Or5(int number){
        int total = 0;
        while(number>=3){
            number--; // Ao colocar o decremento antes do for, serão somentes contados o número menores que number
           
            if( (number%3) == 0 || (number%5) == 0){
                total += number;
            }
        }
        return total;
    }
}