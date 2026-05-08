package 프로그래머스.입문.Day16.배열의유사도

class Solution {

  fun solution(s1: Array<String>, s2: Array<String>): Int {
    var ans = 0
    val ch = HashMap<String, Boolean>().also {
      for (s in s1) it[s] = true
    }

    for (s in s2) if (ch[s] == true) ans++
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(arrayOf("a", "b", "c"), arrayOf("com", "b", "d", "p", "c")) == 2)
  check(s.solution(arrayOf("n", "omg"), arrayOf("m", "dot")) == 0)
  check(s.solution(arrayOf("n", "omg", "m"), arrayOf("m", "dot")) == 1)
}
