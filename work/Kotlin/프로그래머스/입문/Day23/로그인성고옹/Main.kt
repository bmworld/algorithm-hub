package 프로그래머스.입문.Day23.로그인성고옹

import util.validate

class Solution {

  fun solution(id_pw: Array<String>, db: Array<Array<String>>): String {
    val id = id_pw[0]
    val pw = id_pw[1]
    for (user in db) {
      val uId = user[0]
      val uPw = user[1]
      if (id != uId) continue

      return if (pw == uPw) "login" else "wrong pw"
    }

    return "fail"
  }
}

fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("meosseugi", "1234"),
    arrayOf(
      arrayOf("asdf", "1234"),
      arrayOf("yyy", "1234"),
      arrayOf("meosseugi", "1234"),
    ),
  ), "login")

  validate(s.solution(
    arrayOf("meosseugi", "1234"),
    arrayOf(
      arrayOf("asdf", "1234"),
      arrayOf("yyy", "1234"),
      arrayOf("meosseugi", "12345"),
    ),
  ), "wrong pw")

  validate(s.solution(
    arrayOf("rabbit04", "1234"),
    arrayOf(
      arrayOf("asdf", "1234"),
      arrayOf("yyy", "1234"),
      arrayOf("meosseugi", "12345"),
    ),
  ), "fail")

}
