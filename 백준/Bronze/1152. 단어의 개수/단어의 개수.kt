fun main() {
  val bArr = ByteArray(1_000_000)
  val n = System.`in`.read(bArr)
  if (n <= 0) return print(0)

  var cnt = 0
  var inWord = false
  var i = 0
  while (i < n) {
    val b = bArr[i].toInt()
    val isWord = b >= 65 // 대문자: 65..90 / 소문자: 97..122
    if (!inWord && isWord) cnt++
    inWord = isWord
    i++
  }

  print(cnt)
}