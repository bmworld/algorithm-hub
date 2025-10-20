import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val n = readInt(br)
  var max = Int.MIN_VALUE
  var min = Int.MAX_VALUE
  repeat(n) {
    val v = readInt(br)
    if (v > max) max = v
    if (v < min) min = v
  }

  print(min)
  print(' ')
  print(max)
}

fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var sign = 1
  if (c == '-'.code) { // 부호 처리
    sign = -1
    c = input.read()
  }
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = input.read()
  }
  return n * sign
}