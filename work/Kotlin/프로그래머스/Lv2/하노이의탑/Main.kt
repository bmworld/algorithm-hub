package 프로그래머스.Lv2.하노이의탑

import util.validate

class Solution {

  fun solution(n: Int): Array<IntArray> {
    var ans = Array<IntArray>((1 shl n) - 1) { IntArray(2) }
    var i = 0

    fun move(fr: Int, to: Int, cnt: Int) {
      if (cnt == 0) return

      val mid = when {
        fr in 1..2 && to in 1..2 -> 3
        fr in 2..3 && to in 2..3 -> 1
        else -> 2
      }

      move(fr, mid, cnt - 1)
      ans[i++] = intArrayOf(fr, to)
      move(mid, to, cnt - 1)
    }

    move(1, 3, n)

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.18ms, 59.9MB)
 * 테스트 2 〉	통과 (0.18ms, 59.5MB)
 * 테스트 3 〉	통과 (0.23ms, 61.7MB)
 * 테스트 4 〉	통과 (0.18ms, 60.2MB)
 * 테스트 5 〉	통과 (0.20ms, 60.5MB)
 * 테스트 6 〉	통과 (0.24ms, 60.7MB)
 * 테스트 7 〉	통과 (0.25ms, 60.3MB)
 * 테스트 8 〉	통과 (0.43ms, 60.8MB)
 * 테스트 9 〉	통과 (0.52ms, 62MB)
 * 테스트 10 〉	통과 (0.75ms, 62.3MB)
 *
 * [RIVAL]
 * class Solution
 * {
 *     var answer = mutableListOf<IntArray>()
 *
 *     fun solution(n: Int): Array<IntArray>
 *     {
 *         hanoi(n, 1, 2, 3)
 *
 *         return answer.toTypedArray()
 *     }
 *
 *     fun hanoi(n: Int, from: Int, _by: Int, to: Int)
 *     {
 *         if (n == 1)
 *             answer.add(intArrayOf(from, to))
 *         else
 *         {
 *             hanoi(n - 1, from, to, _by)
 *             answer.add(intArrayOf(from, to))
 *             hanoi(n - 1, _by, from, to)
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (0.03ms, 60.1MB)
 * 테스트 2 〉	통과 (0.02ms, 60.1MB)
 * 테스트 3 〉	통과 (0.03ms, 59.8MB)
 * 테스트 4 〉	통과 (0.05ms, 58.2MB)
 * 테스트 5 〉	통과 (0.05ms, 59.4MB)
 * 테스트 6 〉	통과 (0.08ms, 60.5MB)
 * 테스트 7 〉	통과 (0.16ms, 61.3MB)
 * 테스트 8 〉	통과 (0.23ms, 62MB)
 * 테스트 9 〉	통과 (0.32ms, 61.3MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(n: Int): Array<IntArray> {
 *         var array = ArrayList<IntArray>()
 *         getHanoi(n, 1, 3, array)
 *         var answer = arrayOf<IntArray>()
 *         return Array<IntArray>(array.size) {i -> array[i]}
 *     }
 *
 *     fun getHanoi(n: Int, from: Int, to: Int, array: ArrayList<IntArray>) {
 *         if(n == 1) array.add(move(from, to))
 *         else {
 *             getHanoi(n - 1, from, getEmptyIndex(from, to), array)
 *             array.add(move(from, to))
 *             getHanoi(n - 1, getEmptyIndex(from, to), to, array)
 *         }
 *     }
 *
 *     fun move(from: Int, to: Int): IntArray = intArrayOf(from, to)
 *     fun getEmptyIndex(from: Int, to: Int) = 6 - from - to
 * }
 * 테스트 1 〉	통과 (0.03ms, 60.3MB)
 * 테스트 2 〉	통과 (0.02ms, 60.4MB)
 * 테스트 3 〉	통과 (0.03ms, 60.1MB)
 * 테스트 4 〉	통과 (0.07ms, 59.2MB)
 * 테스트 5 〉	통과 (0.07ms, 61.6MB)
 * 테스트 6 〉	통과 (0.14ms, 59.8MB)
 * 테스트 7 〉	통과 (0.24ms, 61.6MB)
 * 테스트 8 〉	통과 (1.10ms, 60.1MB)
 * 테스트 9 〉	통과 (0.43ms, 60.8MB)
 * 테스트 10 〉	통과 (0.97ms, 62.1MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()

  validate(s.solution(1),
    arrayOf(
      intArrayOf(1, 3)
    )
  )

  validate(s.solution(2),
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(1, 3),
      intArrayOf(2, 3),
    )
  )

  validate(s.solution(3),
    arrayOf(
      intArrayOf(1, 3),
      intArrayOf(1, 2),
      intArrayOf(3, 2),
      intArrayOf(1, 3),
      intArrayOf(2, 1),
      intArrayOf(2, 3),
      intArrayOf(1, 3),
    )
  )


  validate(s.solution(4),
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(1, 3),
      intArrayOf(2, 3),
      intArrayOf(1, 2),
      intArrayOf(3, 1),
      intArrayOf(3, 2),
      intArrayOf(1, 2),
      intArrayOf(1, 3),
      intArrayOf(2, 3),
      intArrayOf(2, 1),
      intArrayOf(3, 1),
      intArrayOf(2, 3),
      intArrayOf(1, 2),
      intArrayOf(1, 3),
      intArrayOf(2, 3)
    )
  )

}
