import java.io.*

fun main() {
  val n = readInt(BufferedInputStream(System.`in`))
  BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(n, bw) }
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c in 48..57) { // '0' ~ '9'
    n = n * 10 + (c - 48)
    c = input.read()
  }
  return n
}

/** @param n 체크판 한변 크기 & 퀸 개수 (1<=n<15) */
fun solveTo(n: Int, out: Appendable) {
  var answer = 0
  // 열
  val colCh = BooleanArray(n * 2) { false }
  // 주대각
  val diaLCh = BooleanArray(n * 3) { false }
  // 부대각
  val diaRCh = BooleanArray(n * 2) { false }

  fun check(y: Int, x: Int) {
    // 점검
    if (colCh[x]) return
    if (diaLCh[n + x - y]) return
    if (diaRCh[x + y]) return

    // 선택
    colCh[x] = true
    diaLCh[n + x - y] = true
    diaRCh[x + y] = true

    // 다음
    if (y == n - 1) {
      answer++
    } else {
      for (nx in 0 until n) {
        check(y + 1, nx)
      }
    }

    // 해제
    colCh[x] = false
    diaLCh[n + x - y] = false
    diaRCh[x + y] = false
  }

  // 거울
  for (x in 0 until n / 2) check(0, x)
  answer *= 2

  // 홀수 -> 중앙값처리
  val isOdd = n % 2 == 1
  if (isOdd) {
    check(0, n / 2)
  }

  out.append(answer.toString())
}
