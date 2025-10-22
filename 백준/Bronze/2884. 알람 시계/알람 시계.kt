import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  var h = readInt(br)
  var m = readInt(br) - 45

  if (m < 0) {
    if (h == 0) h = 23 else h--
    m += 60
  }

  print(h)
  print(' ')
  print(m)
}

fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var sign = 1
  if (c == '-'.code) { // 부호 처리
    sign = -1
    c = input.read()
  }
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = input.read()
  }
  return n * sign
}