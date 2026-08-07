package 프로그래머스.Lv2.숫자카드나누기

import util.validate

class Solution {

  fun solution(A: IntArray, B: IntArray): Int {
    var ans = 0

    // A > B
    var a = A[0]
    for (i in 1 until A.size) {
      val gcd = getGCD(a, A[i])
      if (gcd < a) {
        a = gcd
        if (gcd == 1) break
      }
    }

    var valid = true
    for (x in B) {
      if (x % a == 0) {
        valid = false
        break
      }
    }
    if (valid) ans = a

    // B > A
    var b = B[0]
    for (i in 1 until B.size) {
      val gcd = getGCD(b, B[i])
      if (gcd < b) {
        b = gcd
        if (gcd == 1) break
      }
    }

    valid = true
    for (x in A) {
      if (x % b == 0) {
        valid = false
        break
      }
    }

    if (valid && ans < b) ans = b

    return ans
  }

  private fun getGCD(a: Int, b: Int): Int = if (b == 0) a else getGCD(b, a % b)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.12ms, 61.3MB)
 * 테스트 2 〉	통과 (0.36ms, 60.3MB)
 * 테스트 3 〉	통과 (0.06ms, 58.7MB)
 * 테스트 4 〉	통과 (2.63ms, 62.1MB)
 * 테스트 5 〉	통과 (0.46ms, 61.2MB)
 * 테스트 6 〉	통과 (1.00ms, 61.1MB)
 * 테스트 7 〉	통과 (0.19ms, 60.3MB)
 * 테스트 8 〉	통과 (0.16ms, 59.3MB)
 * 테스트 9 〉	통과 (0.48ms, 60.8MB)
 * 테스트 10 〉	통과 (0.09ms, 58.9MB)
 * 테스트 11 〉	통과 (9.27ms, 106MB)
 * 테스트 12 〉	통과 (12.88ms, 99.1MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.*
 * class Solution {
 *     fun solution(arrayA: IntArray, arrayB: IntArray): Int {
 *         var gcdA = arrayA.fold(arrayA[0]){ a, b -> gcd(a, b) }
 *         var gcdB = arrayB.fold(arrayB[0]){ a, b -> gcd(a, b) }
 *
 *         var a = 0
 *         var b = 0
 *
 *         if(gcdA != 1 && arrayB.all{ it % gcdA != 0 }) a = gcdA
 *         if(gcdB != 1 && arrayA.all{ it % gcdB != 0 }) b = gcdB
 *
 *         return max(a, b)
 *     }
 *     fun gcd(n: Int, m: Int): Int {
 *         if (m == 0) return n
 *         else return gcd(m, n % m)
 *     }
 * }
 * 테스트 1 〉	통과 (0.13ms, 59.8MB)
 * 테스트 2 〉	통과 (0.39ms, 60.2MB)
 * 테스트 3 〉	통과 (0.10ms, 59.4MB)
 * 테스트 4 〉	통과 (2.67ms, 63.7MB)
 * 테스트 5 〉	통과 (0.57ms, 60.8MB)
 * 테스트 6 〉	통과 (1.26ms, 62.4MB)
 * 테스트 7 〉	통과 (0.21ms, 58.2MB)
 * 테스트 8 〉	통과 (0.17ms, 61.1MB)
 * 테스트 9 〉	통과 (0.78ms, 60.7MB)
 * 테스트 10 〉	통과 (0.10ms, 59.3MB)
 * 테스트 11 〉	통과 (11.12ms, 104MB)
 * 테스트 12 〉	통과 (11.25ms, 99.1MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(10, 17), intArrayOf(5, 20)),
    0
  )

  validate(s.solution(intArrayOf(10, 20), intArrayOf(5, 17)),
    10
  )

  validate(s.solution(intArrayOf(14, 35, 119), intArrayOf(18, 30, 102)),
    7
  )

}

//    println("a= $a, b = $b, ans = $ans")
