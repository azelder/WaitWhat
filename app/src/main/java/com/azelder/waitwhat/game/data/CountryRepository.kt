package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.data.model.Country

interface CountryRepository {
    suspend fun getCountries(): List<Country>
} 