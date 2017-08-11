package machine.learning.algoritmo.genetico.main

import util.Random
  
class Poblacion(val populationSize: Integer) {
  val cromosomaSize: Integer = populationSize
  var pop: Array[Celula] = _  

  def probabilidadMutacion = 0.0555
  def probabilidadCruza = 0.7
  
  def initialise = {
    pop = new Array[Celula](populationSize + 1)
  }

  def size: Integer = {
    pop.length
  }
  
  /**
   * Creación de la población
   */
  def popular = {
    pop = new Array[Celula](populationSize + 1)

    for( i <- 0 to populationSize - 1) {

      val bytes = new Array[Byte](cromosomaSize)

      for( j <- 0 to cromosomaSize - 1) {
        bytes(j) = Math.round(Math.random).toByte
      }

      val cell = new Celula(bytes)
      pop(i) = cell
    }
  }  

  /**
   * Sustitución de una célula en una particular ubicación de la población
   */
  def addCell(index: Integer, cell: Celula) = {
    pop(index) = cell
  }

  /**
   * Evolución de la población por cruzamiento y mutación 
   * @param si elitista es true, la célula más apta pasa a la siguiente generación
   * @param evaluador El Evaluador que decide si es más apto
   */
  def evolucion(elitista: Boolean, evaluador: Evaluador): Poblacion = {
    val nextGeneration = new Poblacion(pop.size)
    nextGeneration.initialise

    var offset = 0

    if (elitista) {
      val eliteCell = evaluador.masApto(this)
      nextGeneration.addCell(0, mutar(eliteCell))
      offset += 1
    }

    for(index <- offset to pop.size) {
      val cell1: Celula = seleccion(evaluador)
      val cell2: Celula = seleccion(evaluador)
      val hijo: Celula = cruzar(cell1, cell2)

      nextGeneration.addCell(index, mutar(hijo))
    }

    nextGeneration
  }

  /**
   * Mutación de una célula
   */
  def mutar(cell: Celula): Celula = {
    val c: Array[Byte] = cell.genes

    for(index <- 0 to c.length - 1) {

      if (Math.random <= probabilidadMutacion) {
        c(index) = Math.round(Math.random).toByte
      }
    }

    new Celula(c)
  }

  /**
  * Selección de una célula de la población mediante el muestreo universal estocástico
  */
  def seleccion(evaluador: Evaluador): Celula = {
    val rondas = 10

    val poblacion = new Poblacion(rondas)
    poblacion.initialise

    for (i <- 0 to rondas) {
      val randomCell = pop(Random.nextInt(populationSize))
      poblacion.addCell(i, randomCell)
    }

    evaluador.masApto(poblacion)
  }
  
  /**
   * Creación de un hijo a partir de dos células
   */
  def cruzar(cell1: Celula, cell2: Celula): Celula = {
    val otrosGenes = cell2.genes
    val cromosomas = new Array[Byte](otrosGenes.length)

    var index: Integer = 0
    for (gen <- cell1.genes) {

      if (Math.random <= probabilidadCruza) {
         cromosomas(index) = gen
      } else {
         cromosomas(index) = otrosGenes(index)
      }
      index += 1
    }

    new Celula(cromosomas)
  }

  override def toString: String = {
    val sb: StringBuilder = new StringBuilder

    sb.append("[")
    for (cell <- pop) {
      sb.append(cell + ", ")
    }
    sb.dropRight(2)
    sb.append("]")

    sb.toString
  }
}
