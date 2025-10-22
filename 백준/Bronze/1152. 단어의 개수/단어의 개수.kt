fun main() {
  val max = 1_000_000
  val bArr = ByteArray(max)
  val len = System.`in`.read(bArr)
  //
  var cnt = 0
  var inWord = false
  var i = 0
  while (i < len) {
    val b = bArr[i].toInt() and 0xFF
    // > byte 128..255 -> Int 변환 시 음수
    // > 0xFF (00000000 00000000 00000000 11111111) and 연산
    // > 하위 8개 비트만 살림

    if (b > 32) {
      if (!inWord) { // 최초 단어만 카운트
        cnt++
        inWord = true
      }
    } else inWord = false
    i++
  }

  print(cnt)
}