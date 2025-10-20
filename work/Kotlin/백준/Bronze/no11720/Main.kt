package 백준.Bronze.no11720

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
  val br = BufferedReader(InputStreamReader(System.`in`))
  br.readLine()
  val s = br.readLine()
  var sum = 0
  for (c in s) sum += c.code - 48

  print(sum)
}
