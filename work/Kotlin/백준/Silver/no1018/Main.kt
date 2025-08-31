package 백준.Silver.no1018

import java.util.*
import kotlin.math.min

fun main() {

  val sc = Scanner(System.`in`)
  val first = sc.nextLine()
  val row = first[0].code   // 행 개수
  val col = first[1].code   // 열 개수 (검증용)

  val arr =
      Array(row) {
        sc.nextLine()
            .trim()
            .map { if (it.toString() == "W") 1 else 0 }
      }

  println(solution(arr))
}

/**
 * @return 8x8 체스판 만들기 위해 '다시 칠해야하는 정사각형'의 최소 개수
 * @arr 보드 (W = 1, B = 0)
 */
fun solution(arr: Array<List<Int>>): Int {

  // ---------------------------------------------------------------------
  // 체크판을 만들 수 있는 2가지 CASE 각각에 대해
  // 검증결과를 2차원 배열에 저장
  val rowSize = arr.size
  val colSize = arr[0].size


  val chODD = Array(rowSize) { IntArray(colSize) } // 시작점 0 ----> 유효하지 않은 값 = 1
  val chEVEN = Array(rowSize) { IntArray(colSize) } // 시작점 1 ----> 유효하지 않은 값 = 1

  for ((row, ints) in arr.withIndex()) {
    for ((col, v) in ints.withIndex()) {
      if (
          (row % 2 == 1 && col % 2 == 1) // 홀, 홀
          || (row % 2 == 0 && col % 2 == 0) // 짝 ,짝
      ) {

        chODD[row][col] = if (v == 1) 0 else 1
        chEVEN[row][col] = if (v == 0) 0 else 1
      } else {

        chODD[row][col] = if (v == 0) 0 else 1
        chEVEN[row][col] = if (v == 1) 0 else 1
      }
    }
  }

  // ---------------------------------------------------------------------
//  println("--- CHECK ODD")
//  for ((row, ints) in chODD.withIndex()) {
//    for ((col, v) in ints.withIndex()) {
//      println("row=${row}, col=${col}, v=$v")
//    }
//  }
//
//  println("--- CHECK EVEN")
//  for ((row, ints) in chEVEN.withIndex()) {
//    for ((col, v) in ints.withIndex()) {
//      println("row=${row}, col=${col}, v=$v")
//    }
//  }

  var answer = Int.MAX_VALUE
  var rowStart = 0
  var colStart = 0
  val rowMax = rowSize - 8
  val colMax = colSize - 8

  while (rowStart <= rowMax && colStart <= colMax) {

    var sumOfODD = 0
    var sumOfEVEN = 0
    for (row in rowStart until rowStart + 8 ) {
      for (col in colStart until colStart + 8 ) {
        if (chEVEN[row][col] == 1) sumOfEVEN++
        if (chODD[row][col] == 1) sumOfODD++
      }

    }
//    println("sumOfODD = ${sumOfODD}")
//    println("sumOfEVEN = ${sumOfEVEN}")

    answer = min(answer, sumOfODD)
    answer = min(answer, sumOfEVEN)

    if (rowStart < rowMax) {
      rowStart++
    } else {
      rowStart = 0 // 중요
      colStart++
    }
  }

  return answer
}
