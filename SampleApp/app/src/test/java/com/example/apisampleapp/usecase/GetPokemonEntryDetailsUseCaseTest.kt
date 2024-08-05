package com.example.apisampleapp.usecase

import com.example.apisampleapp.dao.PokemonDao
import com.example.apisampleapp.dao.entities.PokemonEntity
import com.example.apisampleapp.network.PokemonApiRepository
import com.example.apisampleapp.network.PokemonApiService
import com.example.apisampleapp.network.models.PokemonEntry
import com.example.apisampleapp.network.models.PokemonEntrySprites
import com.example.apisampleapp.network.models.PokemonEntrySpritesOfficial
import com.example.apisampleapp.network.models.PokemonEntrySpritesOthers
import com.example.apisampleapp.network.models.PokemonEntryStat
import com.example.apisampleapp.network.models.PokemonEntryStatDetails
import com.example.apisampleapp.network.models.PokemonEntryType
import com.example.apisampleapp.network.models.PokemonEntryTypeDetails
import com.example.apisampleapp.util.PokemonStat
import com.example.apisampleapp.util.PokemonType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetPokemonEntryDetailsUseCaseTest {

    private lateinit var useCase: GetPokemonEntryDetailsUseCase
    private lateinit var repository: PokemonApiRepository

    private val pokemonDao: PokemonDao = mock()
    private val pokemonApiService: PokemonApiService = mock()

    @Before
    fun setup() {
        repository = PokemonApiRepository(pokemonApiService)
        useCase = GetPokemonEntryDetailsUseCase(repository, pokemonDao)
    }

    @Test
    fun givenPokemonInDb_returnPokemonFromDb(): Unit = runTest {
        // Given
        val pokemonId = 1
        whenever(pokemonDao.entryExists(pokemonId)).thenReturn(true)
        whenever(pokemonDao.findById(pokemonId))
            .thenReturn(getTestPokemonEntity(pokemonId))

        // When
        val result = useCase.execute(pokemonId)

        // Then
        assertEquals(getTestUIPokemonEntry(pokemonId), result)
    }

    // -------------------------------------------------------------
    // Helper functions
    // -------------------------------------------------------------

    private fun getTestPokemonEntity(id: Int): PokemonEntity =
        PokemonEntity(
            id = id,
            displayId = "#001",
            name = "name",
            spriteUrl = "sample_url",
            type1 = 0,
            type2 = 1,
            baseHp = 10,
            baseAttack = 10,
            baseDefense = 10,
            baseSpAttack = 10,
            baseSpDefense = 10,
            baseSpeed = 10
        )

    private fun getTestUIPokemonEntry(id: Int) =
        UIPokemonEntry(
            id = id,
            displayId = "#001",
            name = "name",
            spriteUrl = "sample_url",
            types = listOf(PokemonType.NORMAL, PokemonType.FIGHTING),
            stats = UIPokemonStats(
                hpStat = PokemonStat.HEALTH_POINTS.apply { statValue = 10 },
                attackStat = PokemonStat.ATTACK.apply { statValue = 10 },
                defenseStat = PokemonStat.DEFENSE.apply { statValue = 10 },
                spAttackStat = PokemonStat.SPECIAL_ATTACK.apply { statValue = 10 },
                spDefenseStat = PokemonStat.SPECIAL_DEFENSE.apply { statValue = 10 },
                speedStat = PokemonStat.SPEED.apply { statValue = 10 }
            )
        )

    private fun getTestPokemonEntry(id: Int) =
        PokemonEntry(
            id = id,
            name = "name",
            sprites = PokemonEntrySprites(
                others = PokemonEntrySpritesOthers(
                    official = PokemonEntrySpritesOfficial(
                        url = "sample_url"
                    )
                )
            ),
            types = listOf(
                PokemonEntryType(
                    slot = 1,
                    type = PokemonEntryTypeDetails(
                        name = "normal"
                    )
                ),
                PokemonEntryType(
                    slot = 1,
                    type = PokemonEntryTypeDetails(
                        name = "fighting"
                    )
                )
            ),
            stats = listOf(
                PokemonEntryStat(
                    baseStat = 10,
                    stat = PokemonEntryStatDetails(
                        name = PokemonStat.HEALTH_POINTS.identifier
                    )
                ),
                PokemonEntryStat(
                    baseStat = 10,
                    stat = PokemonEntryStatDetails(
                        name = PokemonStat.ATTACK.identifier
                    )
                ),
                PokemonEntryStat(
                    baseStat = 10,
                    stat = PokemonEntryStatDetails(
                        name = PokemonStat.DEFENSE.identifier
                    )
                ),
                PokemonEntryStat(
                    baseStat = 10,
                    stat = PokemonEntryStatDetails(
                        name = PokemonStat.SPECIAL_ATTACK.identifier
                    )
                ),
                PokemonEntryStat(
                    baseStat = 10,
                    stat = PokemonEntryStatDetails(
                        name = PokemonStat.SPECIAL_DEFENSE.identifier
                    )
                ),
                PokemonEntryStat(
                    baseStat = 10,
                    stat = PokemonEntryStatDetails(
                        name = PokemonStat.SPEED.identifier
                    )
                )
            )
        )
}
