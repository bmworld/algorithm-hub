package 프로그래머스.입문.Day19.잘라서배열로저장하기

import util.validate


class Solution {

  fun solution(str: String, n: Int): Array<String> {
    val len = str.length
    val size = (len + n - 1) / n
    var ans = Array<String>(size) { "" }
    var ai = 0
    val buf = CharArray(n)
    for (i in 0 until len) {
      val j = i % n
      buf[j] = str[i]
      if (j == n - 1 || i == len - 1) ans[ai++] = buf.concatToString(0, j + 1)
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  validate(s.solution("abc1Addfggg4556b", 6), arrayOf<String>("abc1Ad", "dfggg4", "556b"))
  validate(s.solution("abcdef123", 3), arrayOf<String>("abc", "def", "123"))
  validate(s.solution("abcdef1", 3), arrayOf<String>("abc", "def", "1"))
}
