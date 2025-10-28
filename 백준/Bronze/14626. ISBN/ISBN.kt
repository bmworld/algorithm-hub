import java.io.BufferedInputStream
private val IN = BufferedInputStream(System.`in`)
fun main() {
  var sum = 0
  var m = 0
  var xw = 0
  repeat(13) {
    val v = IN.read()
    val w = if (it % 2 == 0) 1 else 3
    val num = v - 48
    if (it == 12) {
      m = num
    } else if (v == '*'.code) {
      xw = w
    } else if (v in 48..57) sum += num * w
  }
  for (x in 0..9) if ((sum + x * xw + m) % 10 == 0) return print(x)
}