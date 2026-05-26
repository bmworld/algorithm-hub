package 프로그래머스.알고리즘고득점Kit.Heap.이중우선순위큐

import util.validate

import java.util.*

class Solution {

  val ZERO = 48
  val MINUS = 45
  val INSERT = 'I'
  val DELETE_MAX = 1

  fun solution(ops: Array<String>): IntArray {
    val a = TreeMap<Int, Int>()

    for (str in ops) {
      val op = str[0]
      val n = getN(str)

      when (op) {
        INSERT -> a[n] = (a[n] ?: 0) + 1
        else -> if (a.isNotEmpty()) when (n) {
          DELETE_MAX -> removeLast(a)
          else -> removeFirst(a)
        }
      }
    }

    val ans = IntArray(2)
    if (a.isNotEmpty()) {
      ans[0] = a.lastKey()
      ans[1] = a.firstKey()
    }

    return ans
  }

  fun removeFirst(a: TreeMap<Int, Int>) {
    val e = a.firstEntry()
    val cnt = e.value
    if (cnt == 1) a.pollFirstEntry()
    else a[e.key] = cnt - 1
  }

  fun removeLast(a: TreeMap<Int, Int>) {
    val e = a.lastEntry()
    val cnt = e.value
    if (cnt == 1) a.pollLastEntry()
    else a[e.key] = cnt - 1
  }

  fun getN(str: String): Int {
    var s = 1
    var v = 0
    for (i in 2 until str.length) {
      val c = str[i].code
      if (c == MINUS) s = -1
      else v = v * 10 + c - ZERO
    }
    return s * v
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.19ms, 63.6MB)
 * 테스트 2 〉	통과 (0.22ms, 63.5MB)
 * 테스트 3 〉	통과 (0.24ms, 63.7MB)
 * 테스트 4 〉	통과 (0.14ms, 61.8MB)
 * 테스트 5 〉	통과 (0.20ms, 63.9MB)
 * 테스트 6 〉	통과 (0.26ms, 64MB)
 * 테스트 7 〉	통과 (17.73ms, 103MB)
 * 테스트 8 〉	통과 (0.20ms, 64.6MB)
 * 테스트 9 〉	통과 (0.19ms, 64.8MB)
 * 테스트 10 〉	통과 (0.19ms, 64.2MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * import java.util.*
 * class Solution {
 *     fun solution(operations: Array<String>): IntArray {
 *
 *         var minHeap = PriorityQueue<Int>(compareBy { it })
 *         var maxHeap = PriorityQueue<Int>(compareByDescending { it })
 *
 *         for ((index, value) in operations.withIndex()) {
 *
 *             var curCmd = value
 *
 *             var trans = curCmd.split(" ")
 *
 *             if (trans[0] == "I") {
 *                 //값 삽입
 *                 minHeap.add(trans[1].toInt())
 *                 maxHeap.add(trans[1].toInt())
 *             } else {
 *                 when {
 *                     trans[1].equals("1") -> {
 *                         //최댓값 삭제
 *                         if (!maxHeap.isEmpty()) {
 *
 *                             minHeap.remove(maxHeap.peek())
 *
 *                             maxHeap.poll()
 *
 *
 *                         }
 *                     }
 *                     trans[1].equals("-1") -> {
 *                         //최솟값 삭제
 *                         if (!minHeap.isEmpty()) {
 *                             maxHeap.remove(minHeap.peek())
 *
 *                             minHeap.poll()
 *                         }
 *                     }
 *                 }
 *             }
 *
 *
 *         }
 *
 *         if (minHeap.isEmpty() || maxHeap.isEmpty())
 *             return intArrayOf(0, 0)
 *
 *
 *         return intArrayOf(maxHeap.poll(), minHeap.poll())
 *     }
 * }
 * 테스트 1 〉	통과 (5.95ms, 63.9MB)
 * 테스트 2 〉	통과 (5.57ms, 63.7MB)
 * 테스트 3 〉	통과 (7.70ms, 63.8MB)
 * 테스트 4 〉	통과 (6.01ms, 63.5MB)
 * 테스트 5 〉	통과 (8.04ms, 64.4MB)
 * 테스트 6 〉	통과 (6.69ms, 65MB)
 * 테스트 7 〉	통과 (51.79ms, 108MB)
 * 테스트 8 〉	통과 (5.79ms, 65.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1")),
    intArrayOf(0, 0)
  )
  validate(
    s.solution(
      arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333")),
    intArrayOf(333, -45)
  )

  validate(
    s.solution(
      arrayOf("I 10", "I 10", "I -1")),
    intArrayOf(10, -1)
  )
}
