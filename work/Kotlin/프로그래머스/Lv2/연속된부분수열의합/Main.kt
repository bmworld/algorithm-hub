package 프로그래머스.Lv2.연속된부분수열의합

import util.validate

class Solution {

  fun solution(seq: IntArray, k: Int): IntArray {
    val N = seq.size
    var fr = 0
    var to = N - 1

    var acc = seq[0]
    var l = 0
    var r = 0

    fun add() {
      if (++r < N) acc += seq[r]
    }

    fun subt() {
      acc -= seq[l++]
    }

    while (l <= r && r < N) {
      when {
        acc < k -> add()
        acc > k -> subt()
        else -> {
          if (r - l + 1 < to - fr + 1) {
            fr = l
            to = r
          }

          add()
          subt()
        }
      }
    }
    return intArrayOf(fr, to)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.16ms, 60.5MB)
 * 테스트 2 〉	통과 (0.15ms, 60.8MB)
 * 테스트 3 〉	통과 (0.20ms, 60.7MB)
 * 테스트 4 〉	통과 (0.33ms, 59.8MB)
 * 테스트 5 〉	통과 (0.86ms, 61MB)
 * 테스트 6 〉	통과 (1.03ms, 62.2MB)
 * 테스트 7 〉	통과 (2.44ms, 63.1MB)
 * 테스트 8 〉	통과 (2.78ms, 66.3MB)
 * 테스트 9 〉	통과 (3.92ms, 70.4MB)
 * 테스트 10 〉	통과 (5.47ms, 82.4MB)
 * 테스트 11 〉	통과 (8.03ms, 96.6MB)
 * 테스트 12 〉	통과 (9.09ms, 98MB)
 * 테스트 13 〉	통과 (9.28ms, 99MB)
 * 테스트 14 〉	통과 (8.81ms, 94.8MB)
 * 테스트 15 〉	통과 (8.41ms, 97.5MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(sequence: IntArray, k: Int): IntArray {
 *         var answer = intArrayOf(0,0)
 *
 *         var start = 0
 *         var end = 0
 *         var sum = sequence[0]
 *         var distance = Int.MAX_VALUE
 *
 *         while(start < sequence.size && end < sequence.size) {
 *             when {
 *                 sum < k && end < sequence.size - 1 -> {
 *                     end += 1
 *                     sum += sequence[end]
 *                 }
 *                 sum == k -> {
 *                     if(end - start < distance) {
 *                         answer[0] = start
 *                         answer[1] = end
 *                         distance = end - start
 *                     }
 *                     sum -= sequence[start]
 *                     start += 1
 *                 }
 *                 else -> {
 *                     sum -= sequence[start]
 *                     start += 1
 *                 }
 *             }
 *
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 59.3MB)
 * 테스트 2 〉	통과 (0.01ms, 60.2MB)
 * 테스트 3 〉	통과 (0.01ms, 60.7MB)
 * 테스트 4 〉	통과 (0.07ms, 60.1MB)
 * 테스트 5 〉	통과 (0.48ms, 61.2MB)
 * 테스트 6 〉	통과 (0.98ms, 61.1MB)
 * 테스트 7 〉	통과 (1.87ms, 63.7MB)
 * 테스트 8 〉	통과 (2.13ms, 65.1MB)
 * 테스트 9 〉	통과 (3.54ms, 68.7MB)
 * 테스트 10 〉	통과 (4.85ms, 82.7MB)
 * 테스트 11 〉	통과 (7.81ms, 97.1MB)
 * 테스트 12 〉	통과 (7.37ms, 96MB)
 * 테스트 13 〉	통과 (7.27ms, 96.8MB)
 * 테스트 14 〉	통과 (7.21ms, 97.2MB)
 * 테스트 15 〉	통과 (9.14ms, 96.2MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(sequence: IntArray, k: Int): IntArray {
 *         var answer = intArrayOf(0, sequence.size)
 *         val preSum = IntArray(sequence.size + 1)
 *         for (i in sequence.indices) {
 *             preSum[i + 1] = sequence[i] + preSum[i]
 *         }
 *
 *         var i = 0
 *         var j = 0
 *
 *         while (i < sequence.size && j < sequence.size) {
 *             val c = preSum[j + 1] - preSum[i]
 *             when {
 *                 c == k -> {
 *                     if (answer[1] - answer[0] > j - i) answer = intArrayOf(i, j)
 *                     j++
 *                 }
 *                 c < k -> j++
 *                 else -> i++
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 60.8MB)
 * 테스트 2 〉	통과 (0.02ms, 58.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59.3MB)
 * 테스트 4 〉	통과 (0.07ms, 59.4MB)
 * 테스트 5 〉	통과 (0.68ms, 61.2MB)
 * 테스트 6 〉	통과 (1.04ms, 61.2MB)
 * 테스트 7 〉	통과 (1.71ms, 62.6MB)
 * 테스트 8 〉	통과 (2.17ms, 65.3MB)
 * 테스트 9 〉	통과 (4.50ms, 69.7MB)
 * 테스트 10 〉	통과 (6.00ms, 84.2MB)
 * 테스트 11 〉	통과 (9.10ms, 99.2MB)
 * 테스트 12 〉	통과 (9.48ms, 102MB)
 * 테스트 13 〉	통과 (14.89ms, 104MB)
 * 테스트 14 〉	통과 (10.38ms, 101MB)
 * 테스트 15 〉	통과 (10.52ms, 101MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 7, 11, 22), 7), intArrayOf(1, 1))
  validate(s.solution(intArrayOf(1, 2, 3, 4, 5), 7), intArrayOf(2, 3))
  validate(s.solution(intArrayOf(1, 1, 1, 2, 3, 4, 5), 5), intArrayOf(6, 6))
  validate(s.solution(intArrayOf(2, 2, 2, 2, 2), 6), intArrayOf(0, 2))
}
