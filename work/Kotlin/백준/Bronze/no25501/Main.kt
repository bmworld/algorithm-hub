package 백준.Bronze.no25501

fun main() {

  val br = System.`in`.bufferedReader()
  val size = br.readLine().toInt()
  val answer = StringBuilder()
  repeat(size) { answer.append(isPalindrome(br.readLine())).append("\n") }

  println(answer)
}

fun isPalindrome(s: String): String {

  var cnt = 0

  fun recursion(
      s: String,
      l: Int,
      r: Int,
  ): Int {
    cnt++
    return if (l >= r) 1 else if (s[l] != s[r]) 0 else recursion(s, l + 1, r - 1)
  }

  val result = recursion(s, 0, s.length - 1)

  return "$result $cnt"
}
