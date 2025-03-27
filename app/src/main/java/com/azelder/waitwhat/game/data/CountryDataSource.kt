package com.azelder.waitwhat.game.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.azelder.waitwhat.GetContinentsQuery
import com.azelder.waitwhat.GetCountriesOnContinentQuery
import com.azelder.waitwhat.GetCountriesQuery
import com.azelder.waitwhat.game.data.model.Continent
import com.azelder.waitwhat.game.data.model.Country
import okhttp3.OkHttpClient
import javax.inject.Inject

class CountryDataSource @Inject constructor() {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl(SERVICE_URL)
        .okHttpClient(OkHttpClient())
        .build()

    suspend fun getCountries(): List<Country> {
        return try {
            val response = apolloClient.query(
                GetCountriesQuery()
            ).execute()
            response.data?.countries?.map { country ->
                country.toCountry()
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getContinents(): List<Continent> {
        return try {
            val response = apolloClient.query(
                GetContinentsQuery()
            ).execute()
            response.data?.continents?.map { continent ->
                continent.toContinent()
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getCountriesOnContinent(continentCode: String): List<Country> {
        return try {
            val response = apolloClient.query(
                GetCountriesOnContinentQuery(continentCode)
            ).execute()
            response.data?.continent?.countries?.map { country ->
                country.toCountry()
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    companion object {
        const val SERVICE_URL = "https://countries.trevorblades.com/graphql"
    }
}