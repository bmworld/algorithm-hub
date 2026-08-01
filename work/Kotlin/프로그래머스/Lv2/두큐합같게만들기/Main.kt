package 프로그래머스.Lv2.두큐합같게만들기

import util.validate

class Solution {
  companion object {

    const val INF = Int.MAX_VALUE
  }

  fun solution(q1: IntArray, q2: IntArray): Int {

    val s1 = sumOfArr(q1)
    var s2 = sumOfArr(q2)
    val total = s1 + s2
    if (total % 2 != 0L) return -1

    var ans = INF
    val half = total / 2

    val len = q1.size
    val q1End = len - 1

    var sum = 0L
    var l = 0
    var r = -1

    fun add() {
      r++
      sum += if (r < len) q1[r] else if (r < 2 * len) q2[r - len] else 0
    }

    fun subt() {
      sum -= if (l < len) q1[l] else q2[l - len]
      l++
    }

    while (r < 2 * len) {
      when {
        sum < half -> add()
        sum > half -> subt()
        else -> {
          val cnt = if (q1End in l..r) {
            l + if (r > q1End) r - q1End else 0
          } else {
            val fr = l - if (l >= len) len else 0
            val to = r - if (r >= len) len else 0
            (to + 1) + fr + len
          }
          if (cnt < ans) ans = cnt

          add()
          subt()
        }
      }
    }

    return if (ans == INF) -1 else ans
  }

