package machine.learning.algoritmo.genetico.main

class Evaluador {
  var solutionBytes: Array[Byte] = _

  /**
   * Carga una solución de un String
   */
  def load(solution: String) = {
    solutionBytes = new Array[Byte](solution.length)

    for (i <- 0 to solution.length - 1) {
      solutionBytes(i) = solution.charAt(i).asDigit.toByte
    }
  }

  /**
   * Regresa la célula más apta de la población
   */
  def masApto(poblacion: Poblacion): Celula = {
    var cell: Celula = poblacion.pop(0)

    for (i <- 1 to poblacion.size - 2) {
      val nextCell = poblacion.pop(i)

      if (estadoFisico(nextCell) > estadoFisico(cell)) {
        cell = poblacion.pop(i)
      }
    }

    cell
  }

  /**
  * Determina el estado físico de una célula comparándola con la solución óptima
  */
  def estadoFisico(cell: Celula): Double = {
    var score: Integer = 0
    var index: Integer = 0

    for (gen <- cell.genes) {
      if (solutionBytes(index) == gen) { 
        score += 1 
      }
      index += 1
    }

    val maxScore = cell.genes.size
    1.0 - ((maxScore.toDouble - score.toDouble) / 100)
  }
}

