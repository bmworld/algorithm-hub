package 프로그래머스.입문.최빈값구하기

class Solution {

  fun solution(array: IntArray): Int {
    val ch = IntArray(1000)

    var ans = 0
    var dup = false
    var maxCnt = 0
    for (v in array) {
      val cnt = ch[v] + 1
      ch[v] = cnt
      if (cnt > maxCnt) {
        maxCnt = cnt
        ans = v
        dup = false
      } else if (cnt == maxCnt) dup = true
    }
    return if (dup) -1 else ans
  }
}
