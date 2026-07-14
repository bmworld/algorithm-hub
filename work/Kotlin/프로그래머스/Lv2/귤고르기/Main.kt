package 프로그래머스.Lv2.귤고르기

import util.validate

class Solution {

  fun solution(k: Int, tangerine: IntArray): Int {
    val map = HashMap<Int, Int>()
    var len = 0
    for (x in tangerine) map[x] = (map[x] ?: 0.also { len++ }) + 1

    val cnts = IntArray(len)
    var i = 0
    for (x in map) cnts[i++] = x.value

    qs(cnts, 0, len - 1)

    var ans = 0
    var picked = 0
    for (i in len - 1 downTo 0) {
      if (picked >= k) break
      picked += cnts[i]
      ans++
    }

    return ans
  }

  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    while (pos <= pr) {
      val x = a[pos]
      when {
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }
    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (8.72ms, 68.2MB)
 * 테스트 2 〉	통과 (11.52ms, 66.8MB)
 * 테스트 3 〉	통과 (8.83ms, 67.6MB)
 * 테스트 4 〉	통과 (8.90ms, 69.4MB)
 * 테스트 5 〉	통과 (9.19ms, 65.7MB)
 * 테스트 6 〉	통과 (8.80ms, 64.5MB)
 * 테스트 7 〉	통과 (11.03ms, 67.3MB)
 * 테스트 8 〉	통과 (9.50ms, 66.4MB)
 * 테스트 9 〉	통과 (9.56ms, 66.7MB)
 * 테스트 10 〉	통과 (14.83ms, 67.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(k: Int, tangerine: IntArray): Int {
 *         var answer: Int = 0
 *         var limit = 0
 *         tangerine.groupBy { it }.toList().sortedByDescending { it.second.size }.forEach{
 *         if(limit >= k){
 *             return answer
 *         }
 *         limit += it.second.size
 *         answer++
 *     }
 *
 *     return answer
 *     }
 * }
 * 테스트 1 〉	통과 (23.70ms, 73.1MB)
 * 테스트 2 〉	통과 (27.42ms, 72.9MB)
 * 테스트 3 〉	통과 (23.82ms, 73.5MB)
 * 테스트 4 〉	통과 (21.69ms, 74.7MB)
 * 테스트 5 〉	통과 (22.74ms, 70.1MB)
 * 테스트 6 〉	통과 (22.31ms, 71.2MB)
 * 테스트 7 〉	통과 (23.85ms, 72.6MB)
 * 테스트 8 〉	통과 (23.87ms, 71.4MB)
 * 테스트 9 〉	통과 (23.24ms, 71.2MB)
 * 테스트 10 〉	통과 (23.09ms, 72.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(6, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)), 3)
  validate(s.solution(4, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)), 2)
  validate(s.solution(2, intArrayOf(1, 1, 1, 1, 2, 2, 2, 3)), 1)

}
