package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.example.project.model.Pokemon
import org.example.project.model.PokemonDetails
import org.example.project.model.PokemonListResponse

class PokeApiService(private val client: HttpClient) {
    suspend fun getPokemonList(limit: Int): List<PokemonDetails> {
        val response = client.get("https://pokeapi.co/api/v2/pokemon?limit=$limit")
        val result = response.body<PokemonListResponse>()

        return result.results.map { basic ->
            getPokemonDetails(basic.name)
        }
    }

    suspend fun getPokemonDetails(name: String): PokemonDetails {
        val response = client.get("https://pokeapi.co/api/v2/pokemon/$name")
        return response.body()
    }
}