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
    threewayQuickSort(jobs, 0, len - 1)
    val h = HEAP(len)

    var accTime = 0
    var end = jobs[0][rIdx]

    fun execute(nextEnd: Int? = null) {
      while (h.isNotEmpty()) {
        val lsi = h.pop()
        val l = (lsi / SEP1).toInt()
        val si = lsi % SEP1
        val s = (si / SEP2).toInt()
        end += l
        accTime += end - s
      }

      if (nextEnd != null) end = maxOf(end, nextEnd)
    }

    for (i in 0 until len) {
      val job = jobs[i]
      val reqTime = job[rIdx]
      val tknTime = job[tIdx]
      if (reqTime > end) execute(reqTime)
      h.push(encode(tknTime, reqTime, i))
    }
    execute()

    return accTime / len
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


  fun swap(
    a: Array<IntArray>,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun threewayQuickSort(
    a: Array<IntArray>,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1][rIdx]

    while (pos <= pr) {
      val x = a[pos][rIdx]
      when {
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }
    threewayQuickSort(a, l, pl - 1)
    threewayQuickSort(a, pr + 1, r)
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	실패 (1.09ms, 64MB)
 * 테스트 2 〉	실패 (1.44ms, 63.6MB)
 * 테스트 3 〉	실패 (0.98ms, 63.8MB)
 * 테스트 4 〉	실패 (1.13ms, 64.1MB)
 * 테스트 5 〉	실패 (0.95ms, 63.6MB)
 * 테스트 6 〉	실패 (0.39ms, 64.1MB)
 * 테스트 7 〉	실패 (1.02ms, 63.7MB)
 * 테스트 8 〉	실패 (0.81ms, 62.9MB)
 * 테스트 9 〉	실패 (0.73ms, 61.8MB)
 * 테스트 10 〉	실패 (1.54ms, 63.2MB)
 * 테스트 11 〉	통과 (0.52ms, 63.3MB)
 * 테스트 12 〉	실패 (0.34ms, 62.2MB)
 * 테스트 13 〉	통과 (1.70ms, 62.3MB)
 * 테스트 14 〉	통과 (0.52ms, 62.6MB)
 * 테스트 15 〉	실패 (0.52ms, 62.7MB)
 * 테스트 16 〉	통과 (0.38ms, 63.1MB)
 * 테스트 17 〉	통과 (0.48ms, 62.5MB)
 * 테스트 18 〉	통과 (0.35ms, 62.6MB)
 * 테스트 19 〉	통과 (0.38ms, 63.9MB)
 * 테스트 20 〉	통과 (0.28ms, 63.6MB)
 * ```
 *
 *
 * ```
 * RIVAL:
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
    )), 16)


}

//      println("[done] end($end) | acc($accTime)")
//       println("[$i] taken=$tknTime / req($reqTime) vs end ($end) | acc = $accTime")
//println("avg = $avg = $accTime / $len")
