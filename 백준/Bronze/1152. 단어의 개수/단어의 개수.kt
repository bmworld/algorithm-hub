const val SPACE = 32.toByte()

fun main() {
  val bArr = ByteArray(1_000_000)
  val n = System.`in`.read(bArr)
  if (n <= 0) return print(0)

  var cnt = 0
  var inWord = false
  var i = 0
  while (i < n) {
    val isWord = bArr[i] > SPACE // 공백: 32 / 대문자: 65..90 / 소문자: 97..122
    if (!inWord && isWord) cnt++
    inWord = isWord
    i++
  }

  print(cnt)
}