import java.io.BufferedInputStream

fun main() {
  var cnt = 0
  var inWord = false
  var c: Int

  while (true) {
    c = IN.read()
    if (c == -1 || c == '\n'.code) break

    if (c > 32) { 
      if (!inWord) { // 최초 단어만 카운트
        cnt++
        inWord = true
      }
    } else inWord = false
  }

  print(cnt)
}

val IN = BufferedInputStream(System.`in`)