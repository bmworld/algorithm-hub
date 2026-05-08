package 프로그래머스.입문.Day15.영어가싫어요


/**
 * z_ero
 * o_ne
 * tw_o
 * th_ree
 * fo_ur
 * fi_ve
 * si_x
 * se_ven
 * e_ight
 * n_ine
 */

class Solution {


  fun solution(str: String): Long {
    var ans = 0L
    var i = 0
    val len = str.length
    while (i < len) {
      val x = when (str[i]) {
        'z' -> {
          i += 4
          0
        }
        'o' -> {
          i += 3
          1
        }
        't' -> if (str[i + 1] == 'w') {
          i += 3
          2
        } else {
          i += 5
          3
        }
        'f' -> if (str[i + 1] == 'o') {
          i += 4
          4
        } else {
          i += 4
          5
        }
        's' -> if (str[i + 1] == 'i') {
          i += 3
          6
        } else {
          i += 5
          7
        }
        'e' -> {
          i += 5
          8
        }
        'n' -> {
          i += 4
          9
        }
        else -> break
      }
      ans = ans * 10 + x
    }

    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution("onetwothreefourfivesixseveneightnine") == 123456789L)
  check(s.solution("onefourzerosixseven") == 14067L)
  check(s.solution("one") == 1L)
  check(s.solution("onezero") == 10L)
}
