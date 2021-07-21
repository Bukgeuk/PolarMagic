package dev.bukgeuk.polarmagic.util

fun getGraphA(v: Double, t: Int): Double {
    // (at^3)/3 = v
    return (3 * v / Math.pow(t.toDouble(), 3.0))
}

fun getGraphS(a: Double, i: Int, t: Int): Double {
    return (a * (Math.pow((i - t).toDouble(), 3.0) - Math.pow((i - 1 - t).toDouble(), 3.0)) / 3)
}