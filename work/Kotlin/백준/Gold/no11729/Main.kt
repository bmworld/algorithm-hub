package 백준.Gold.no11729

import java.io.*

fun main() {
  val n = readInt(BufferedInputStream(System.`in`))
  val bw = BufferedWriter(OutputStreamWriter(System.out))
  println(solveTo(n, bw))
}

fun solveTo(n: Int, out: Appendable) {

  var answer = ""

  when (out) {
    is BufferedWriter -> {
      val bw = out as BufferedWriter
      bw.write(answer)
      bw.newLine()
    }
    is Writer -> {
      val w = out as Writer
      w.write(answer)
      out.append('\n')
    }
    else -> {
      out.append(answer)
    }
  }

  // 하노이탑.
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

/** 테스트용 */
fun solution(n: Int): String {
  val sb = StringBuilder()
  solveTo(n, sb)
  return sb.trim().toString()
}
