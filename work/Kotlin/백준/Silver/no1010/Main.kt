package 백준.Silver.no1010

import java.io.StreamTokenizer

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      // 불필요한 문법 비활성화 + 숫자/공백만 활성화
      resetSyntax()
      whitespaceChars(0, 32) // 공백(스페이스 이하) 전부 공백 처리
      parseNumbers() // 숫자 토큰을 nval(double)로 바로 파싱
      slashSlashComments(false)
      slashStarComments(false)
      eolIsSignificant(false)

      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val c = Array(30) { IntArray(30) { 0 } }
      for (n in 0..29) {
        c[n][0] = 1
        c[n][n] = 1
        for (k in 1 until n) {
          c[n][k] = c[n - 1][k - 1] + c[n - 1][k]
        }
      }

      val sb = StringBuilder()
      repeat(nextInt()) {
        val m = nextInt() // 서
        val n = nextInt() // 동
        sb.append(c[n][m]).append('\n') // nCm
      }

      print(sb)
    }

/**
 * @param n 전체항목 수 (0 < N ≤ M < 30)
 * @param m 선택할 항목 수 (0 < N ≤ M < 30)
 * @return 경우의 수
 */
fun solution(n: Int, m: Int): Int {
  if (C[n][m] > 0) return C[n][m]
  if (n == m || m == 0) return 1
  else {
    C[n][m] = solution(n - 1, m - 1) + solution(n - 1, m)
    return C[n][m]
  }
}

val C = Array(30) { IntArray(30) { 0 } }

fun buildPascal() {
  for (n in 0..29) {
    C[n][0] = 1
    C[n][n] = 1
    for (k in 1 until n) {
      C[n][k] = C[n - 1][k - 1] + C[n - 1][k]
    }
  }
}
