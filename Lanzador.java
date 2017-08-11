package machine.learning.algoritmo.genetico.base;

import java.util.Scanner;

import machine.learning.algoritmo.genetico.main.Evaluador;
import machine.learning.algoritmo.genetico.main.Evolucion;
import machine.learning.algoritmo.genetico.main.Poblacion;

public class Lanzador {
  public static void main(String... args){
    Scanner sc = new Scanner(System.in);
    Evaluador evaluador = new Evaluador();
		
    System.out.println("Ingrese cromosoma esperado:");		
    String cromosoma = sc.nextLine();
		
    System.out.println("Ingrese tamaño de población:");
    Integer populationSize = sc.nextInt();
    sc.close();
    Poblacion poblacion = new Poblacion(populationSize, cromosoma.length());
				  
    System.out.println("Probabilidad de Mutación: "  +  poblacion.probabilidadMutacion());
    System.out.println("Probabilidad de Cruza:    " + poblacion.probabilidadCruza());
    System.out.println("Número de genes:          " + cromosoma.length());
		System.out.println("Tamaño de población:      " + populationSize + "\n");
		  
    evaluador.load(cromosoma);
		  
    poblacion.popular();
		  
    Evolucion evolucion = new Evolucion();
    evolucion.runner(evaluador, poblacion);
  }
}