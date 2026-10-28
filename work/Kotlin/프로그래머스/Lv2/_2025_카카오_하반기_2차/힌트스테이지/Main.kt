package 프로그래머스.Lv2._2025_카카오_하반기_2차.힌트스테이지

import util.validate

class Solution {

  fun solution(cost: Array<IntArray>, hintBundle: Array<IntArray>): Int {
    var ans = Int.MAX_VALUE

    val n = cost.size
    val last = n - 1
    val k = hintBundle[0].size - 1

    fun dfs(stage: Int, acc: Int, hints: IntArray) {
      if (acc >= ans) return
      val cur = cost[stage][minOf(hints[stage], last)]

      if (stage == last) {
        if (acc + cur < ans) ans = acc + cur
        return
      }

      val nxt = acc + cur
      dfs(stage + 1, nxt, hints.copyOf())

      val bundle = hintBundle[stage]
      for (j in 1..k) hints[bundle[j] - 1]++
      dfs(stage + 1, nxt + bundle[0], hints.copyOf())
    }

    dfs(0, 0, IntArray(n))

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.18ms, 58.6MB)
 * 테스트 2 〉	통과 (0.21ms, 59.6MB)
 * 테스트 3 〉	통과 (0.53ms, 59.4MB)
 * 테스트 4 〉	통과 (1.04ms, 63.2MB)
 * 테스트 5 〉	통과 (4.43ms, 68.2MB)
 * 테스트 6 〉	통과 (0.34ms, 60.1MB)
 * 테스트 7 〉	통과 (0.88ms, 64.4MB)
 * 테스트 8 〉	통과 (1.21ms, 63.9MB)
 * 테스트 9 〉	통과 (4.66ms, 69.2MB)
 * 테스트 10 〉	통과 (8.81ms, 69MB)
 *
 * v2:
 * 테스트 1 〉	통과 (0.22ms, 59.3MB)
 * 테스트 2 〉	통과 (0.28ms, 59.2MB)
 * 테스트 3 〉	통과 (0.47ms, 60.9MB)
 * 테스트 4 〉	통과 (1.03ms, 60.8MB)
 * 테스트 5 〉	통과 (3.98ms, 63.9MB)
 * 테스트 6 〉	통과 (0.24ms, 60.4MB)
 * 테스트 7 〉	통과 (0.64ms, 59.4MB)
 * 테스트 8 〉	통과 (0.56ms, 60MB)
 * 테스트 9 〉	통과 (1.67ms, 65.3MB)
 * 테스트 10 〉	통과 (1.33ms, 63.9MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     private lateinit var cost: Array<IntArray>
 *     private lateinit var hint: Array<IntArray>
 *     private var answer = 0
 *
 *     fun solution(cost: Array<IntArray>, hint: Array<IntArray>): Int {
 *         this.cost = cost
 *         this.hint = hint
 *         answer = cost.sumOf { it[0] }
 *         calc(cost.size - 1, 0, IntArray(cost.size))
 *         return answer
 *     }
 *
 *     private fun calc(depth: Int, price: Int, count: IntArray) {
 *         if (depth == 0) {
 *             var totalPrice = price
 *             count.forEachIndexed { i, c -> totalPrice += cost[i][c.coerceAtMost(count.size - 1)] }
 *             if (totalPrice < answer) answer = totalPrice
 *             return
 *         }
 *         val newCount = count.copyOf()
 *         hint[depth - 1].drop(1).forEach { newCount[it - 1]++ }
 *         calc(depth - 1, price + hint[depth - 1][0], newCount)
 *         calc(depth - 1, price, count)
 *     }
 * }
 * 테스트 1 〉	통과 (15.66ms, 63.5MB)
 * 테스트 2 〉	통과 (12.53ms, 63.4MB)
 * 테스트 3 〉	통과 (12.96ms, 63.7MB)
 * 테스트 4 〉	통과 (19.47ms, 67.6MB)
 * 테스트 5 〉	통과 (17.81ms, 71.7MB)
 * 테스트 6 〉	통과 (12.45ms, 63.7MB)
 * 테스트 7 〉	통과 (11.43ms, 67.2MB)
 * 테스트 8 〉	통과 (12.29ms, 67.6MB)
 * 테스트 9 〉	통과 (20.99ms, 75.3MB)
 * 테스트 10 〉	통과 (24.77ms, 77.7MB)
 *
 * [RIVAL 2]
 * import kotlin.math.min
 *
 * class Solution {
 *     fun solution(cost: Array<IntArray>, hint: Array<IntArray>): Int {
 *         var min: Int = cost.sumOf { it[0] }
 *         val hintArr = IntArray(cost.size)
 *         fun f(idx: Int, sum: Int){
 *             if(sum >= min)return
 *             if(idx == hint.size){
 *                 min = min(min, sum + cost[idx][min(hintArr[idx], hint.size)])
 *                 return
 *             }
 *             f(idx+1, sum + cost[idx][min(hintArr[idx], hint.size)] )
 *             for(j in 1 until hint[0].size){
 *                 hintArr[hint[idx][j]-1]++
 *             }
 *             f(idx+1, sum + hint[idx][0] + cost[idx][min(hintArr[idx], hint.size)] )
 *             for(j in 1 until hint[0].size){
 *                 hintArr[hint[idx][j]-1]--
 *             }
 *         }
 *         f(0, 0)
 *         return min
 *     }
 * }
 * 테스트 1 〉	통과 (0.23ms, 58.4MB)
 * 테스트 2 〉	통과 (0.35ms, 60.2MB)
 * 테스트 3 〉	통과 (0.36ms, 58.6MB)
 * 테스트 4 〉	통과 (0.65ms, 61.7MB)
 * 테스트 5 〉	통과 (1.04ms, 62.4MB)
 * 테스트 6 〉	통과 (0.32ms, 59.8MB)
 * 테스트 7 〉	통과 (0.72ms, 62.5MB)
 * 테스트 8 〉	통과 (0.83ms, 63.6MB)
 * 테스트 9 〉	통과 (1.48ms, 61.7MB)
 * 테스트 10 〉	통과 (2.54ms, 61.7MB)
 *
 * [RIVAL 3]
 * ```
 */
fun main() {
  val s = Solution()

  // CASE 1
  validate(s.solution(
    arrayOf(
      intArrayOf(160, 140, 120, 110, 60),
      intArrayOf(290, 270, 260, 120, 10),
      intArrayOf(160, 130, 120, 60, 20),
      intArrayOf(160, 120, 80, 70, 20),
      intArrayOf(110, 70, 60, 30, 20),
    ),
    arrayOf(
      intArrayOf(40, 2, 3),
      intArrayOf(40, 5, 3),
      intArrayOf(20, 5, 4),
      intArrayOf(50, 5, 5),
    ),
  ), 810)

// CASE 2
  validate(s.solution(
    arrayOf(
      intArrayOf(110, 100, 90, 80, 70, 50, 10),
      intArrayOf(170, 160, 150, 140, 130, 110, 30),
      intArrayOf(260, 250, 190, 180, 170, 130, 100),
      intArrayOf(170, 150, 120, 90, 60, 40, 30),
      intArrayOf(220, 140, 110, 100, 70, 60, 50),
      intArrayOf(290, 180, 150, 130, 100, 20, 10),
      intArrayOf(110, 100, 90, 70, 50, 40, 30),
    ),
    arrayOf(
      intArrayOf(40, 3, 4, 3),
      intArrayOf(80, 3, 7, 4),
      intArrayOf(40, 7, 6, 5),
      intArrayOf(40, 7, 5, 5),
      intArrayOf(50, 6, 7, 6),
      intArrayOf(30, 7, 7, 7),
    ),
  ), 1080)

// CASE 3
  validate(s.solution(
    arrayOf(
      intArrayOf(290, 270, 170, 100, 60, 20),
      intArrayOf(210, 190, 180, 170, 120, 80),
      intArrayOf(200, 180, 170, 80, 40, 20),
      intArrayOf(100, 90, 70, 60, 40, 10),
      intArrayOf(230, 200, 170, 150, 90, 80),
      intArrayOf(150, 100, 80, 60, 40, 10),
    ),
    arrayOf(
      intArrayOf(230, 2, 2, 2, 2),
      intArrayOf(330, 5, 3, 4, 3),
      intArrayOf(180, 5, 4, 4, 6),
      intArrayOf(280, 5, 5, 6, 6),
      intArrayOf(260, 6, 6, 6, 6),
    ),
  ), 1180)

// CASE 4
  validate(s.solution(
    arrayOf(
      intArrayOf(49250, 42271, 40724, 36310, 32560, 30670, 24100, 10378),
      intArrayOf(58510, 56101, 54078, 32864, 31443, 19451, 18098, 7187),
      intArrayOf(68812, 66112, 65024, 60529, 53992, 39865, 31325, 17700),
      intArrayOf(13768, 12866, 11379, 10425, 6853, 6176, 5655, 2556),
      intArrayOf(51748, 48647, 41478, 39756, 25302, 18081, 16504, 811),
      intArrayOf(52690, 34113, 32370, 29555, 19343, 11763, 7566, 5962),
      intArrayOf(9306, 9190, 8196, 7573, 6275, 4723, 1316, 212),
      intArrayOf(40713, 40158, 31449, 22349, 20956, 20377, 19489, 14450),
    ),
    arrayOf(
      intArrayOf(0, 2, 4, 6, 2),
      intArrayOf(0, 6, 3, 3, 4),
      intArrayOf(0, 6, 4, 5, 5),
      intArrayOf(0, 7, 5, 8, 7),
      intArrayOf(0, 7, 7, 7, 7),
      intArrayOf(0, 7, 7, 7, 8),
      intArrayOf(0, 8, 8, 8, 8),
    ),
  ), 267789)

// CASE 5
  validate(s.solution(
    arrayOf(
      intArrayOf(330, 300, 277, 162, 126),
      intArrayOf(640, 392, 358, 345, 231),
      intArrayOf(960, 872, 678, 392, 113),
      intArrayOf(210, 195, 176, 42, 37),
      intArrayOf(980, 964, 623, 327, 154),
    ),
    arrayOf(
      intArrayOf(976, 2, 2, 3, 5, 3, 3),
      intArrayOf(746, 3, 3, 3, 3, 4, 3),
      intArrayOf(319, 5, 4, 4, 4, 4, 4),
      intArrayOf(966, 5, 5, 5, 5, 5, 5),
    ),
  ), 3004)

// CASE 6
  validate(s.solution(
    arrayOf(
      intArrayOf(230, 223, 192, 93, 59, 33),
      intArrayOf(290, 240, 193, 117, 113, 42),
      intArrayOf(950, 908, 796, 598, 574, 328),
      intArrayOf(720, 692, 588, 237, 102, 61),
      intArrayOf(780, 569, 522, 450, 337, 293),
      intArrayOf(850, 837, 768, 568, 382, 124),
    ),
    arrayOf(
      intArrayOf(158, 2, 2, 3),
      intArrayOf(260, 5, 4, 3),
      intArrayOf(268, 4, 4, 4),
      intArrayOf(426, 5, 5, 6),
      intArrayOf(330, 6, 6, 6),
    ),
  ), 3426)

}
