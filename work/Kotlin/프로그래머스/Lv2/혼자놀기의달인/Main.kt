package 프로그래머스.Lv2.혼자놀기의달인

import util.validate

class Solution {

  fun solution(cards: IntArray): Int {
    val N = cards.size
    val used = BooleanArray(N + 1)
    val cnter = IntArray(N)
    var groups = 0


    fun dfs(x: Int, cnt: Int) {
      if (used[x]) {
        if (cnt > 0) cnter[groups++] = cnt
        return
      }

      used[x] = true
      dfs(cards[x - 1], cnt + 1)
    }


    for (x in cards) dfs(x, 0)

    var t1 = 0
    var t2 = 0
    repeat(groups) {
      val cnt = cnter[it]
      when {
        cnt > t1 -> t1 = cnt
        cnt > t2 -> t2 = cnt
      }
    }

    return t1 * t2
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.21ms, 60.2MB)
 * 테스트 2 〉	통과 (0.17ms, 59.7MB)
 * 테스트 3 〉	통과 (0.22ms, 59.4MB)
 * 테스트 4 〉	실패 (0.18ms, 59.5MB)
 * 테스트 5 〉	통과 (0.19ms, 61MB)
 * 테스트 6 〉	실패 (0.22ms, 60.4MB)
 * 테스트 7 〉	통과 (0.25ms, 59MB)
 * 테스트 8 〉	통과 (0.31ms, 59.4MB)
 * 테스트 9 〉	통과 (0.59ms, 59.9MB)
 * 테스트 10 〉	통과 (0.19ms, 60.6MB)
 * 테스트 11 〉	통과 (0.20ms, 59.1MB)
 * 테스트 12 〉	통과 (0.60ms, 58.2MB)
 * 테스트 13 〉	통과 (0.52ms, 60.1MB)
 * 테스트 14 〉	통과 (0.29ms, 58.5MB)
 * 테스트 15 〉	실패 (0.23ms, 58.9MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(8, 6, 3, 7, 2, 5, 1, 4)), 12)
}
