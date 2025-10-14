package 백준.Bronze.no1008

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val st = StringTokenizer(br.readLine(), " ")

  val a = st.nextToken().toDouble()
  val b = st.nextToken().toDouble()
  println(a / b)
}
