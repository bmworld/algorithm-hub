package 프로그래머스.Lv0.콜라츠수열만들기

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 1000
  }

  fun solution(n: Int): IntArray {
    val tmp = IntArray(MAX_LEN)
    var len = 0
    tmp[len++] = n

    var x = n
    while (x != 1)
      tmp[len++] = (if (x % 2 == 0) x / 2 else 3 * x + 1).also { x = it }

    return tmp.copyOf(len)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.17ms, 59.4MB)
 * 테스트 2 〉	통과 (0.19ms, 60.8MB)
 * 테스트 3 〉	통과 (0.18ms, 58.6MB)
 * 테스트 4 〉	통과 (0.22ms, 58.9MB)
 * 테스트 5 〉	통과 (0.18ms, 59.6MB)
 * 테스트 6 〉	통과 (0.35ms, 58MB)
 * 테스트 7 〉	통과 (0.22ms, 58.9MB)
 * 테스트 8 〉	통과 (0.15ms, 60.9MB)
 * 테스트 9 〉	통과 (0.25ms, 57.8MB)
 * 테스트 10 〉	통과 (0.16ms, 59.7MB)
 * 테스트 11 〉	통과 (0.17ms, 60.4MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(n: Int): List<Int> {
 *         return generateSequence(n) { if (it == 1) null else if (it % 2 == 0) it / 2 else 3 * it + 1 }.toList()
 *     }
 * }
 * 테스트 1 〉	통과 (4.49ms, 60.2MB)
 * 테스트 2 〉	통과 (5.79ms, 60.3MB)
 * 테스트 3 〉	통과 (4.27ms, 60.1MB)
 * 테스트 4 〉	통과 (7.97ms, 60.1MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(n: Int): IntArray {
 *         var answer = ArrayList<Int>()
 *         answer.add(n)
 *         var num = n
 *         while(num != 1){
 *             if(num % 2 == 0){
 *                 num /= 2
 *                 answer.add(num)
 *             } else {
 *                 num = (num * 3)+1
 *                 answer.add(num)
 *             }
 *         }
 *         return answer.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (3.78ms, 60.7MB)
 * 테스트 2 〉	통과 (4.00ms, 60.6MB)
 * 테스트 3 〉	통과 (3.99ms, 59.9MB)
 * 테스트 4 〉	통과 (3.69ms, 61.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(10), intArrayOf(10, 5, 16, 8, 4, 2, 1))
  validate(s.solution(999),
    intArrayOf(
      999, 2998, 1499, 4498, 2249, 6748, 3374, 1687, 5062, 2531,
      7594, 3797, 11392, 5696, 2848, 1424, 712, 356, 178, 89,
      268, 134, 67, 202, 101, 304, 152, 76, 38, 19,
      58, 29, 88, 44, 22, 11, 34, 17, 52, 26,
      13, 40, 20, 10, 5, 16, 8, 4, 2, 1
    )
  )

  validate(s.solution(997),
    intArrayOf(
      997, 2992, 1496, 748, 374, 187, 562, 281, 844, 422, 211, 634, 317, 952, 476, 238, 119, 358,
      179, 538, 269, 808, 404, 202, 101, 304, 152, 76, 38, 19, 58, 29, 88, 44, 22, 11, 34, 17, 52,
      26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
    )
  )
}
