package 백준.Bronze.no2566

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer



fun main() {


  val br = BufferedReader(InputStreamReader(System.`in`))
  val size = 9
  val arr = Array(size) {
    val st = StringTokenizer(br.readLine()," ")
    IntArray(size) {st.nextToken().toInt()}
  }

  println(solution(arr))

}

fun solution(arr: Array<IntArray>): String {


  var max = Int.MIN_VALUE
  var maxColOrder = Int.MIN_VALUE
  var maxRowOrder = Int.MIN_VALUE

  arr.forEachIndexed{rowIdx, line ->
    line.forEachIndexed { colIdx, num ->

      if (max < num) {
        max = num
        maxColOrder = colIdx + 1
        maxRowOrder = rowIdx + 1
      }
      arr[rowIdx][colIdx] = num
    }
  }



  return "$max\n$maxRowOrder $maxColOrder"

}
