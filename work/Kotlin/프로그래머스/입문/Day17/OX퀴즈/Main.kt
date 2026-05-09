package 프로그래머스.입문.Day17.OX퀴즈

class Solution {

  val SPACE = 32
  val PLUS = 43
  val MINUS = 45
  val ZERO = 48
  val EQUAL = 61
  val NUM = ZERO..ZERO + 9

  val X = "X"
  val O = "O"
  fun solution(quiz: Array<String>): Array<String> {
    var ans: Array<String> = Array<String>(quiz.size) { "" }
    val nums = IntArray(2)

    repeat(quiz.size) {
      var s = 1
      var v = 0
      var op = PLUS
      var ni = 0

      val str = quiz[it]
      for (i in 0 until str.length) {
        val code = str[i].code
        when (code) {
          in NUM -> v = v * 10 + code - ZERO
          MINUS -> if (str[i + 1].code == SPACE) op = code else s = -1
          SPACE -> if (str[i - 1].code in NUM) {
            nums[ni++] = i(s, v)
            s = 1
            v = 0
          }
          EQUAL -> {
            s = 1
            v = 0
          }
        }
      }
      val a = nums[0] + (if (op == PLUS) 1 else -1) * nums[1]
      ans[it] = if (a == i(s, v)) O else X
    }
    return ans
  }

  fun i(s: Int, v: Int): Int = s * v
}

fun main() {
  val s = Solution()
  val act = s.solution(arrayOf<String>("3 - 4 = -3", "5 + 6 = 11", "-11 - -10 = -1"))
  val exp = arrayOf<String>("X", "O", "O")
  repeat(exp.size) {
    check(act[it] == exp[it])
  }
}

/**
 * AS IS
 * 테스트 1 〉	통과 (0.03ms, 60.5MB)
 * 테스트 2 〉	통과 (0.03ms, 60.6MB)
 * 테스트 3 〉	통과 (0.03ms, 61.5MB)
 * 테스트 4 〉	통과 (0.03ms, 61MB)
 * 테스트 5 〉	통과 (0.03ms, 60.8MB)
 * 테스트 6 〉	통과 (0.04ms, 63.1MB)
 * 테스트 7 〉	통과 (0.03ms, 62.5MB)
 * 테스트 8 〉	통과 (0.02ms, 60.9MB)
 * 테스트 9 〉	통과 (0.02ms, 62.2MB)
 * 테스트 10 〉	통과 (0.03ms, 62.1MB)
 *
 * TO BE
 * 테스트 1 〉	통과 (0.02ms, 64.6MB)
 * 테스트 2 〉	통과 (0.03ms, 61.7MB)
 * 테스트 3 〉	통과 (0.03ms, 60.6MB)
 * 테스트 4 〉	통과 (0.03ms, 60.9MB)
 * 테스트 5 〉	통과 (0.03ms, 61MB)
 * 테스트 6 〉	통과 (0.02ms, 61.7MB)
 * 테스트 7 〉	통과 (0.02ms, 61.9MB)
 * 테스트 8 〉	통과 (0.02ms, 63MB)
 * 테스트 9 〉	통과 (0.02ms, 62.8MB)
 * 테스트 10 〉	통과 (0.02ms, 61.3MB)
 *
 */
