package 프로그래머스.Lv1.소수만들기

import util.validate

class Solution {

  fun solution(nums: IntArray): Int {
    var ans = 0
    for (i in 0 until nums.size)
      for (j in i + 1 until nums.size)
        for (k in j + 1 until nums.size) if (isPrime(nums[i] + nums[j] + nums[k])) ans++

    return ans
  }

  fun isPrime(x: Int): Boolean {
    if (x == 2 || x == 3) return true
    if (x % 2 == 0 || x % 3 == 0) return false

    var d = 5
    while (d <= x / d) {
      if (x % d == 0 || x % (d + 2) == 0) return false
      d += 6
    }

    return true
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.20ms, 58.1MB)
 * 테스트 2 〉	통과 (0.25ms, 58.8MB)
 * 테스트 3 〉	통과 (0.07ms, 59.8MB)
 * 테스트 4 〉	통과 (0.07ms, 58.1MB)
 * 테스트 5 〉	통과 (0.24ms, 59.1MB)
 * 테스트 6 〉	통과 (0.32ms, 58.5MB)
 * 테스트 7 〉	통과 (0.02ms, 58.1MB)
 * 테스트 8 〉	통과 (0.62ms, 59.1MB)
 * 테스트 9 〉	통과 (0.13ms, 59.9MB)
 * 테스트 10 〉	통과 (0.76ms, 59.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun isPrime(n: Int): Boolean {
 *         if(n<2)return false
 *         else if(n<6){
 *             if(n==4)
 *             return false
 *             else return true}
 *         for (i in 2..Math.sqrt(n.toDouble()).toInt()){
 *             if(n%i==0)return false
 *         }
 *         return true
 *     }
 *     fun solution(nums: IntArray): Int {
 *         var answer = 0
 *         for (i in 0..nums.size-3){
 *             for(j in i+1..nums.size-2){
 *                 for(k in j+1..nums.size-1){
 *                     println(nums[i]+nums[j]+nums[k])
 *                     if(isPrime(nums[i]+nums[j]+nums[k]))answer++
 *                 }
 *             }
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (9.21ms, 58.9MB)
 * 테스트 2 〉	통과 (9.93ms, 59.1MB)
 * 테스트 3 〉	통과 (4.94ms, 58.9MB)
 * 테스트 4 〉	통과 (4.46ms, 59.3MB)
 * 테스트 5 〉	통과 (13.93ms, 59.7MB)
 * 테스트 6 〉	통과 (11.62ms, 60MB)
 * 테스트 7 〉	통과 (1.75ms, 59.5MB)
 * 테스트 8 〉	통과 (21.70ms, 75.5MB)
 * 테스트 9 〉	통과 (5.17ms, 59.3MB)
 * 테스트 10 〉	통과 (22.54ms, 75.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 3, 4)), 1)
  validate(s.solution(intArrayOf(1, 1, 0)), 1)
  validate(s.solution(intArrayOf(1, 1, 1)), 1)
  validate(s.solution(intArrayOf(1, 1, 3)), 1)
  validate(s.solution(intArrayOf(1, 2, 7, 6, 4)), 4)

}
//          println("x = $x ->$prime")
