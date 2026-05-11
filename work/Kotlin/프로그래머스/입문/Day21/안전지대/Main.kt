package 프로그래머스.입문.Day21.안전지대

class Solution {

  val SAFE = 0
  val BOMB = 1
  val UNSAFE = 2
  val dc = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
  val dr = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
  fun solution(a: Array<IntArray>): Int {
    val n = a.size
    var ans = n * n

    repeat(n) { r ->
      repeat(n) { c ->
        if (a[r][c] == BOMB) {
          ans--
          repeat(dc.size) { k ->
            val nr = r + dr[k]
            val nc = c + dc[k]
            if (nr in 0 until n && nc in 0 until n && a[nr][nc] == SAFE) {
              ans--
              a[nr][nc] = UNSAFE
            }
          }
        }
      }
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(
    arrayOf(intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0),
      intArrayOf(0, 0, 1, 0, 0), intArrayOf(0, 0, 0, 0, 0))) == 16)

  check(s.solution(
    arrayOf(intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0),
      intArrayOf(0, 0, 1, 1, 0), intArrayOf(0, 0, 0, 0, 0))) == 13)

  check(s.solution(
    arrayOf(intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 1, 0, 0, 0), intArrayOf(0, 0, 1, 0, 0),
      intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0))) == 11)
}
