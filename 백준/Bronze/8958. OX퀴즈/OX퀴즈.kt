import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)

fun main() {
  val n = readInt()
  val maxLenPerLine = 5 // 점수 최대 4자리 + 개행1
  val sb = StringBuilder(maxLenPerLine * n)
  repeat(n) {
    var s = 0
    var seq = 0
    while (true) {
      val ox = readOX() // O: 1, X = 0
      if (ox == -1) break
      if (ox == 1) {
        s += ++seq
      } else seq = 0
    }
    sb.append(s).append('\n')
  }

  println(sb)
}

fun readOX(): Int {
  val c = IN.read()
  var n = -1
  if (c == 79) {
    n = 1
  } else if (c == 88) {
    n = 0
  }

  return n
}

fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c >= 48 && c <= 57) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}