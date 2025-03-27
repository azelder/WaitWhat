package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.GetCountriesOnContinentQuery
import com.azelder.waitwhat.GetCountriesQuery
import com.azelder.waitwhat.game.data.model.Country

fun GetCountriesQuery.Country.toCountry() : Country {
    return Country(
        code = code,
        name = name,
        emoji = emoji,
        emojiU = emojiU,
    )
}

fun GetCountriesOnContinentQuery.Country.toCountry() : Country {
    return Country(
        code = code,
        name = name,
        emoji = emoji,
    )
}