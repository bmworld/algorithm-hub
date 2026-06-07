package 프로그래머스.Lv1.과일장수

import util.validate

class Solution {

  fun solution(k: Int, m: Int, score: IntArray): Int {
    val cnts = IntArray(k + 1)
    for (x in score) cnts[x]++

    var ans = 0
    var rmn = 0
    for (p in k downTo 1) {
      val cnt = cnts[p]
      if (cnt == 0) continue
      val sum = rmn + cnt
      ans += p * m * (sum / m)
      rmn = sum % m
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 57.9MB)
 * 테스트 2 〉	통과 (0.01ms, 59.5MB)
 * 테스트 3 〉	통과 (0.01ms, 59.2MB)
 * 테스트 4 〉	통과 (0.02ms, 58.8MB)
 * 테스트 5 〉	통과 (0.01ms, 59.6MB)
 * 테스트 6 〉	통과 (1.14ms, 60.2MB)
 * 테스트 7 〉	통과 (1.17ms, 62.2MB)
 * 테스트 8 〉	통과 (0.19ms, 59.9MB)
 * 테스트 9 〉	통과 (1.33ms, 61.7MB)
 * 테스트 10 〉	통과 (0.83ms, 61MB)
 * 테스트 11 〉	통과 (3.10ms, 89.6MB)
 * 테스트 12 〉	통과 (3.11ms, 90.7MB)
 * 테스트 13 〉	통과 (3.30ms, 90.4MB)
 * 테스트 14 〉	통과 (3.19ms, 90.2MB)
 * 테스트 15 〉	통과 (3.23ms, 89.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(k: Int, m: Int, score: IntArray): Int {
 *         var answer: Int = 0
 *         score.sortDescending()
 *         var num =0
 *         score.forEach{
 *             num+=1
 *             if(num%m == 0){
 *                answer+= it*m
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (13.10ms, 63.5MB)
 * 테스트 2 〉	통과 (9.99ms, 62.7MB)
 * 테스트 3 〉	통과 (12.79ms, 62MB)
 * 테스트 4 〉	통과 (12.09ms, 62MB)
 * 테스트 5 〉	통과 (12.15ms, 63MB)
 * 테스트 6 〉	통과 (20.61ms, 65.4MB)
 * 테스트 7 〉	통과 (17.34ms, 66.1MB)
 * 테스트 8 〉	통과 (13.14ms, 64.3MB)
 * 테스트 9 〉	통과 (17.23ms, 65.9MB)
 * 테스트 10 〉	통과 (14.92ms, 65.6MB)
 * 테스트 11 〉	통과 (37.89ms, 94.4MB)
 * 테스트 12 〉	통과 (36.36ms, 94.7MB)
 * 테스트 13 〉	통과 (44.54ms, 93.7MB)
 * 테스트 14 〉	통과 (40.49ms, 93.7MB)
 * 테스트 15 〉	통과 (44.37ms, 94.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, 4, intArrayOf(1, 2, 3, 1, 2, 3, 1)), 8)
  validate(s.solution(4, 3, intArrayOf(4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2)), 33)
}

//println("p = $p, cnt=$cnt, rmn=$rmn / price = $price")
