package com.wahidabd.shinigami.utils


/**
 * Created by Wahid on 4/9/2023.
 * Github github.com/wahidabd.
 */


data class Quadruple<out A, out B, out C, out D >(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
): java.io.Serializable
