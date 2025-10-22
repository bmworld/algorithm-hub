import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)

fun main() {

  var i = 0
  var m = 8
  var n = 1
  while (i < 8) {
    var c = IN.read()
    while (c <= 32) c = IN.read() // 필터
    val num = c - '0'.code
    if (num == m) m-- else if (num == n) n++

    i++
  }

  if (m == 0) print("descending") else if (n == 8) print("ascending") else print("mixed")
}