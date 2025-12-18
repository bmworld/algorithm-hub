package 백준.Bronze.no1152

const val SPACE = 32.toByte()
fun main() {
  val bArr = ByteArray(1_000_000)
  val n = System.`in`.read(bArr)
  var cnt = 0
  var inWord = false
  var i = 0
  while (i < n) {
    val isWord = bArr[i] > SPACE
    if (!inWord && isWord) cnt++
    inWord = isWord
    i++
  }
  print(cnt)
}
