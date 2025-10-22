import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val h = readInt(br)
  val m = readInt(br)

  var nh = h
  var nm = m - 45
  if (nm < 0) {
    if (nh == 0) nh = 23 else nh--
    nm += 60
  }

  print(nh)
  print(' ')
  print(nm)
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