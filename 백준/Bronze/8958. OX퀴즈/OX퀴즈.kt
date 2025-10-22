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
      when (readOX()) {
        1 -> s += ++seq
        0 -> seq = 0
        else -> break
      }
    }
    sb.append(s).append('\n')
  }

  print(sb)
}

fun readOX(): Int {
  val c = IN.read() // 'O' = 79, 'X' = 88
  return if (c == 79) 1 else if (c == 88) 0 else -1
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