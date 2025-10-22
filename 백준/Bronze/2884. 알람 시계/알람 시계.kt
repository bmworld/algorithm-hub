import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val token = StringTokenizer(br.readLine())
  var h = Integer.parseInt(token.nextToken())
  var m = Integer.parseInt(token.nextToken()) - 45

  if (m < 0) {
    if (--h < 0) h = 23
    m += 60
  }

  print(h)
  print(' ')
  print(m)

  br.close()
}