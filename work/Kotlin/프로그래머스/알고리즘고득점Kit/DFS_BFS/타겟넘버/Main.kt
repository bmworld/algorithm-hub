package 프로그래머스.알고리즘고득점Kit.DFS_BFS.타겟넘버

import util.validate

class Solution {

  fun solution(a: IntArray, target: Int): Int {
    var ans = 0
    var sum = 0
    for (x in a) sum += x

    fun dfs(i: Int, sum: Int) {
      if (sum == target) ans++
      if (sum < target) return
      for (j in i until a.size) dfs(j + 1, sum - a[j] * 2)
    }

    dfs(0, sum)
    return ans
  }
}

/**
 * ```
 * ME: v1
 * 테스트 1 〉	통과 (2.77ms, 62.5MB)
 * 테스트 2 〉	통과 (2.10ms, 62.3MB)
 * 테스트 3 〉	통과 (0.13ms, 63.7MB)
 * 테스트 4 〉	통과 (0.15ms, 64.5MB)
 * 테스트 5 〉	통과 (0.37ms, 63.5MB)
 * 테스트 6 〉	통과 (0.15ms, 64MB)
 * 테스트 7 〉	통과 (0.15ms, 65.4MB)
 * 테스트 8 〉	통과 (0.36ms, 62.6MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(numbers: IntArray, target: Int): Int {
 *         var answer = 0
 *         fun dfs(sum: Int,idx: Int){
 *             if(idx<numbers.size-1){
 *                 dfs(sum+numbers[idx],idx+1)
 *                 dfs(sum-numbers[idx],idx+1)
 *             }
 *             else{
 *                 if(sum+numbers[idx] == target) {answer++}
 *                 if(sum-numbers[idx] == target) {answer++}
 *             }
 *         }
 *         dfs(0,0)
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (3.16ms, 63.2MB)
 * 테스트 2 〉	통과 (3.13ms, 64.3MB)
 * 테스트 3 〉	통과 (0.20ms, 64.2MB)
 * 테스트 4 〉	통과 (0.28ms, 63.8MB)
 * 테스트 5 〉	통과 (0.41ms, 63.2MB)
 * 테스트 6 〉	통과 (0.26ms, 65.4MB)
 * 테스트 7 〉	실행 중단
 * 테스트 8 〉	통과 (0.35ms, 63.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 1, 1, 1, 1), 3), 5)
  validate(s.solution(intArrayOf(4, 1, 2, 1), 4), 2)
}

//        println("[$i] -> $j, next = $nxt")
