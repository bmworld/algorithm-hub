import java.io.BufferedInputStream

private val IN = BufferedInputStream(System.`in`)

fun main() {
  val n = readInt()
  print(if (n % 4 == 0 && n % 100 != 0 || n % 400 == 0) 1 else 0)
}

private fun readInt(): Int {
  var n = 0
  var sign = 1
  var c = IN.read()
  while (true) {
    when (c) {
      in 48..57 -> {
        n = n * 10 + (c - 48)
        c = IN.read()
      }
      45 -> {
        sign = -1
        c = IN.read()
      }
      10,
      32 -> return n * sign
      else -> c = IN.read()
    }
  }
}