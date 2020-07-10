package com.christianltdias.tinnova_exerc;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.christianltdias.tinnova_exerc.domain.AuxMethods;
import com.christianltdias.tinnova_exerc.domain.CalculadoraEleitores;
import com.christianltdias.tinnova_exerc.domain.Veiculo;
import com.christianltdias.tinnova_exerc.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	private VeiculoRepository categoriaRepo;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		System.out.println("Execicio 1");
		CalculadoraEleitores calc = new CalculadoraEleitores();
		System.out.println(calc);

		System.out.println("\nExecicio 2");
		int[] v = { 5, 3, 2, 4, 7, 1, 0, 6 };
		System.out.println(Arrays.toString(AuxMethods.bubbleSort(v)));
		
		System.out.println("\nExecicio 3");
		System.out.println(AuxMethods.factorial(0));
		System.out.println(AuxMethods.factorial(1));
		System.out.println(AuxMethods.factorial(2));
		System.out.println(AuxMethods.factorial(3));
		System.out.println(AuxMethods.factorial(4));
		System.out.println(AuxMethods.factorial(5));
		System.out.println(AuxMethods.factorial(6));
		
		System.out.println("\nExecicio 4");
		System.out.println(AuxMethods.sumMultiplesOf3Or5(10));
		System.out.println(AuxMethods.sumMultiplesOf3Or5(31));
	}

	@Override
	public void run(String... args) throws Exception {
		// Criação inicial de objetos

		System.out.println("Execicio 5\n");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Veiculo carro1 = new Veiculo(null, "Uno", "Fiat", 2000, "Um carro bem bonito", false,
				sdf.parse("1/07/2020 10:32"), new Date(System.currentTimeMillis()));

		Veiculo carro2 = new Veiculo(null, "X5", "Bmw", 2012, "Um carro bem maneiro", true,
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

		Veiculo carro3 = new Veiculo(null, "Golf", "Wolkswagen", 1998, "Um carro bem legal", false,
				sdf.parse("6/07/2020 10:32"), new Date(System.currentTimeMillis()));

		Veiculo carro4 = new Veiculo(null, "Mustang", "Ford", 2006, "Um carro bem feio", true,
				sdf.parse("3/06/2020 10:32"), new Date(System.currentTimeMillis()));

		categoriaRepo.saveAll(Arrays.asList(carro1, carro2, carro3, carro4));
	}

}
