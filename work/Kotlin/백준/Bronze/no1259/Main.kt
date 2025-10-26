package 백준.Bronze.no1259

import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)

  val sb = StringBuilder()
  while (true) {
    var c = br.read()
    while (c <= 32) c = br.read() // filter
    var n = ""
    while (c in '0'.code..'9'.code) {
      n += (c - '0'.code)
      c = br.read()
    }
    if (n == "0") break // END
    var result = "yes"
    val len = n.length
    for (i in 0 until len / 2) {
      if (n[i] != n[len - 1 - i]) {
        result = "no"
        break
      }
    }
    sb.appendLine(result)
  }
  print(sb)
}
