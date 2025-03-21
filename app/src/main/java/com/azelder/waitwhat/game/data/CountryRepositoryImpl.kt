package com.azelder.waitwhat.game.data

import com.azelder.waitwhat.game.data.model.Country
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    val dataSource: CountryDataSource
) : CountryRepository {
    override suspend fun getCountries(): List<Country> = dataSource.getCountries()
} 