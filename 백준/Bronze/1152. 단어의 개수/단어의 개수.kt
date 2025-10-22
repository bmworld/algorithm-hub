fun main() {
  val s = readln().trim()
  print(if (s.isBlank()) 0 else s.split(' ').size)
}