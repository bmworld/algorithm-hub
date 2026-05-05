package 프로그래머스.입문.Day9.모스부호1

val morse = hashMapOf(
  ".-" to 'a',
  "-..." to 'b',
  "-.-." to 'c',
  "-.." to 'd',
  "." to 'e',
  "..-." to 'f',
  "--." to 'g',
  "...." to 'h',
  ".." to 'i',
  ".---" to 'j',
  "-.-" to 'k',
  ".-.." to 'l',
  "--" to 'm',
  "-." to 'n',
  "---" to 'o',
  ".--." to 'p',
  "--.-" to 'q',
  ".-." to 'r',
  "..." to 's',
  "-" to 't',
  "..-" to 'u',
  "...-" to 'v',
  ".--" to 'w',
  "-..-" to 'x',
  "-.--" to 'y',
  "--.." to 'z'
)

class Solution {

  fun solution(letter: String): String = letter.split(" ").map { morse[it] }.joinToString("")
}

fun main() {
  val s = Solution()
  check(s.solution(".... . .-.. .-.. ---").also { println(it) } == "hello")
  check(s.solution(".--. -.-- - .... --- -.").also { println(it) } == "python")
}
