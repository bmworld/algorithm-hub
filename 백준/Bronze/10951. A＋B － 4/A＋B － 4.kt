fun main() {
  val br = System.`in`.bufferedReader()
  while (true) {
    val line = br.readLine() ?: break
    val sp = line.split(' ')
    println(sp[0].toInt() + sp[1].toInt())
  }
}