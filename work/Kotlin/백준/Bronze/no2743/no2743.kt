package 백준.Bronze.no2743

import java.util.Scanner

fun main() {

  val sc = Scanner(System.`in`)
  val m = sc.nextLine()
  println(solution(m))

}


fun solution(v: String): Int {
  if (v.isEmpty()) throw NullPointerException("필수값입니다.")
  return v.length

}
