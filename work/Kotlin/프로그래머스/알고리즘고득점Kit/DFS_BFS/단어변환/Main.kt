package 프로그래머스.알고리즘고득점Kit.DFS_BFS.단어변환

import util.validate

class Solution {

  fun solution(begin: String, target: String, words: Array<String>): Int {
    if (!words.contains(target)) return 0

    val N = words.size
    val len = begin.length
    val used = BooleanArray(N)

    var min = Int.MAX_VALUE

    fun dfs(dep: Int, cur: String) {
      if (dep > min || dep >= N) return
      if (cur == target) {
        if (dep < min) min = dep
        return
      }

      l@ for (i in 0 until N) {
        if (used[i]) continue
        var diff = 0
        val comp = words[i]
        for (j in 0 until len)
          if (cur[j] != comp[j]) if (++diff > 1) continue@l

        used[i] = true
        dfs(dep + 1, comp)
        used[i] = false
      }
    }

    dfs(0, begin)
    return if (min == Int.MAX_VALUE) 0 else min
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (8.66ms, 61.9MB)
 * 테스트 2 〉	통과 (8.95ms, 62.8MB)
 * 테스트 3 〉	통과 (9.08ms, 63MB)
 * 테스트 4 〉	통과 (9.13ms, 61.2MB)
 * 테스트 5 〉	통과 (8.81ms, 63.4MB)
 *
 * [RIVAL 1]
 * import java.util.*
 *
 * class Solution {
 *     fun String.diff(other:String):Int {
 *         return this.filterIndexed { index, c -> c != other.get(index) }.map { 1 }.sum()
 *     }
 *
 *     class Word(val level:Int,val txt:String,val check:Array<Boolean>)
 *
 *     fun solution(begin: String, target: String, words: Array<String>): Int {
 *         val q: Queue<Word> = LinkedList()
 *         val checkArray = Array(words.size) { false }
 *         q.add(Word(0,begin,checkArray))
 *         var ret = 0
 *         while(!q.isEmpty()) {
 *             val w = q.poll()
 *             if(w.txt == target) {
 *                 ret = w.level
 *                 break
 *             }
 *             w.check.forEachIndexed { index, b ->
 *                 if(!b && (w.txt.diff(words[index]) == 1)) {
 *                     q.add(Word(w.level+1,words[index],w.check.clone().apply { set(index,true) }))
 *                 }
 *             }
 *         }
 *         return ret
 *     }
 * }
 * 테스트 1 〉	통과 (4.55ms, 59.9MB)
 * 테스트 2 〉	통과 (4.60ms, 61MB)
 * 테스트 3 〉	통과 (5.68ms, 60MB)
 * 테스트 4 〉	통과 (4.28ms, 60.7MB)
 *
 * [RIVAL 2]
 * import kotlin.math.min
 *
 * class Solution {
 *     fun solution(begin: String, target: String, words: Array<String>): Int {
 *         var answer = 0
 *         val n=words.size
 *         val adj=Array(n){BooleanArray(n)}
 *         var targetIdx=-1
 *         for(i in 0 until n){
 *             if(target==words[i]) targetIdx=i
 *         }
 *         fun checkString(str1:String,str2:String):Int{
 *             var count=0
 *             for(i in 0 until str1.length){
 *                 if(str1[i]!=str2[i])    count++
 *             }
 *             return count
 *         }
 *         for(i in 0 until n){
 *             for(j in 0 until n){
 *                 if(checkString(words[i],words[j])==1){
 *                     adj[i][j]=true
 *                 }
 *             }
 *         }
 *
 *         val visit=BooleanArray(n)
 *         val q=java.util.ArrayDeque<Pair<Int,Int>>()
 *         if(targetIdx!=-1){
 *             answer=Int.MAX_VALUE
 *             for(i in 0 until n){
 *                 val visit=BooleanArray(n)
 *                 if(checkString(begin,words[i])==1){
 *                     visit[i]=true
 *                     q.add(Pair(i,1))
 *                     while(!q.isEmpty()){
 *                         val p=q.poll()
 *                         if(p.first==targetIdx)  {
 *                             answer=min(answer,p.second)
 *                             break
 *                         }
 *                         for(j in 0 until n){
 *                             if(!visit[j]&&adj[p.first][j]){
 *                                 visit[j]=true
 *                                 q.add(Pair(j,p.second+1))
 *                             }
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.26ms, 59.7MB)
 * 테스트 2 〉	통과 (0.46ms, 59.6MB)
 * 테스트 3 〉	통과 (0.85ms, 59.1MB)
 * 테스트 4 〉	통과 (0.35ms, 59.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("hit", "cog", arrayOf(
    "hot", "dot", "dog", "lot", "log", "cog"
  )), 4)

  validate(s.solution("hit", "cog", arrayOf(
    "hot", "dot", "dog", "lot", "log"
  )), 0)

  validate(s.solution("hit", "hot", arrayOf(
    "hot", "dot", "dog", "lot", "hit"
  )), 1)
}

// println("---- [$dep] = ${used.contentToString()}")
//        println("[$dep][$i] cur=$cur vs comp=$comp (diff= $diff)")
