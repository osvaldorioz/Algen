package machine.learning.algoritmo.genetico.main

class Evolucion extends MaquinaEvolucionadora {
  
   def runner(evaluador: Evaluador, poblacion: Poblacion){
      val solucion: Celula = run(evaluador, poblacion)

      println("\nsoluci\u00F3n:   " + solucion)
   }
}
