import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
  val br = BufferedReader(InputStreamReader(System.`in`))
  val none = br.readLine()
  val v = br.readLine().chars()
  var r = 0
  for (char in v) r += char - 48

  print(r)
}