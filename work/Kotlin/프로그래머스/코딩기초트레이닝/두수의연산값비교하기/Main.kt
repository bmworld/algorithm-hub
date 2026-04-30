package 프로그래머스.코딩기초트레이닝.두수의연산값비교하기

class Solution {

  fun solution(a: Int, b: Int): Int {
    var tmp = a
    var rmn = b
    while (rmn > 0) {
      tmp = tmp * 10
      rmn /= 10
    }
    return maxOf(tmp + b, 2 * a * b)
  }
}


fun main() {
  println(Solution().solution(2, 91))
  println(Solution().solution(91, 2))
}
