package 프로그래머스.입문.Day21.외계어사전

class Solution {

  val EXIST = 1
  val NOT_EXIST = 2
  fun solution(spell: Array<String>, dic: Array<String>): Int {
    l@ for (word in dic) {
      for (s in spell) {
        val c1 = s[0]
        var cnt = 0
        for (c2 in word) {
          if (c1 != c2) continue
          if (++cnt > 1) continue@l
        }
        if (cnt == 0) continue@l
      }
      return EXIST

    }
    return NOT_EXIST
  }
}

fun main() {
  val s = Solution()
  check(s.solution(
    arrayOf("p", "o", "s"),
    arrayOf("sod", "eocd", "qixm", "adio", "soo")
  )
    == 2)

  check(s.solution(
    arrayOf("z", "d", "x"),
    arrayOf("def", "dww", "dzx", "loveaw")
  )
    == 1)

  check(s.solution(
    arrayOf("s", "o", "m", "d"),
    arrayOf("moos", "dzx", "smm", "sunmmo", "som")
  )
    == 2)

  check(s.solution(
    arrayOf("a", "b"),
    arrayOf("ab")
  )
    == 1)

  check(s.solution(
    arrayOf("a", "b"),
    arrayOf("zzabzz")
  )
    == 1)

  check(s.solution(
    arrayOf("a", "b"),
    arrayOf("abb")
  )
    == 2)
}
