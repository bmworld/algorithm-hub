package 백준.Gold.no9663

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
  val board = Array(n) { BooleanArray(n) { false } }

  fun checkRow(y: Int, x: Int) {
    if (y >= n) return

    // 열검증하고, 넘김
    for (nx in 0 until n) {}

    // 충돌판정
    var trouble = false
    // 열
    var uy = y - 1
    while (uy >= 0) {
      if (board[uy--][x]) {
        trouble = true
        break
      }
    }

    // 좌상
    var ulx = x - 1
    var uly = y - 1
    while (uly >= 0 && ulx >= 0) {
      if (board[uly--][ulx--]) {
        trouble = true
        break
      }
    }

    // 우상
    var urx = x + 1
    var ury = y - 1
    while (urx < n && ury >= 0) {
      if (board[ury--][urx++]) {
        trouble = true
        break
      }
    }

    if (trouble) return

    // ---------------------------------------------------------------------
    // 선택
    if (y + 1 == n) {
      answer++
      return
    }

    // ---------------------------------------------------------------------
    // 다음
    for (nx in 0 until n) {
      board[y][nx] = true
      checkRow(y + 1, nx)
      board[y][nx] = false
    }
  }

  checkRow(0, 0)

  out.append(answer.toString())
}

/** 테스트용 */
fun solution(n: Int): Int {
  val sb = StringBuilder()
  solveTo(n, sb)
  return sb.toString().toInt()
}
