package 프로그래머스.알고리즘고득점Kit.Heap.DiscController

import util.validate

class Solution {

  val rIdx = 0
  val tIdx = 1
  val SEP2: Long = 1_000
  val SEP1: Long = SEP2 * 100_000
  fun encode(l: Int, s: Int, i: Int): Long = l * SEP1 + s * SEP2 + i

  fun solution(jobs: Array<IntArray>): Int {
    val len = jobs.size

    val q = HEAP(len)
    val req = HashMap<Int, MutableList<Long>>()

    for (i in 0 until len) {
      val job = jobs[i]
      val time = job[rIdx]
      var arr = req[time]
      val e = encode(job[tIdx], time, i)

      if (arr == null) {
        arr = mutableListOf(e)
      } else arr.add(e)

      req[time] = arr

    }

    var acc = 0
    var now = 0
    var end = 0

    while (req.isNotEmpty() || q.isNotEmpty()) {

      val arr = req[now]
      if (arr != null) {
        for (e in arr) q.push(e)
        req.remove(now)
      }

      if (end <= now && q.isNotEmpty()) {
        val lsi = q.pop()
        val l = (lsi / SEP1).toInt()
        val si = lsi % SEP1
        val s = (si / SEP2).toInt()
        end = now + l
        acc += end - s
      }

      now++
    }
    return acc / len
  }


  class HEAP(size: Int) {

    val heap = LongArray(size + 1)
    val root = 1
    var len = 0

    fun clear() {
      len = 0
    }

    fun isNotEmpty() = len > 0

    fun push(v: Long) {
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

    fun pop(): Long {
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
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (13.63ms, 67.9MB)
 * 테스트 2 〉	통과 (14.99ms, 68.2MB)
 * 테스트 3 〉	통과 (16.85ms, 66.6MB)
 * 테스트 4 〉	통과 (15.90ms, 65.7MB)
 * 테스트 5 〉	통과 (13.74ms, 67.6MB)
 * 테스트 6 〉	통과 (5.96ms, 64MB)
 * 테스트 7 〉	통과 (15.78ms, 66.8MB)
 * 테스트 8 〉	통과 (11.01ms, 66.9MB)
 * 테스트 9 〉	통과 (6.89ms, 64.3MB)
 * 테스트 10 〉	통과 (16.30ms, 68.6MB)
 * 테스트 11 〉	통과 (3.71ms, 65.2MB)
 * 테스트 12 〉	통과 (3.71ms, 65.2MB)
 * 테스트 13 〉	통과 (3.53ms, 65.7MB)
 * 테스트 14 〉	통과 (3.63ms, 64.1MB)
 * 테스트 15 〉	통과 (3.56ms, 65.6MB)
 * 테스트 16 〉	통과 (3.69ms, 64.1MB)
 * 테스트 17 〉	통과 (4.34ms, 63.9MB)
 * 테스트 18 〉	통과 (4.72ms, 63MB)
 * 테스트 19 〉	통과 (3.62ms, 64.5MB)
 * 테스트 20 〉	통과 (3.46ms, 62.6MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * import java.util.*
 *
 * class Solution {
 *     fun solution(jobs: Array<IntArray>): Int {
 *         var jobList = jobs.map { it[0] to it[1]}.sortedBy { it.first }
 *         var sortedTime: PriorityQueue<Pair<Int, Int>> = PriorityQueue(compareBy { it.second })
 *         var current = 0
 *         var sum = 0
 *         while (!jobList.isEmpty() || !sortedTime.isEmpty()) {
 *             val c = jobList.takeWhile { it.first <= current }
 *             sortedTime.addAll(c)
 *             jobList = jobList.drop(c.size)
 *             if (sortedTime.isEmpty()) {
 *                 current = jobList.first().first
 *             } else {
 *                 val j = sortedTime.poll()
 *                 current += j.second
 *                 sum += current - j.first
 *             }
 *         }
 *
 *         return sum / jobs.size
 *     }
 * }
 * 테스트 1 〉	통과 (15.36ms, 69.1MB)
 * 테스트 2 〉	통과 (14.95ms, 68.7MB)
 * 테스트 3 〉	통과 (15.53ms, 68MB)
 * 테스트 4 〉	통과 (15.22ms, 68.3MB)
 * 테스트 5 〉	통과 (16.29ms, 67.7MB)
 * 테스트 6 〉	통과 (12.79ms, 66.6MB)
 * 테스트 7 〉	통과 (17.33ms, 67.6MB)
 * 테스트 8 〉	통과 (14.53ms, 68.3MB)
 * 테스트 9 〉	통과 (13.09ms, 67MB)
 * 테스트 10 〉	통과 (15.10ms, 69.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(arrayOf(
      intArrayOf(10, 1),
      intArrayOf(1, 1),
      intArrayOf(1, 1),
      intArrayOf(3, 1),
      intArrayOf(3, 1),
      intArrayOf(5, 1),
    )), 1)

  validate(
    s.solution(arrayOf(
      intArrayOf(0, 3),
      intArrayOf(1, 9),
      intArrayOf(3, 5),
    )), 8)

  validate(
    s.solution(arrayOf(
      intArrayOf(0, 10),
      intArrayOf(0, 10),
      intArrayOf(1, 1),
    )), 13)


}

//println("[${now}] arr = ${arr}, ${req.size}")
//println("[$lsi] end $end / acc = $end")
