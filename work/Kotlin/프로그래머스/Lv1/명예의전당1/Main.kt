package 프로그래머스.Lv1.명예의전당1

import util.validate

class Solution {

  val MAX = 1_000
  fun solution(k: Int, score: IntArray): IntArray {
    var ans = IntArray(score.size)
    val h = HEAP(MAX)
    for (i in 0 until score.size) {
      val v = score[i]
      when {
        i < k -> {
          h.push(v)
          ans[i] = h.peak()
        }
        else -> {
          val min = h.peak()
          if (v <= min) ans[i] = min
          else {
            h.pop()
            h.push(v)
            ans[i] = h.peak()
          }
        }
      }
    }
    return ans
  }
}

class HEAP(size: Int) {

  val heap = IntArray(size + 1)
  val root = 1
  var len = 0

  fun clear() {
    len = 0
  }

  fun isNotEmpty() = len > 0

  fun push(v: Int) {
    var ci = ++len
    heap[len] = v
    while (ci > root) {
      val pi = ci shr 1
      val p = heap[pi]
      val c = heap[ci]
      if (p > c) {
        heap[pi] = c
        heap[ci] = p
        ci = pi
      } else break
    }
  }

  fun pop(): Int {
    if (len == 0) return 0
    val v = heap[root]
    val x = heap[len--]

    var pi = root
    var ci = root shl 1
    while (ci <= len) {
      val ri = ci + 1
      if (ri <= len && heap[ri] < heap[ci]) ci++
      if (heap[ci] >= x) break
      heap[pi] = heap[ci]
      pi = ci
      ci = pi shl 1
    }
    heap[pi] = x
    return v
  }

  fun peak(): Int = heap[root]
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.19ms, 58.9MB)
 * 테스트 2 〉	통과 (0.19ms, 58.4MB)
 * 테스트 3 〉	통과 (0.18ms, 59.6MB)
 * 테스트 4 〉	통과 (0.23ms, 58.8MB)
 * 테스트 5 〉	통과 (0.21ms, 59.7MB)
 * 테스트 6 〉	통과 (0.28ms, 58.6MB)
 * 테스트 7 〉	통과 (0.20ms, 59.7MB)
 * 테스트 8 〉	통과 (0.19ms, 58.3MB)
 * 테스트 9 〉	통과 (0.20ms, 59.8MB)
 * 테스트 10 〉	통과 (0.20ms, 58.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import java.util.*
 *
 * class Solution {
 *     fun solution(k: Int, score: IntArray): IntArray {
 *         var answer = mutableListOf<Int>()
 *         val pq = PriorityQueue<Int>()
 *         for(item in score) {
 *             if(pq.size < k) {
 *                 pq.add(item)
 *             }
 *             else {
 *                 if(pq.peek() < item) {
 *                     pq.add(item)
 *                     pq.poll()
 *                 }
 *             }
 *             answer.add(pq.peek())
 *         }
 *         return answer.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (3.88ms, 60.8MB)
 * 테스트 2 〉	통과 (9.65ms, 60.1MB)
 * 테스트 3 〉	통과 (4.35ms, 59.1MB)
 * 테스트 4 〉	통과 (4.30ms, 61MB)
 * 테스트 5 〉	통과 (4.26ms, 59.5MB)
 * 테스트 6 〉	통과 (4.36ms, 59.2MB)
 * 테스트 7 〉	통과 (4.42ms, 59.5MB)
 * 테스트 8 〉	통과 (9.36ms, 59.5MB)
 * 테스트 9 〉	통과 (4.51ms, 59.2MB)
 * 테스트 10 〉	통과 (4.54ms, 58.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, intArrayOf(10, 100, 20, 150, 1, 100, 200)),
    intArrayOf(10, 10, 10, 20, 20, 100, 100))
  validate(s.solution(4, intArrayOf(0, 300, 40, 300, 20, 70, 150, 50, 500, 1000)),
    intArrayOf(0, 0, 0, 0, 20, 40, 70, 70, 150, 300))
}
