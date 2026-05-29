package 프로그래머스.알고리즘고득점Kit.완전탐색.소수찾기

import util.validate

class Solution {

  val ZERO = 48
  fun solution(numbers: String): Int {
    var ans = 0
    val len = numbers.length
    var limit = 1
    repeat(len) { limit *= 10 }

    val used = BooleanArray(len)
    val NUM = IntArray(len)
    val ch = BooleanArray(limit)
    fun getN(last: Int, dep: Int): Int {
      var x = last
      repeat(dep) {
        x = x * 10 + NUM[dep - (it + 1)]
      }
      return x
    }

    fun dfs(dep: Int) {
      if (dep >= len) return

      for (i in 0 until len) {
        if (used[i]) continue
        val digit = numbers[i].code - ZERO
        if (dep == 0 && digit == 0) continue
        used[i] = true
        NUM[dep] = digit
        var n = getN(digit, dep)
        if (!ch[n] && isPrime(n)) ans++
        ch[n] = true
        dfs(dep + 1)
        used[i] = false
      }
    }

    dfs(0)
    return ans
  }

  fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n <= 3) return true
    if (n % 2 == 0 || n % 3 == 0) return false

    var d = 5
    while (d * d <= n) {
      if (n % d == 0 || n % (d + 2) == 0) return false
      d += 6
    }
    return true
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.17ms, 60.5MB)
 * 테스트 2 〉	통과 (0.88ms, 64.6MB)
 * 테스트 3 〉	통과 (0.11ms, 63.6MB)
 * 테스트 4 〉	통과 (0.83ms, 64.1MB)
 * 테스트 5 〉	통과 (4.47ms, 72.5MB)
 * 테스트 6 〉	통과 (0.12ms, 64.4MB)
 * 테스트 7 〉	통과 (0.14ms, 65.2MB)
 * 테스트 8 〉	통과 (4.75ms, 73.7MB)
 * 테스트 9 〉	통과 (0.19ms, 62MB)
 * 테스트 10 〉	통과 (0.90ms, 63.4MB)
 * 테스트 11 〉	통과 (0.23ms, 65.9MB)
 * 테스트 12 〉	통과 (0.20ms, 63.2MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *       lateinit var combNumbers : MutableList<Int>
 *
 *     fun solution(numbers: String): Int {
 *         var answer = 0
 *         combNumbers = mutableListOf()
 *
 *         temp(numbers,"")
 *
 *         combNumbers.distinct().forEach {
 *
 *             if(isPrime(it)){
 *                 answer++
 *             }
 *         }
 *         return answer
 *     }
 *
 *     fun temp(numbers:String,result:String){
 *         if(!result.isNullOrEmpty()){
 *             combNumbers.add(result.toInt())
 *         }
 *
 *         if(numbers.isEmpty()){
 *             return
 *         }
 *         numbers.forEachIndexed { index, c ->
 *             temp((numbers.removeRange(index..index)),result+c)
 *         }
 *
 *
 *     }
 *     fun isPrime(num:Int):Boolean{
 *
 *         if(num == 1 || num == 0 ){return false}
 *         for(i in 2..num/2){
 *             if(num%i == 0){
 *                 return false
 *             }
 *         }
 *         return true
 *     }
 * }
 * 테스트 1 〉	통과 (18.81ms, 66.3MB)
 * 테스트 2 〉	통과 (28.86ms, 66.2MB)
 * 테스트 3 〉	통과 (17.14ms, 66.3MB)
 * 테스트 4 〉	통과 (25.41ms, 67.7MB)
 * 테스트 5 〉	통과 (26.55ms, 73.4MB)
 * 테스트 6 〉	통과 (15.80ms, 66.5MB)
 * 테스트 7 〉	통과 (15.84ms, 67MB)
 * 테스트 8 〉	통과 (29.45ms, 74.8MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("17"), 3)
  validate(s.solution("011"), 2)
}

//        println("[$dep] i = ${i}, ${used[i]}")
