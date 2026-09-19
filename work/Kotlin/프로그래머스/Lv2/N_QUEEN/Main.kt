package 프로그래머스.Lv2.N_QUEEN

import util.validate

class Solution {

  fun solution(n: Int): Int {
    var ans = 0
    val half = n / 2
    var colMask = 0
    var dlMask = 0
    var dRMask = 0

    fun dfs(r: Int, c: Int) {
      val cm = 1 shl c
      val dlm = 1 shl (n - 1 - r + c)
      val drm = 1 shl (r + c)
      if (colMask and cm == cm
        || dlMask and dlm == dlm
        || dRMask and drm == drm
      ) return

      if (r == n - 1) {
        ans++
        return
      }

      colMask = colMask or cm
      dlMask = dlMask or dlm
      dRMask = dRMask or drm

      for (lc in 0..c - 2) dfs(r + 1, lc)
      for (rc in c + 2..n - 1) dfs(r + 1, rc)

      colMask = colMask xor cm
      dlMask = dlMask xor dlm
      dRMask = dRMask xor drm
    }

    for (c in 0 until half) dfs(0, c)
    ans *= 2

    if (n % 2 == 1) dfs(0, half)

    return ans
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (0.23ms, 58.1MB)
 * 테스트 2 〉	통과 (0.19ms, 59.8MB)
 * 테스트 3 〉	통과 (0.21ms, 59.7MB)
 * 테스트 4 〉	통과 (0.18ms, 59.5MB)
 * 테스트 5 〉	통과 (0.19ms, 57.7MB)
 * 테스트 6 〉	통과 (0.28ms, 58.1MB)
 * 테스트 7 〉	통과 (0.42ms, 60MB)
 * 테스트 8 〉	통과 (0.89ms, 62.9MB)
 * 테스트 9 〉	통과 (1.63ms, 62.4MB)
 * 테스트 10 〉	통과 (7.51ms, 59.8MB)
 * 테스트 11 〉	통과 (30.44ms, 60.8MB)
 * v2:
 * 테스트 1 〉	통과 (0.18ms, 60.2MB)
 * 테스트 2 〉	통과 (0.28ms, 59.9MB)
 * 테스트 3 〉	통과 (0.18ms, 60.2MB)
 * 테스트 4 〉	통과 (0.22ms, 59.7MB)
 * 테스트 5 〉	통과 (0.17ms, 59.8MB)
 * 테스트 6 〉	통과 (0.36ms, 59.3MB)
 * 테스트 7 〉	통과 (0.55ms, 59.5MB)
 * 테스트 8 〉	통과 (0.86ms, 61.4MB)
 * 테스트 9 〉	통과 (1.73ms, 61.3MB)
 * 테스트 10 〉	통과 (7.51ms, 60.9MB)
 * 테스트 11 〉	통과 (28.68ms, 60.8MB)
 *
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(n: Int): Int {
 *         return getNQueen(n, 0, Chess(n))
 *     }
 *
 *     fun getNQueen(n: Int, row: Int, chess: Chess) : Int{
 *         if(row == n) return 1
 *         else {
 *             var count = 0
 *             for(i in 0 until n) {
 *                 if(chess.isQueenAvailable(row, i)) {
 *                     count += getNQueen(n, row + 1, chess.copy().apply {
 *                         queens.add(Queen(row, i))
 *                     })
 *                 }
 *             }
 *             return count
 *         }
 *     }
 *
 *     class Chess(val n: Int, var queens: ArrayList<Queen> = ArrayList<Queen>()) {
 *
 *         fun isQueenAvailable(row: Int, column: Int): Boolean {
 *             for(queen in queens) {
 *                 if(queen.row == row) return false
 *                 if(queen.column == column) return false
 *                 if(queen.row - row == queen.column - column) return false
 *                 if(queen.row - row == column - queen.column) return false
 *             }
 *             return true
 *         }
 *
 *         fun copy() = Chess(n, ArrayList(queens))
 *     }
 *
 *     class Queen(val row: Int, val column: Int)
 * }
 * 테스트 1 〉	통과 (0.50ms, 60.2MB)
 * 테스트 2 〉	통과 (0.48ms, 59.3MB)
 * 테스트 3 〉	통과 (1.20ms, 58MB)
 * 테스트 4 〉	통과 (0.72ms, 58.4MB)
 * 테스트 5 〉	통과 (1.42ms, 60.1MB)
 * 테스트 6 〉	통과 (2.42ms, 60.1MB)
 * 테스트 7 〉	통과 (2.76ms, 59.4MB)
 * 테스트 8 〉	통과 (10.37ms, 66MB)
 * 테스트 9 〉	통과 (13.50ms, 73.1MB)
 * 테스트 10 〉	통과 (42.30ms, 88.1MB)
 * 테스트 11 〉	통과 (179.38ms, 149MB)
 *
 * [RIVAL 2]
 * import kotlin.math.abs
 * class Solution {
 *     var col:IntArray = IntArray(12,{i->0})
 *     fun solution(n: Int): Int {
 *         var answer = 0
 *         answer = solve(0, n)
 *         return answer
 *     }
 *
 *     private fun solve(idx: Int, n: Int): Int {
 *         var flag:Boolean = true
 *         if(idx >= n) return 1
 *         var ret:Int = 0
 *
 *         for(i in 0 until n){
 *             flag = true
 *             for(j in 0 until idx){
 *                 if(col[j] == i || abs(idx-j) == abs(i-col[j])){
 *                     flag = false
 *                     break
 *                 }
 *             }
 *             if(flag){
 *                 col[idx] = i
 *                 ret += solve(idx + 1, n)
 *             }
 *         }
 *
 *         return ret
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 58.9MB)
 * 테스트 2 〉	통과 (0.02ms, 60.1MB)
 * 테스트 3 〉	통과 (0.02ms, 60.9MB)
 * 테스트 4 〉	통과 (0.09ms, 59.7MB)
 * 테스트 5 〉	통과 (0.20ms, 60.1MB)
 * 테스트 6 〉	통과 (0.44ms, 59.3MB)
 * 테스트 7 〉	통과 (0.68ms, 58.4MB)
 * 테스트 8 〉	통과 (2.06ms, 60.6MB)
 * 테스트 9 〉	통과 (7.19ms, 60.9MB)
 * 테스트 10 〉	통과 (21.66ms, 61.2MB)
 * 테스트 11 〉	통과 (99.63ms, 59.6MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1), 1)
  validate(s.solution(2), 0)
  validate(s.solution(3), 0)
  validate(s.solution(4), 2)
}
