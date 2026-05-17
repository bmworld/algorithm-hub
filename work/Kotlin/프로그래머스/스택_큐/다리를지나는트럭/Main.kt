package 프로그래머스.스택_큐.다리를지나는트럭

import util.validate

class Solution {

  fun solution(L: Int, W: Int, a: IntArray): Int {
    val size = a.size
    val inBridge = IntArray(L)

    var ans = L
    var w = 0
    var i = 0
    var l = 0
    var r = l
    var truck = a[i]
    var pos = 0

    while (true) {
      if (r++ - l == L) {
        l++
        w -= inBridge[pos]
        inBridge[pos] = 0
      }


      if (w + truck <= W) {
        w += truck.also { inBridge[pos] = it }
        if (++i < size) truck = a[i]
        else {
          ans = r + L
          break
        }
      }
      pos = nxt(pos, L)
    }
    return ans
  }

  fun nxt(i: Int, L: Int): Int = if (i + 1 < L) i + 1 else 0
}

//println("[-] l=${l}, ${inBridge[bridgePos]}")
//println("[$l, $r] => w=$w, cur=$i")
//println("[+] r=$r, truck = $truck")

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.20ms, 63.9MB)
 * 테스트 2 〉	통과 (1.41ms, 62MB)
 * 테스트 3 〉	통과 (0.11ms, 63.8MB)
 * 테스트 4 〉	통과 (1.24ms, 62.7MB)
 * 테스트 5 〉	통과 (4.95ms, 62.2MB)
 * 테스트 6 〉	통과 (3.81ms, 61.8MB)
 * 테스트 7 〉	통과 (0.27ms, 62.4MB)
 * 테스트 8 〉	통과 (0.14ms, 63.3MB)
 * 테스트 9 〉	통과 (0.77ms, 63.6MB)
 * 테스트 10 〉	통과 (0.13ms, 65.1MB)
 * 테스트 11 〉	통과 (0.17ms, 63.3MB)
 * 테스트 12 〉	통과 (0.18ms, 61.6MB)
 * 테스트 13 〉	통과 (0.37ms, 62.5MB)
 * 테스트 14 〉	통과 (0.10ms, 62.4MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * import java.util.*
 *
 * class Solution {
 *     fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
 *         var answer = 0
 *         val bridgeQueue: Queue<Int> = LinkedList(List(bridge_length){0})
 *         val waitingQueue: Queue<Int> = LinkedList(truck_weights.toList())
 *
 *         while (bridgeQueue.isNotEmpty()) {
 *             answer++
 *             bridgeQueue.poll()
 *             if (waitingQueue.isNotEmpty()) {
 *                 if (bridgeQueue.sum() + waitingQueue.peek() <= weight) {
 *                     bridgeQueue.add(waitingQueue.poll())
 *                 } else {
 *                     bridgeQueue.add(0)
 *                 }
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (30.35ms, 66.1MB)
 * 테스트 2 〉	통과 (366.57ms, 67.7MB)
 * 테스트 3 〉	통과 (13.77ms, 66.2MB)
 * 테스트 4 〉	통과 (103.13ms, 67.4MB)
 * 테스트 5 〉	통과 (2203.18ms, 73.9MB)
 * 테스트 6 〉	통과 (407.09ms, 68.6MB)
 * 테스트 7 〉	통과 (24.18ms, 66.6MB)
 * 테스트 8 〉	통과 (18.04ms, 65.7MB)
 * 테스트 9 〉	통과 (19.76ms, 67.4MB)
 * 테스트 10 〉	통과 (14.99ms, 66.4MB)
 * 테스트 11 〉	통과 (13.54ms, 66.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(2, 10, intArrayOf(1, 10, 1, 10)), 9)
  validate(s.solution(1, 1, intArrayOf(1, 1, 1, 1)), 5)
  validate(s.solution(2, 10, intArrayOf(1)), 3)
  validate(s.solution(2, 10, intArrayOf(7, 4, 5, 6)), 8)
  validate(s.solution(100, 100, intArrayOf(10)), 101)
  validate(s.solution(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)), 110)
  validate(s.solution(4, 3, intArrayOf(2, 1, 1, 1)), 10)
  validate(s.solution(2, 3, intArrayOf(1, 2, 1, 1)), 6)
  validate(s.solution(3, 6, intArrayOf(1, 2, 3, 1, 1)), 8)
  validate(s.solution(3, 4, intArrayOf(2, 2, 2, 2, 2, 2)), 11)
  validate(s.solution(4, 5, intArrayOf(1, 4, 1, 1, 1)), 11)
  validate(s.solution(5, 5, intArrayOf(5, 1, 1, 1, 1, 1)), 15)

  validate(s.solution(2, 2, intArrayOf(1, 1, 2, 1)).also { println("ans=$it") }, 8)
  validate(s.solution(2, 10, intArrayOf(1, 2, 3, 4, 5, 10)).also { println("ans=$it") }, 9)

}

//println("[OUT] l=${l}, ${inBridge[pos]}")
//println("[$l, $r] => w=$w, cur=$i")
//println("[IN] r=$r, truck = $truck")
