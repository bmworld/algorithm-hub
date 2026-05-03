package 프로그래머스.입문.Day6.문자반복출력하기

class Solution {

  fun solution(str: String, n: Int): String {
    val len = str.length
    val arr = CharArray(len * n)
    for (i in 0 until len) {
      val c = str[i]
      val stt = i * n
      repeat(n) {
        arr[stt + it] = c
      }
    }

    return arr.concatToString()
  }
}

fun main() {
  val s = Solution()
  check(s.solution("hello", 3) == "hhheeellllllooo")
}

/**
테스트 1 〉	통과 (6.45ms, 64.6MB)
테스트 2 〉	통과 (6.55ms, 64.7MB)
테스트 3 〉	통과 (6.00ms, 63.4MB)
테스트 4 〉	통과 (6.24ms, 63.4MB)
 */
