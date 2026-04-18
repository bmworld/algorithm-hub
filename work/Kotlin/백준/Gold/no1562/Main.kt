package 백준.Gold.no1562

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 3
const val OBS = 8
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
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

val NUM = 48..57
fun i(): Int {
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

const val WS = 10
val WB = ByteArray(WS)
fun w(
  num: Int
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos)
}

const val NUM_LEN = 10
const val MOD = 1_000_000_000
const val lock = (1 shl 10) - 1
fun main() {
  val len = i()
  if (len < 10) {
    w(0)
    O.flush()
    return
  }

  // 10개에서부터 시작하면...?
  // 9876543210
  // 10123456789
  // 98765432101
  // 210123456789
  // 987654321010
  // 987654321012
  // ...
  // 123456789876543210

  fun dfs(
    dep: Int,
    v: Int,
    lock: Int
  ) {
    if (dep == len) {
      return
    }

    // flag 0이 되기전까지는...이미있는것은 사용하지 못하도록.
    repeat(NUM_LEN) {

    }
    // unlock 이후에, 그걸로....진행하면 되지 않나...?
  }


  repeat(9) {
    val v = it + 1
    val mid = if (v < NUM_LEN / 2) 0 else 9
    val goal = if (mid == 0) 9 else 0
    val rmn = getDist(v, mid) + getDist(mid, goal)
    println("v = $v ($rmn)")
    if (rmn > len - 1) return@repeat
    dfs(0, v, lock)
  }

  O.flush()
}

fun getDist(
  a: Int,
  b: Int
): Int = abs(a - b)

fun abs(v: Int): Int = if (v >= 0) v else -v

/**
 * 문제
 * 45656이란 수를 보자.
 *
 * 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
 *
 * N이 주어질 때, 길이가 N이면서 0부터 9까지 숫자가 모두 등장하는 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. 0으로 시작하는 수는 계단수가 아니다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 10
 * 예제 출력 1
 * 1
 * 힌트
 * 참고로, N=1일때부터, N=40일 때 까지 답을 모두 더하면 126461847755이 나온다.
 */
