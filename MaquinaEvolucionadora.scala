package machine.learning.algoritmo.genetico.main

trait MaquinaEvolucionadora {

  def run(evaluador: Evaluador, poblacion: Poblacion): Celula = {
    def run(pop: Poblacion, generacion: Int): Celula = {
      val masApto = evaluador.masApto(pop)
      val aptitud = evaluador.estadoFisico(masApto)

      println(f"Generaci\u00F3n: $generacion%02d Cromosoma: $masApto%s Aptitud: $aptitud%2.2f")

      if (aptitud >= 1.0)
         masApto
      else
         run(
           pop.evolucion(true, evaluador),
           generacion + 1
         )
    }

    run(poblacion, 1)
  }
}
