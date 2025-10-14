import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val n = readInt(br)
  print(if (n % 4 == 0 && n % 100 != 0 || n % 400 == 0) 1 else 0)
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = input.read()
  }
  return n
}