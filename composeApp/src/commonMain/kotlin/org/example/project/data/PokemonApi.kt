package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.example.project.model.Pokemon
import org.example.project.model.PokemonListResponse

class PokeApiService(private val client: HttpClient) {

    suspend fun getPokemonList(limit: Int = 20): List<Pokemon> {
        val response: HttpResponse = client.get("https://pokeapi.co/api/v2/pokemon?limit=$limit")
        val result = response.body<PokemonListResponse>()
        return result.results
    }
}