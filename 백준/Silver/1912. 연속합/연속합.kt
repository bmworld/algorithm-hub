import java.io.BufferedInputStream

fun main() {
  val bi = BufferedInputStream(System.`in`)
  val n = readInt(bi)
  val arr = IntArray(n) { readInt(bi) }
  println(solveTo(arr))
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read() // skip spaces
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

fun solveTo(arr: IntArray): Int {
  var max = arr[0]

  var cur = arr[0]
  for (i in 1 until arr.size) {
    val v = arr[i]
    cur = maxOf(cur + v, v)
    max = maxOf(max, cur)
  }

  return max
}