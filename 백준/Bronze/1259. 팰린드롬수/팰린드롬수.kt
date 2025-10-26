import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)

  val sb = StringBuilder()
  while (true) {
    var c = br.read()
    while (c <= 32) c = br.read() // filter
    var n = 0
    var digit = 0
    while (c in '0'.code..'9'.code) {
      n = n * 10 + (c - '0'.code)
      c = br.read()
      digit++
    }

    if (n == 0) break //
    var palindrome = "yes"

    while (digit > 0) {
      var fZero = 1
      var fZeroCnt = digit - 1
      while (fZeroCnt > 0) {
        fZero *= 10
        fZeroCnt--
      }

      val f = n / fZero
      val l = n % 10

      if (f != l) {
        palindrome = "no"
        break
      }

      n = (n - f * fZero)
      n /= 10
      digit = digit - 2
    }

    sb.appendLine(palindrome)
  }

  print(sb)
}