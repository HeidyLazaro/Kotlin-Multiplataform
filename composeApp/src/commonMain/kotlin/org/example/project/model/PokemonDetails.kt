package org.example.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetails(
    val id: Int,
    val name: String,
    val height: Int, // en decímetros
    val weight: Int, // en hectogramos

    @SerialName("sprites") val sprites: Sprites,

    val types: List<PokemonTypeSlot>,

    val abilities: List<PokemonAbilitySlot>
)

@Serializable
data class Sprites(
    @SerialName("front_default") val frontDefault: String?
)

@Serializable
data class PokemonTypeSlot(
    val slot: Int,
    val type: TypeInfo
)

@Serializable
data class TypeInfo(
    val name: String,
    val url: String
)

@Serializable
data class PokemonAbilitySlot(
    val ability: AbilityInfo,
    @SerialName("is_hidden") val isHidden: Boolean,
    val slot: Int
)

@Serializable
data class AbilityInfo(
    val name: String,
    val url: String
)
