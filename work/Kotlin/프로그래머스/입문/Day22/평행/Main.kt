package 프로그래머스.입문.Day22.평행

class Solution {

  val IMPOSSIBLE = 0
  val POSSIBLE = 1
  fun solution(dots: Array<IntArray>): Int {
    val len = dots.size

    for (i in 0 until len)
      for (j in i + 1 until len) {
        if (i == j) continue
        val di = dots[i]
        val dj = dots[j]

        val k = getIdx(len, i, j)
        var l = getIdx(len, i, j, k)
        val dk = dots[k]
        val dl = dots[l]

        val biggerBI = di[1] > dj[1]
        val dy1 = (if (biggerBI) di[1] - dj[1] else dj[1] - di[1]).toDouble()
        val dx1 = (if (biggerBI) di[0] - dj[0] else dj[0] - di[0]).toDouble()

        val biggerDK = dk[1] > dl[1]
        val dy2 = (if (biggerDK) dk[1] - dl[1] else dk[1] - dl[1]).toDouble()
        val dx2 = (if (biggerDK) dk[0] - dl[0] else dk[0] - dl[0]).toDouble()

        val d1 = dy1 / dx1
        val d2 = dy2 / dx2
        if (d1 == d2) return POSSIBLE
      }
    return IMPOSSIBLE
  }

  fun getIdx(len: Int, exp1: Int, exp2: Int, exp3: Int = -1): Int {
    var x = 0
    while (x < len) {
      if (x != exp1 && x != exp2 && x != exp3) break
      x++
    }
    return x
  }
}


fun main() {
  val s = Solution()
  check(s.solution(
    arrayOf(
      intArrayOf(1, 4),
      intArrayOf(9, 2),
      intArrayOf(3, 8),
      intArrayOf(11, 6),
    )
  )
    == 1)

  check(s.solution(
    arrayOf(
      intArrayOf(3, 5),
      intArrayOf(4, 1),
      intArrayOf(2, 4),
      intArrayOf(5, 10),
    )
  )
    == 0)

  // 겹치는 경우 포함
  check(s.solution(
    arrayOf(
      intArrayOf(1, 1),
      intArrayOf(2, 2),
      intArrayOf(3, 3),
      intArrayOf(4, 4),
    )
  )
    == 1)

  check(s.solution(
    arrayOf(
      intArrayOf(0, 1),
      intArrayOf(1, 0),
      intArrayOf(3, 3),
      intArrayOf(4, 4),
    )
  )
    == 0)
}

/**
 * 테스트 1 〉	통과 (0.02ms, 61.8MB)
 * 테스트 2 〉	통과 (0.03ms, 62.2MB)
 * 테스트 3 〉	통과 (0.03ms, 61.2MB)
 * 테스트 4 〉	통과 (0.02ms, 61.1MB)
 * 테스트 5 〉	통과 (0.02ms, 61MB)
 * 테스트 6 〉	통과 (0.02ms, 62.8MB)
 * 테스트 7 〉	통과 (0.01ms, 61MB)
 * 테스트 8 〉	통과 (0.02ms, 60.5MB)
 * 테스트 9 〉	통과 (0.03ms, 60.9MB)
 * 테스트 10 〉	통과 (0.02ms, 61.1MB)
 * 테스트 11 〉	통과 (0.02ms, 61.3MB)
 * 테스트 12 〉	통과 (0.02ms, 62.2MB)
 * 테스트 13 〉	통과 (0.02ms, 64.4MB)
 * 테스트 14 〉	통과 (0.03ms, 62.6MB)
 * 테스트 15 〉	통과 (0.03ms, 61.5MB)
 * 테스트 16 〉	통과 (0.02ms, 61.2MB)
 * 테스트 17 〉	통과 (0.02ms, 61.5MB)
 */
