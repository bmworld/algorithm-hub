fun main() {

  val br = System.`in`.bufferedReader()
  val n = br.readLine().trim().toInt()
  print(FACT[n])
}

val FACT =
    LongArray(21).apply {
      this[0] = 1
      for (i in 1..20) this[i] = this[i - 1] * i
    }
