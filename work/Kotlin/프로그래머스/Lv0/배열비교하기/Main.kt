package 프로그래머스.Lv0.배열비교하기

import util.validate

class Solution {

  fun solution(arr1: IntArray, arr2: IntArray): Int {
    val l1 = arr1.size
    val l2 = arr2.size
    return when {
      l1 > l2 -> 1
      l1 < l2 -> -1
      else -> {
        var s1 = 0
        var s2 = 0
        for (i in 0 until l1) {
          s1 += arr1[i]
          s2 += arr2[i]
        }

        when {
          s1 > s2 -> 1
          s1 < s2 -> -1
          else -> 0
        }
      }
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.9MB)
 * 테스트 2 〉	통과 (0.01ms, 58.9MB)
 * 테스트 3 〉	통과 (0.01ms, 59.7MB)
 * 테스트 4 〉	통과 (0.01ms, 59.6MB)
 * 테스트 5 〉	통과 (0.01ms, 58.8MB)
 * 테스트 6 〉	통과 (0.01ms, 59.4MB)
 * 테스트 7 〉	통과 (0.01ms, 60.8MB)
 * 테스트 8 〉	통과 (0.01ms, 59.6MB)
 * 테스트 9 〉	통과 (0.01ms, 60.4MB)
 * 테스트 10 〉	통과 (0.01ms, 59.3MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr1: IntArray, arr2: IntArray): Int {
 *         if (arr1.size < arr2.size) return -1
 *         else if (arr1.size > arr2.size) return 1
 *         else if (arr1.sum() < arr2.sum()) return -1
 *         else if (arr1.sum() > arr2.sum()) return 1
 *         return 0
 *     }
 * }
 *
 * [RIVAL 2]
 * 테스트 1 〉	통과 (0.01ms, 58.6MB)
 * 테스트 2 〉	통과 (0.01ms, 60.8MB)
 * 테스트 3 〉	통과 (0.01ms, 59.6MB)
 * 테스트 4 〉	통과 (8.74ms, 63.2MB)
 * 테스트 5 〉	통과 (0.01ms, 59MB)
 * 테스트 6 〉	통과 (8.70ms, 61.7MB)
 * 테스트 7 〉	통과 (8.64ms, 63.6MB)
 * 테스트 8 〉	통과 (0.01ms, 60.1MB)
 * 테스트 9 〉	통과 (0.01ms, 59.5MB)
 * 테스트 10 〉	통과 (0.01ms, 60.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(49, 13),
    intArrayOf(70, 11, 2)
  ), -1)

  validate(s.solution(
    intArrayOf(49, 13, 1, 2),
    intArrayOf(70, 11, 2)
  ), 1)


  validate(s.solution(
    intArrayOf(1, 2, 3, 4, 5),
    intArrayOf(3, 3, 3, 3, 3)
  ), 0)


  validate(s.solution(
    intArrayOf(100, 17, 84, 1),
    intArrayOf(55, 12, 65, 36)
  ), 1)
}
