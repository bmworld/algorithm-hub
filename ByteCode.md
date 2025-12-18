# 💡 ASCII Code Map

## 개요

ASCII(=American Standard Code for Information Interchange)는
**`0 ~ 127`** 범위의 정수값으로서 각 숫자에 문자나 제어 문자가 매핑된다.

> Byte는 -128~127 범위를 가지지만, 실제 바이트 값은 0~255이며  
> `unsigned = (byte.toInt() and 0xFF)` 로 해석됨.  
> ASCII는 0~127까지만 유효하며, 128~255는 인코딩마다 다른 확장 영역임.

📎 참고
• 표준 ASCII: https://www.asciitable.com￼
• UTF-8: ASCII 포함하며, 0~127 구간은 동일


---

## Mini Map

| 범위      | 설명            | 예시                          |
|---------|---------------|-----------------------------|
| 0–31    | 제어 문자 (표시 불가) | `\n(10)`, `\r(13)`          |
| 32–47   | 공백 및 특수문자     | space(32), !\"#$%&'()*+,-./ |
| 48–57   | 숫자            | 0–9                         |
| 58–64   | 특수문자          | :;<=>?@                     |
| 65–90   | 대문자           | A–Z                         |
| 91–96   | 괄호 및 구분자      | [ \\ ] ^ _ `                |
| 97–122  | 소문자           | a–z                         |
| 123–126 | 특수문자          | { &#124; } ~                |
| 127     | 삭제(DEL)       | ␡                           |

## Total Map

| Byte (signed) | Unsigned (0–255) |  ASCII  | Type                 |
|--------------:|-----------------:|:-------:|:---------------------|
|   `-128`~`-1` |      `128`~`255` |   `-`   | extended / non-ASCII |
|             0 |                0 |  `NUL`  | control              |
|             1 |                1 |  `SOH`  | control              |
|             2 |                2 |  `STX`  | control              |
|             3 |                3 |  `ETX`  | control              |
|             4 |                4 |  `EOT`  | control              |
|             5 |                5 |  `ENQ`  | control              |
|             6 |                6 |  `ACK`  | control              |
|             7 |                7 |  `BEL`  | control              |
|             8 |                8 |  `BS`   | control              |
|             9 |                9 |  `TAB`  | control              |
|            10 |               10 |  `LF`   | control              |
|            11 |               11 |  `VT`   | control              |
|            12 |               12 |  `FF`   | control              |
|            13 |               13 |  `CR`   | control              |
|            14 |               14 |  `SO`   | control              |
|            15 |               15 |  `SI`   | control              |
|            16 |               16 |  `DLE`  | control              |
|            17 |               17 |  `DC1`  | control              |
|            18 |               18 |  `DC2`  | control              |
|            19 |               19 |  `DC3`  | control              |
|            20 |               20 |  `DC4`  | control              |
|            21 |               21 |  `NAK`  | control              |
|            22 |               22 |  `SYN`  | control              |
|            23 |               23 |  `ETB`  | control              |
|            24 |               24 |  `CAN`  | control              |
|            25 |               25 |  `EM`   | control              |
|            26 |               26 |  `SUB`  | control              |
|            27 |               27 |  `ESC`  | control              |
|            28 |               28 |  `FS`   | control              |
|            29 |               29 |  `GS`   | control              |
|            30 |               30 |  `RS`   | control              |
|            31 |               31 |  `US`   | control              |
|            32 |               32 |   ` `   | symbol               |
|            33 |               33 |   `!`   | symbol               |
|            34 |               34 |   `"`   | symbol               |
|            35 |               35 |   `#`   | symbol               |
|            36 |               36 |   `$`   | symbol               |
|            37 |               37 |   `%`   | symbol               |
|            38 |               38 |   `&`   | symbol               |
|            39 |               39 |   `'`   | symbol               |
|            40 |               40 |   `(`   | symbol               |
|            41 |               41 |   `)`   | symbol               |
|            42 |               42 |   `*`   | symbol               |
|            43 |               43 |   `+`   | symbol               |
|            44 |               44 |   `,`   | symbol               |
|            45 |               45 |   `-`   | symbol               |
|            46 |               46 |   `.`   | symbol               |
|            47 |               47 |   `/`   | symbol               |
|            48 |               48 |   `0`   | digit                |
|            49 |               49 |   `1`   | digit                |
|            50 |               50 |   `2`   | digit                |
|            51 |               51 |   `3`   | digit                |
|            52 |               52 |   `4`   | digit                |
|            53 |               53 |   `5`   | digit                |
|            54 |               54 |   `6`   | digit                |
|            55 |               55 |   `7`   | digit                |
|            56 |               56 |   `8`   | digit                |
|            57 |               57 |   `9`   | digit                |
|            58 |               58 |   `:`   | symbol               |
|            59 |               59 |   `;`   | symbol               |
|            60 |               60 |   `<`   | symbol               |
|            61 |               61 |   `=`   | symbol               |
|            62 |               62 |   `>`   | symbol               |
|            63 |               63 |   `?`   | symbol               |
|            64 |               64 |   `@`   | symbol               |
|            65 |               65 |   `A`   | upper                |
|            66 |               66 |   `B`   | upper                |
|            67 |               67 |   `C`   | upper                |
|            68 |               68 |   `D`   | upper                |
|            69 |               69 |   `E`   | upper                |
|            70 |               70 |   `F`   | upper                |
|            71 |               71 |   `G`   | upper                |
|            72 |               72 |   `H`   | upper                |
|            73 |               73 |   `I`   | upper                |
|            74 |               74 |   `J`   | upper                |
|            75 |               75 |   `K`   | upper                |
|            76 |               76 |   `L`   | upper                |
|            77 |               77 |   `M`   | upper                |
|            78 |               78 |   `N`   | upper                |
|            79 |               79 |   `O`   | upper                |
|            80 |               80 |   `P`   | upper                |
|            81 |               81 |   `Q`   | upper                |
|            82 |               82 |   `R`   | upper                |
|            83 |               83 |   `S`   | upper                |
|            84 |               84 |   `T`   | upper                |
|            85 |               85 |   `U`   | upper                |
|            86 |               86 |   `V`   | upper                |
|            87 |               87 |   `W`   | upper                |
|            88 |               88 |   `X`   | upper                |
|            89 |               89 |   `Y`   | upper                |
|            90 |               90 |   `Z`   | upper                |
|            91 |               91 |   `[`   | symbol               |
|            92 |               92 |   `\`   | symbol               |
|            93 |               93 |   `]`   | symbol               |
|            94 |               94 |   `^`   | symbol               |
|            95 |               95 |   `_`   | symbol               |
|            96 |               96 | `` ` `` | symbol               |
|            97 |               97 |   `a`   | lower                |
|            98 |               98 |   `b`   | lower                |
|            99 |               99 |   `c`   | lower                |
|           100 |              100 |   `d`   | lower                |
|           101 |              101 |   `e`   | lower                |
|           102 |              102 |   `f`   | lower                |
|           103 |              103 |   `g`   | lower                |
|           104 |              104 |   `h`   | lower                |
|           105 |              105 |   `i`   | lower                |
|           106 |              106 |   `j`   | lower                |
|           107 |              107 |   `k`   | lower                |
|           108 |              108 |   `l`   | lower                |
|           109 |              109 |   `m`   | lower                |
|           110 |              110 |   `n`   | lower                |
|           111 |              111 |   `o`   | lower                |
|           112 |              112 |   `p`   | lower                |
|           113 |              113 |   `q`   | lower                |
|           114 |              114 |   `r`   | lower                |
|           115 |              115 |   `s`   | lower                |
|           116 |              116 |   `t`   | lower                |
|           117 |              117 |   `u`   | lower                |
|           118 |              118 |   `v`   | lower                |
|           119 |              119 |   `w`   | lower                |
|           120 |              120 |   `x`   | lower                |
|           121 |              121 |   `y`   | lower                |
|           122 |              122 |   `z`   | lower                |
|           123 |              123 |   `{`   | symbol               |
|           124 |              124 |  `\| `  | symbol               |
|           125 |              125 |   `}`   | symbol               |
|           126 |              126 |   `~`   | symbol               |
|           127 |              127 |  `DEL`  | control              |
