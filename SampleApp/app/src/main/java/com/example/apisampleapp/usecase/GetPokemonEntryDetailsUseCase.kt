package com.example.apisampleapp.usecase

import com.example.apisampleapp.dao.PokemonDao
import com.example.apisampleapp.dao.entities.PokemonEntity
import com.example.apisampleapp.network.PokemonApiRepository
import com.example.apisampleapp.network.models.PokemonEntry
import com.example.apisampleapp.network.models.PokemonEntryStat
import com.example.apisampleapp.usecase.shared.UseCase
import com.example.apisampleapp.util.PokemonStat
import com.example.apisampleapp.util.PokemonType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

/**
 * UseCase for getting an specific Pokemon entry details.
 */
class GetPokemonEntryDetailsUseCase @Inject constructor(
    private val repository: PokemonApiRepository,
    private val pokemonDao: PokemonDao
) : UseCase<Int, UIPokemonEntry?>() {

    override suspend fun execute(input: Int): UIPokemonEntry? {
        val uiEntry: UIPokemonEntry?

        if (pokemonDao.entryExists(input)) {
            val pokemonEntry = pokemonDao.findById(input)
            uiEntry = mapEntityToUi(pokemonEntry)
        } else {
            val pokemonEntryDetails = repository.getPokemonEntryDetails(input)
            uiEntry = mapResponseToUi(pokemonEntryDetails)?.apply {
                pokemonDao.insertAll(mapUiToEntity(this))
            }
        }

        return uiEntry
    }


    private suspend fun mapEntityToUi(entity: PokemonEntity): UIPokemonEntry =
        withContext(Dispatchers.Default) {
            val types = arrayListOf(PokemonType.entries[entity.type1])
            entity.type2?.let { types.add(PokemonType.entries[it]) }

            return@withContext UIPokemonEntry(
                id = entity.id,
                displayId = entity.displayId,
                name = entity.name,
                spriteUrl = entity.spriteUrl,
                types = types,
                stats = UIPokemonStats(
                    hpStat = PokemonStat.HEALTH_POINTS.apply { statValue = entity.baseHp },
                    attackStat = PokemonStat.ATTACK.apply { statValue = entity.baseAttack },
                    defenseStat = PokemonStat.DEFENSE.apply { statValue = entity.baseDefense },
                    spAttackStat = PokemonStat.SPECIAL_ATTACK.apply { statValue = entity.baseSpAttack },
                    spDefenseStat = PokemonStat.SPECIAL_DEFENSE.apply { statValue = entity.baseSpDefense },
                    speedStat = PokemonStat.SPEED.apply { statValue = entity.baseSpeed }
                )
            )
        }

    private suspend fun mapUiToEntity(uiPokemon: UIPokemonEntry) =
        withContext(Dispatchers.Default) {
            return@withContext PokemonEntity(
                id = uiPokemon.id,
                displayId = uiPokemon.displayId,
                name = uiPokemon.name,
                spriteUrl = uiPokemon.spriteUrl,
                type1 = uiPokemon.types.first().ordinal,
                type2 = if (uiPokemon.types.size > 1) uiPokemon.types.last().ordinal else null,
                baseHp = uiPokemon.stats.hpStat.statValue,
                baseAttack = uiPokemon.stats.attackStat.statValue,
                baseDefense = uiPokemon.stats.defenseStat.statValue,
                baseSpAttack = uiPokemon.stats.spAttackStat.statValue,
                baseSpDefense = uiPokemon.stats.spDefenseStat.statValue,
                baseSpeed = uiPokemon.stats.speedStat.statValue
            )
        }

    private suspend fun mapResponseToUi(response: PokemonEntry?) =
        withContext(Dispatchers.Default) {
            return@withContext response?.let { entry ->
                UIPokemonEntry(
                    id = entry.id,
                    displayId = mapDisplayId(entry.id),
                    name = entry.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                    },
                    spriteUrl = entry.sprites.others.official.url,
                    types = entry.types.map { pokemonEntryType ->
                        val pokemonResponseType = pokemonEntryType.type.name
                        PokemonType.valueOf(pokemonResponseType.uppercase())
                    },
                    stats = mapPokemonStats(entry.stats)
                )
            }
        }

    private suspend fun mapPokemonStats(stats: List<PokemonEntryStat>): UIPokemonStats {
        val uiPokemonStat = UIPokemonStats()

        withContext(Dispatchers.Default) {
            stats.forEach { entryStat ->
                val statIdentifier = entryStat.stat.name
                val pokemonStat = PokemonStat.entries.find { it.identifier == statIdentifier }
                    ?: PokemonStat.UNDEFINED
                when (pokemonStat) {
                    PokemonStat.HEALTH_POINTS -> uiPokemonStat.hpStat.statValue = entryStat.baseStat
                    PokemonStat.ATTACK -> uiPokemonStat.attackStat.statValue = entryStat.baseStat
                    PokemonStat.DEFENSE -> uiPokemonStat.defenseStat.statValue = entryStat.baseStat
                    PokemonStat.SPECIAL_ATTACK -> uiPokemonStat.spAttackStat.statValue = entryStat.baseStat
                    PokemonStat.SPECIAL_DEFENSE -> uiPokemonStat.spDefenseStat.statValue = entryStat.baseStat
                    PokemonStat.SPEED -> uiPokemonStat.speedStat.statValue = entryStat.baseStat
                    PokemonStat.UNDEFINED -> { /** No action required **/ }
                }
            }
        }

        return uiPokemonStat
    }

    private suspend fun mapDisplayId(id: Int) =
        withContext(Dispatchers.Default) {
            return@withContext if (id < 10) {
                "#00$id"
            } else if (id < 100) {
                "#0${id}"
            } else {
                "#${id}"
            }
        }
}

data class UIPokemonEntry(
    val id: Int,
    val displayId: String,
    val name: String,
    val spriteUrl: String,
    val types: List<PokemonType>,
    val stats: UIPokemonStats = UIPokemonStats()
)

data class UIPokemonStats(
    val hpStat: PokemonStat = PokemonStat.HEALTH_POINTS,
    val attackStat: PokemonStat = PokemonStat.ATTACK,
    val defenseStat: PokemonStat = PokemonStat.DEFENSE,
    val spAttackStat: PokemonStat = PokemonStat.SPECIAL_ATTACK,
    val spDefenseStat: PokemonStat = PokemonStat.SPECIAL_DEFENSE,
    val speedStat: PokemonStat = PokemonStat.SPEED
)