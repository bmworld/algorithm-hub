import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)

fun main() {

  var h = readInt()
  var m = readInt()

  var t = h * 60 + m - 45
  if (t < 0) t += 1440
  h = t / 60
  m = t % 60

  print("$h $m")
}

fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}