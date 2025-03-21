package com.azelder.waitwhat.game.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.azelder.waitwhat.GetCountriesQuery
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

    companion object {
        const val SERVICE_URL = "https://countries.trevorblades.com/graphql"
    }
}