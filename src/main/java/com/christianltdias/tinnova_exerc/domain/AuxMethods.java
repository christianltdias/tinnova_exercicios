package com.christianltdias.tinnova_exerc.domain;

public class AuxMethods {

    static public String nome;
    
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

    static public int factorial(int value){
        int total = 1;
        while(value > 1){
            total *= value--;
        }
        return total;
    }

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