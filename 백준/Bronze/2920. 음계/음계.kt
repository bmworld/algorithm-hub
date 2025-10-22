import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)
const val SPACE = 32
const val ZERO = 48

fun main() {

  var m = 8
  var n = 1
  var i = 0
  while (i < 8) {
    var c = IN.read()
    while (c <= SPACE) c = IN.read()
    val num = c - ZERO
    if (num == m) m-- else if (num == n) n++

    i++
  }

  print(if (m == 0) "descending" else if (n == 8) "ascending" else "mixed")
}