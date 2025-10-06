import java.io.*

fun main() {
  val n = readInt(BufferedInputStream(System.`in`))
  BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(n, bw) }
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c in 48..57) { // '0' ~ '9'
    n = n * 10 + (c - 48)
    c = input.read()
  }
  return n
}

/** @param n (5 <= n <= 40) */
fun solveTo(n: Int, out: Appendable) {
  var recCnt = 0
  var dpCnt = 0
  val fib = IntArray(41)

  fun recursionFib(n: Int): Int {
    if (n == 1 || n == 2) {
      recCnt++
      return 1 // 코드 1
    } else {
      return recursionFib(n - 1) + recursionFib(n - 2)
    }
  }

  fun dpFib(n: Int): Int {
    if (fib[n] != 0) return fib[n]
    fib[1] = 1
    fib[2] = 1
    for (i in 3..n) {
      dpCnt++
      fib[i] = fib[i - 1] + fib[i - 2] // 코드 2
    }

    return fib[n]
  }

  recursionFib(n)
  dpFib(n)

  out.append(recCnt.toString()).append(" ").append(dpCnt.toString())
}