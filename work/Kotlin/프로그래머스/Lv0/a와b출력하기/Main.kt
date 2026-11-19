package 프로그래머스.Lv0.a와b출력하기

import java.io.BufferedInputStream

private const val IBS = 1 shl 4
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

fun main(args: Array<String>) {
  val a = i()
  val b = i()
  print("a = $a\nb = $b")
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (140.50ms, 51.3MB)
 * 테스트 2 〉	통과 (127.60ms, 51MB)
 * 테스트 3 〉	통과 (142.12ms, 51.4MB)
 * 테스트 4 〉	통과 (136.34ms, 50.8MB)
 * 테스트 5 〉	통과 (158.01ms, 50.8MB)
 *
 * v2:
 * 테스트 1 〉	통과 (79.96ms, 51.4MB)
 * 테스트 2 〉	통과 (109.01ms, 51.1MB)
 * 테스트 3 〉	통과 (74.18ms, 51.4MB)
 * 테스트 4 〉	통과 (85.78ms, 52.4MB)
 * 테스트 5 〉	통과 (73.96ms, 51.5MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
