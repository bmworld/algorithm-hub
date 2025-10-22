fun main() {
  val s = readln().trim()
  var spaceCnt = 0
  var hasChar = false
  for (ch in s) {
    if (ch == ' ') spaceCnt++ else hasChar = true
  }
  print(if (hasChar) spaceCnt + 1 else 0)
}