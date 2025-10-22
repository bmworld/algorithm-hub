fun main() {
  val s = readln().trim()
  if (s.isBlank()) return print(0)

  var spaceCnt = 0
  for (ch in s) {
    if (ch == ' ') spaceCnt++
  }

  print(spaceCnt + 1)
}