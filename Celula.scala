package machine.learning.algoritmo.genetico.main

class Celula(val cromosoma: Array[Byte]) {

  def genes = cromosoma

  override def toString: String = {

    val sb: StringBuilder = new StringBuilder

    for (c <- cromosoma) {
        if (c == 1) { 
           sb.append("1") 
        } else { 
           sb.append("0") 
        }
    }

    sb.toString
  }
}
