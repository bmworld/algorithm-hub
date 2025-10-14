package 백준.Bronze.no1001

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
  val br = BufferedReader(InputStreamReader(System.`in`))
  val st = StringTokenizer(br.readLine(), " ")
  val a = st.nextToken().toInt()
  val b = st.nextToken().toInt()
  println(a - b)
}
