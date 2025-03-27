package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.GetContinentsQuery
import com.azelder.waitwhat.game.data.model.Continent

fun GetContinentsQuery.Continent.toContinent(): Continent {
    return Continent(
        code = code,
        name = name
    )
}