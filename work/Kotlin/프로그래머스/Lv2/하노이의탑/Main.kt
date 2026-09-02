package 프로그래머스.Lv2.하노이의탑

import util.validate

class Solution {

  fun solution(n: Int): Array<IntArray> {
    var ans = mutableListOf<IntArray>()

    fun move(fr: Int, mid: Int, to: Int, cnt: Int) {
      if (cnt == 0) return

      move(fr, to, mid, cnt - 1)
      ans.add(intArrayOf(fr, to))
      move(mid, fr, to, cnt - 1)
    }

    move(1, 2, 3, n)

    return ans.toTypedArray()
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
 * v2:
 * 테스트 1 〉	통과 (0.20ms, 60.6MB)
 * 테스트 2 〉	통과 (0.21ms, 59.6MB)
 * 테스트 3 〉	통과 (0.19ms, 60.5MB)
 * 테스트 4 〉	통과 (0.15ms, 60.2MB)
 * 테스트 5 〉	통과 (0.23ms, 60.5MB)
 * 테스트 6 〉	통과 (0.25ms, 60.6MB)
 * 테스트 7 〉	통과 (0.25ms, 61MB)
 * 테스트 8 〉	통과 (0.30ms, 62.7MB)
 * 테스트 9 〉	통과 (0.39ms, 61.7MB)
 * 테스트 10 〉	통과 (0.53ms, 61.7MB)
 * 테스트 11 〉	통과 (0.96ms, 66.5MB)
 * 테스트 12 〉	통과 (1.17ms, 71.5MB)
 * 테스트 13 〉	통과 (2.01ms, 76.7MB)
 *
 * v3:
 * 테스트 1 〉	통과 (0.02ms, 58.1MB)
 * 테스트 2 〉	통과 (0.03ms, 61MB)
 * 테스트 3 〉	통과 (0.03ms, 60.4MB)
 * 테스트 4 〉	통과 (0.08ms, 59.1MB)
 * 테스트 5 〉	통과 (0.06ms, 60.7MB)
 * 테스트 6 〉	통과 (0.92ms, 58.9MB)
 * 테스트 7 〉	통과 (0.18ms, 60.3MB)
 * 테스트 8 〉	통과 (0.26ms, 61.7MB)
 * 테스트 9 〉	통과 (0.34ms, 61.2MB)
 * 테스트 10 〉	통과 (0.66ms, 63.2MB)
 * 테스트 11 〉	통과 (0.59ms, 65.6MB)
 * 테스트 12 〉	통과 (4.02ms, 71.4MB)
 * 테스트 13 〉	통과 (1.78ms, 77MB)
 *
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
