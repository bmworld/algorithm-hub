package 프로그래머스.입문.Day8.외계행성의나이

const val ageMapper = "abcdefghij"

class Solution {

  val MAX_LEN = 4
  val ZERO = ageMapper[0]

  fun solution(age: Int): String {
    if (age == 0) return ZERO.toString()

    var ans = CharArray(MAX_LEN) { ZERO }
    var n = age
    var i = MAX_LEN
    while (n > 0) {
      val digit = n % 10
      ans[--i] = ageMapper[digit]
      n /= 10
    }
    return ans.concatToString(i, MAX_LEN)
  }
}

fun main() {
  val s = Solution()
  check(s.solution(0) == "a")
  check(s.solution(1) == "b")

  for (i in 0 until ageMapper.length) {
    check(s.solution(i).also { println(it) } == ageMapper[i].toString())
  }

  check(s.solution(100) == "baa")
  check(s.solution(1000) == "baaa")
}