  fun sumOfArr(arr: IntArray): Long {
    var r = 0L
    for (x in arr) r += x
    return r
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.34ms, 59.5MB)
 * 테스트 2 〉	통과 (0.30ms, 60.7MB)
 * 테스트 3 〉	통과 (0.37ms, 59.6MB)
 * 테스트 4 〉	통과 (0.45ms, 59.5MB)
 * 테스트 5 〉	통과 (0.26ms, 60.6MB)
 * 테스트 6 〉	통과 (0.30ms, 60.3MB)
 * 테스트 7 〉	통과 (0.43ms, 61.8MB)
 * 테스트 8 〉	통과 (0.41ms, 60.5MB)
 * 테스트 9 〉	통과 (0.58ms, 60.7MB)
 * 테스트 10 〉	통과 (0.59ms, 61.1MB)
 * 테스트 11 〉	통과 (4.01ms, 63.8MB)
 * 테스트 12 〉	통과 (4.00ms, 65MB)
 * 테스트 13 〉	통과 (4.60ms, 65.2MB)
 * 테스트 14 〉	통과 (5.62ms, 66.3MB)
 * 테스트 15 〉	통과 (5.06ms, 68.8MB)
 * 테스트 16 〉	통과 (5.47ms, 69.4MB)
 * 테스트 17 〉	통과 (5.50ms, 69.1MB)
 * 테스트 18 〉	통과 (11.39ms, 86.5MB)
 * 테스트 19 〉	통과 (10.20ms, 86.5MB)
 * 테스트 20 〉	통과 (10.50ms, 84.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *
 *     fun solution(queue1: IntArray, queue2: IntArray): Int {
 *         val firstQueue = ArrayDeque(queue1.map { it.toLong() }.toList())
 *         val secondQueue = ArrayDeque(queue2.map { it.toLong() }.toList())
 *
 *         return getMinCount(firstQueue, secondQueue, firstQueue.sum(), secondQueue.sum(), 0, queue1.size * 3)
 *     }
 *
 *     private tailrec fun getMinCount(
 *         firstQueue: ArrayDeque<Long>,
 *         secondQueue: ArrayDeque<Long>,
 *         firstSum: Long,
 *         secondSum: Long,
 *         count: Int,
 *         chance: Int
 *     ): Int {
 *         when {
 *             firstSum == secondSum -> return count
 *             count == chance -> return -1
 *             firstSum > secondSum -> {
 *                 val data = firstQueue.removeFirst()
 *                 secondQueue.add(data)
 *
 *                 return getMinCount(
 *                     firstQueue, secondQueue,
 *                     firstSum - data, secondSum + data, count + 1, chance
 *                 )
 *             }
 *             else -> {
 *                 val data = secondQueue.removeFirst()
 *                 firstQueue.add(data)
 *
 *                 return getMinCount(
 *                     firstQueue, secondQueue,
 *                     firstSum + data, secondSum - data, count + 1, chance
 *                 )
 *             }
 *         }
 *     }
 *
 * }
 * 테스트 1 〉	통과 (14.26ms, 64.3MB)
 * 테스트 2 〉	통과 (5.68ms, 59.2MB)
 * 테스트 3 〉	통과 (15.05ms, 64.6MB)
 * 테스트 4 〉	통과 (13.87ms, 64.4MB)
 * 테스트 5 〉	통과 (14.00ms, 64.8MB)
 * 테스트 6 〉	통과 (14.11ms, 65.3MB)
 * 테스트 7 〉	통과 (14.31ms, 65.6MB)
 * 테스트 8 〉	통과 (14.61ms, 64.7MB)
 * 테스트 9 〉	통과 (15.29ms, 65MB)
 * 테스트 10 〉	통과 (15.12ms, 66MB)
 * 테스트 11 〉	통과 (33.17ms, 76MB)
 * 테스트 12 〉	통과 (25.19ms, 74.4MB)
 * 테스트 13 〉	통과 (22.77ms, 71.8MB)
 * 테스트 14 〉	통과 (24.79ms, 72.2MB)
 * 테스트 15 〉	통과 (25.04ms, 80.3MB)
 * 테스트 16 〉	통과 (25.33ms, 81.1MB)
 * 테스트 17 〉	통과 (25.89ms, 80.3MB)
 * 테스트 18 〉	통과 (38.59ms, 110MB)
 * 테스트 19 〉	통과 (51.18ms, 115MB)
 * 테스트 20 〉	통과 (43.59ms, 117MB)
 *
 * [RIVAL 2]
 * import java.util.*
 * class Solution {
 *
 *     fun solution(queue1: IntArray, queue2: IntArray): Int {
 *         val q1 = LinkedList<Int>()
 *         val q2 = LinkedList<Int>()
 *             q1.addAll(queue1.toTypedArray())
 *             q2.addAll(queue2.toTypedArray())
 *             var one = queue1.sum().toLong()
 *         val goal = (one + queue2.sum().toLong()) / 2
 *         var count = 0
 *
 *         while(one != goal){
 *             if(one > goal){
 *                 one -= q1[0]
 *                 q2.add(q1.pop())
 *
 *             }else{
 *                 one += q2[0]
 *                 q1.add(q2.pop())
 *             }
 *            count++
 *             if(count > queue1.size*4) {return -1}
 *         }
 *         return count
 *     }
 * }
 * 테스트 1 〉	통과 (12.20ms, 63.6MB)
 * 테스트 2 〉	통과 (12.76ms, 63.8MB)
 * 테스트 3 〉	통과 (11.89ms, 63.5MB)
 * 테스트 4 〉	통과 (14.19ms, 63.8MB)
 * 테스트 5 〉	통과 (12.11ms, 63.7MB)
 * 테스트 6 〉	통과 (12.31ms, 64.1MB)
 * 테스트 7 〉	통과 (13.20ms, 64MB)
 * 테스트 8 〉	통과 (12.40ms, 64.2MB)
 * 테스트 9 〉	통과 (13.13ms, 65MB)
 * 테스트 10 〉	통과 (14.25ms, 64.1MB)
 * 테스트 11 〉	통과 (27.76ms, 78.4MB)
 * 테스트 12 〉	통과 (22.43ms, 74.1MB)
 * 테스트 13 〉	통과 (19.06ms, 73MB)
 * 테스트 14 〉	통과 (19.33ms, 74.8MB)
 * 테스트 15 〉	통과 (22.10ms, 80.6MB)
 * 테스트 16 〉	통과 (22.03ms, 83.2MB)
 * 테스트 17 〉	통과 (22.97ms, 81.7MB)
 * 테스트 18 〉	통과 (111.90ms, 154MB)
 * 테스트 19 〉	통과 (108.41ms, 154MB)
 * 테스트 20 〉	통과 (89.83ms, 150MB)
 * [RIVAL 3]
 * class Solution {
 *     fun solution(queue1: IntArray, queue2: IntArray): Int {
 *         val n = queue1.size
 *         val merged = LongArray(2 * n).apply {
 *             for (i in 0 until n) {
 *                 this[i] = queue1[i].toLong()
 *                 this[i + n] = queue2[i].toLong()
 *             }
 *         }
 *
 *         val total = merged.sum()
 *         if (total % 2L != 0L) {
 *             return -1
 *         }
 *
 *         val target = total / 2L
 *         var sum = queue1.sumOf { it.toLong() }
 *         var count = 0
 *         var left = 0
 *         var right = n
 *
 *         while (count <= 4 * n) {
 *             if (sum == target) {
 *                 return count
 *             }
 *
 *             if (sum < target) {
 *                 sum += merged[right % (2 * n)]
 *                 ++right
 *             } else {
 *                 sum -= merged[left % (2 * n)]
 *                 ++left
 *             }
 *             ++count
 *         }
 *
 *         return -1
 *     }
 * }
 * 테스트 1 〉	통과 (8.59ms, 63.2MB)
 * 테스트 2 〉	통과 (8.55ms, 62.6MB)
 * 테스트 3 〉	통과 (9.73ms, 64.1MB)
 * 테스트 4 〉	통과 (9.40ms, 64.3MB)
 * 테스트 5 〉	통과 (8.75ms, 63.8MB)
 * 테스트 6 〉	통과 (8.61ms, 64.3MB)
 * 테스트 7 〉	통과 (8.71ms, 63.6MB)
 * 테스트 8 〉	통과 (8.58ms, 64.2MB)
 * 테스트 9 〉	통과 (8.66ms, 64.1MB)
 * 테스트 10 〉	통과 (12.88ms, 63.9MB)
 * 테스트 11 〉	통과 (12.56ms, 69.3MB)
 * 테스트 12 〉	통과 (16.24ms, 68.5MB)
 * 테스트 13 〉	통과 (12.64ms, 68.4MB)
 * 테스트 14 〉	통과 (12.85ms, 70.2MB)
 * 테스트 15 〉	통과 (12.44ms, 73.6MB)
 * 테스트 16 〉	통과 (15.85ms, 74.1MB)
 * 테스트 17 〉	통과 (14.56ms, 74.2MB)
 * 테스트 18 〉	통과 (17.56ms, 93.6MB)
 * 테스트 19 〉	통과 (15.71ms, 95.4MB)
 * 테스트 20 〉	통과 (15.83ms, 95.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(intArrayOf(3, 2, 7, 2), intArrayOf(4, 6, 5, 1)),
    2
  )

  validate(
    s.solution(intArrayOf(1, 2, 1, 2), intArrayOf(1, 10, 1, 2)),
    7
  )

  validate(
    s.solution(intArrayOf(1, 1), intArrayOf(1, 5)),
    -1
  )
}
