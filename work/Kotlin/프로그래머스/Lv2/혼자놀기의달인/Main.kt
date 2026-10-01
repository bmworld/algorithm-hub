package 프로그래머스.Lv2.혼자놀기의달인

import util.validate

class Solution {

  fun solution(cards: IntArray): Int {
    val N = cards.size
    val used = BooleanArray(N + 1)

    var t1 = 0
    var t2 = 0

    fun dfs(x: Int, cnt: Int) {
      if (used[x]) {
        when {
          cnt > t1 -> {
            t2 = t1
            t1 = cnt
          }
          cnt > t2 -> t2 = cnt
        }
        return
      }

      used[x] = true
      dfs(cards[x - 1], cnt + 1)
    }

    for (x in cards) dfs(x, 0)
    return t1 * t2
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.21ms, 59.2MB)
 * 테스트 2 〉	통과 (0.18ms, 57.8MB)
 * 테스트 3 〉	통과 (0.18ms, 60.1MB)
 * 테스트 4 〉	통과 (0.27ms, 60.1MB)
 * 테스트 5 〉	통과 (0.23ms, 59.3MB)
 * 테스트 6 〉	통과 (0.21ms, 57.4MB)
 * 테스트 7 〉	통과 (0.15ms, 59.4MB)
 * 테스트 8 〉	통과 (0.21ms, 58MB)
 * 테스트 9 〉	통과 (0.22ms, 59.8MB)
 * 테스트 10 〉	통과 (0.17ms, 60.8MB)
 *
 * [RIVAL 1]
 * import java.util.*
 *
 * class Solution {
 *     fun solution(cards: IntArray): Int {
 *         var a = Array<Boolean>(cards.size, {false})
 *         var q = PriorityQueue<Int>(Collections.reverseOrder())
 *
 *
 *         while (a.any {it==false}) {
 *             var cnt = 0
 *             var cur = a.indexOfFirst {it==false}
 *             while (a[cur] == false) {
 *                 a[cur] = true
 *                 cur = cards[cur]-1
 *                 cnt += 1
 *             }
 *             q.add(cnt)
 *         }
 *
 *         val m = q.poll()
 *         if (q.isEmpty()) {
 *             return 0
 *         } else {
 *             return m * q.poll()
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (1.24ms, 59.8MB)
 * 테스트 2 〉	통과 (0.29ms, 59.3MB)
 * 테스트 3 〉	통과 (0.77ms, 59.6MB)
 * 테스트 4 〉	통과 (0.32ms, 59.6MB)
 * 테스트 5 〉	통과 (0.35ms, 58.5MB)
 * 테스트 6 〉	통과 (0.34ms, 60.4MB)
 * 테스트 7 〉	통과 (0.49ms, 60.6MB)
 * 테스트 8 〉	통과 (0.55ms, 59.4MB)
 * 테스트 9 〉	통과 (0.48ms, 57.8MB)
 * 테스트 10 〉	통과 (0.42ms, 60.6MB)
 *
 * [RIVAL 2]
 * import java.util.*
 *
 * class Solution {
 *     fun solution(cards: IntArray): Int {
 *         val hs = HashSet<Int>()
 *         val al = ArrayList<Int>()
 *
 *         for (i in cards.indices) {
 *             if (hs.contains(i)) continue
 *
 *             var cnt = 1
 *             var next = cards[i] - 1
 *             hs.add(i)
 *
 *             while (next != i) {
 *                 cnt++
 *                 hs.add(next)
 *                 next = cards[next] - 1
 *             }
 *             al.add(cnt)
 *         }
 *
 *         al.sortDescending()
 *         return if (al.size == 1) 0 else al[0] * al[1]
 *     }
 * }
 * 테스트 1 〉	통과 (5.37ms, 60.2MB)
 * 테스트 2 〉	통과 (5.30ms, 60.5MB)
 * 테스트 3 〉	통과 (5.85ms, 61.3MB)
 * 테스트 4 〉	통과 (4.58ms, 62MB)
 * 테스트 5 〉	통과 (4.71ms, 60.8MB)
 * 테스트 6 〉	통과 (9.80ms, 60.9MB)
 * 테스트 7 〉	통과 (5.24ms, 60.7MB)
 * 테스트 8 〉	통과 (7.87ms, 60.8MB)
 * 테스트 9 〉	통과 (4.89ms, 59.2MB)
 * 테스트 10 〉	통과 (4.60ms, 61MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(8, 6, 3, 7, 2, 5, 1, 4)), 12)
  validate(s.solution(intArrayOf(1, 2)), 1)
  validate(s.solution(intArrayOf(2, 1)), 0)
  validate(s.solution(intArrayOf(1, 2, 3, 4)), 1)
  validate(s.solution(intArrayOf(2, 3, 4, 1)), 0)
  validate(s.solution(intArrayOf(2, 1, 4, 3, 6, 5)), 4)
}
