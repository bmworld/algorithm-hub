package 프로그래머스.입문.Day13.배열원소의길이

class Solution {

  fun solution(strlist: Array<String>): IntArray = strlist.map { it.length }.toIntArray()
}

fun main() {
  val s = Solution()
  val orgn = arrayOf<String>("We", "are", "the", "world!")
  val ans = s.solution(orgn)
  for (i in 0 until orgn.size) check(orgn[i].length == ans[i])
}
