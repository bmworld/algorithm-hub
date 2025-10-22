fun main() {
  val bArr = ByteArray(1_000_000)
  val n = System.`in`.read(bArr)
  if (n <= 0) return print(0)

  var cnt = 0
  var inWord = false
  for (i in 0 until n) {
    val b = bArr[i].toInt()
    // 대문자: 65..90
    // 소문자: 97..122
    val toUppercase = 0xDF
    val isWord = (b and toUppercase) in 65..90
    if (isWord && !inWord) cnt++
    inWord = isWord
  }

  print(cnt)
}