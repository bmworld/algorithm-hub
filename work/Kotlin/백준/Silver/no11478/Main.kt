package 백준.Silver.no11478

fun main() {
  val a: String = readln()
  var cnt = 0
  val strLen = a.length
  for (len in 1..strLen) {
    val ch = HashSet<String>()
    repeat(strLen - len + 1) { l ->
      ch.add(a.substring(l, l + len))
    }
    cnt += ch.size
  }

  print(cnt)
}
