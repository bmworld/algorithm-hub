package 프로그래머스.Lv0.대소문자바꿔서출력하기

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 5
const val OBS = 1 shl 5
val I = BufferedInputStream(System.`in`)
val O = BufferedOutputStream(System.`out`, OBS)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private const val A: Byte = 65
private const val a: Byte = 97
fun main(args: Array<String>) {
  var c: Byte
  val ans = ByteArray(10_000)
  var i = 0
  while (r().also { c = it } >= A)
    ans[i++] = (if (c >= a) c - a + A else c - A + a).toByte()

  O.write(ans, 0, i)
  O.flush()
}

/**
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (157.84ms, 53MB)
 * 테스트 2 〉	통과 (101.44ms, 53.3MB)
 * 테스트 3 〉	통과 (68.27ms, 53.9MB)
 * 테스트 4 〉	통과 (2486.50ms, 53.1MB)
 * v2:
 * 테스트 1 〉	통과 (142.95ms, 50.8MB)
 * 테스트 2 〉	통과 (167.73ms, 50.8MB)
 * 테스트 3 〉	통과 (213.23ms, 50.4MB)
 * 테스트 4 〉	통과 (2470.33ms, 49.7MB)
 *
 * [RIVAL 1]
 * 테스트 1 〉	통과 (77.84ms, 53.2MB)
 * 테스트 2 〉	통과 (114.09ms, 53.3MB)
 * 테스트 3 〉	통과 (150.83ms, 53.3MB)
 * 테스트 4 〉	통과 (162.64ms, 53.2MB)
 */
