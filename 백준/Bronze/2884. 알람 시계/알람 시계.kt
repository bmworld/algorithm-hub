import java.io.BufferedInputStream

fun main() {

  var h = readInt()
  var m = readInt() - 45

  if (m < 0) {
    if (--h < 0) h = 23
    m += 60
  }

  print("$h $m")
}

val IN = BufferedInputStream(System.`in`)

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