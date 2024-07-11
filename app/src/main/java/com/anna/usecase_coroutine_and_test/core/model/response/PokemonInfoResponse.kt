package com.anna.usecase_coroutine_and_test.core.model.response

import com.google.gson.annotations.SerializedName

data class PokemonInfoResponse(
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val cries: Cries,
    val forms: List<Form>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>,
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<Any>,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    @SerializedName("past_abilities")
    val pastAbilities: List<Any>,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int // 905
) {
    data class Ability(
        val ability: Ability,
        @SerializedName("is_hidden")
        val isHidden: Boolean,
        val slot: Int
    ) {
        data class Ability(
            val name: String,
            val url: String
        )
    }

    data class Cries(
        val latest: String,
        val legacy: String
    )

    data class Form(
        val name: String,
        val url: String
    )

    data class GameIndice(
        @SerializedName("game_index")
        val gameIndex: Int,
        val version: Version
    ) {
        data class Version(
            val name: String,
            val url: String
        )
    }

    data class Move(
        val move: Move,
        @SerializedName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail>
    ) {
        data class Move(
            val name: String,
            val url: String
        )

        data class VersionGroupDetail(
            @SerializedName("level_learned_at")
            val levelLearnedAt: Int, // 0
            @SerializedName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod,
            @SerializedName("version_group")
            val versionGroup: VersionGroup
        ) {
            data class MoveLearnMethod(
                val name: String,
                val url: String
            )

            data class VersionGroup(
                val name: String,
                val url: String
            )
        }
    }

    data class Species(
        val name: String,
        val url: String
    )

    data class Sprites(
        @SerializedName("back_default")
        val backDefault: String,
        @SerializedName("back_female")
        val backFemale: Any?,
        @SerializedName("back_shiny")
        val backShiny: String,
        @SerializedName("back_shiny_female")
        val backShinyFemale: Any?,
        @SerializedName("front_default")
        val frontDefault: String,
        @SerializedName("front_female")
        val frontFemale: Any?,
        @SerializedName("front_shiny")
        val frontShiny: String,
        @SerializedName("front_shiny_female")
        val frontShinyFemale: Any?,
        val other: Other,
        val versions: Versions
    ) {
        data class Other(
            @SerializedName("dream_world")
            val dreamWorld: DreamWorld,
            val home: Home,
            @SerializedName("official_artwork")
            val officialArtwork: OfficialArtwork,
            val showdown: Showdown
        ) {
            data class DreamWorld(
                @SerializedName("front_default")
                val frontDefault: String,
                @SerializedName("front_female")
                val frontFemale: Any?
            )

            data class Home(
                @SerializedName("front_default")
                val frontDefault: String,
                @SerializedName("front_female")
                val frontFemale: Any?,
                @SerializedName("front_shiny")
                val frontShiny: String,
                @SerializedName("front_shiny_female")
                val frontShinyFemale: Any?
            )

            data class OfficialArtwork(
                @SerializedName("front_default")
                val frontDefault: String,
                @SerializedName("front_shiny")
                val frontShiny: String
            )

            data class Showdown(
                @SerializedName("back_default")
                val backDefault: String,
                @SerializedName("back_female")
                val backFemale: Any?,
                @SerializedName("back_shiny")
                val backShiny: String,
                @SerializedName("back_shiny_female")
                val backShinyFemale: Any?,
                @SerializedName("front_default")
                val frontDefault: String,
                @SerializedName("front_female")
                val frontFemale: Any?,
                @SerializedName("front_shiny")
                val frontShiny: String,
                @SerializedName("front_shiny_female")
                val frontShinyFemale: Any?
            )
        }

        data class Versions(
            @SerializedName("generation_i")
            val generationI: GenerationI,
            @SerializedName("generation_ii")
            val generationIi: GenerationIi,
            @SerializedName("generation_iii")
            val generationIii: GenerationIii,
            @SerializedName("generation_iv")
            val generationIv: GenerationIv,
            @SerializedName("generation_v")
            val generationV: GenerationV,
            @SerializedName("generation_vi")
            val generationVi: GenerationVi,
            @SerializedName("generation_vii")
            val generationVii: GenerationVii,
            @SerializedName("generation_viii")
            val generationViii: GenerationViii
        ) {
            data class GenerationI(
                @SerializedName("red_blue")
                val redBlue: RedBlue,
                val yellow: Yellow
            ) {
                data class RedBlue(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_gray")
                    val backGray: String,
                    @SerializedName("back_transparent")
                    val backTransparent: String,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_gray")
                    val frontGray: String,
                    @SerializedName("front_transparent")
                    val frontTransparent: String
                )

                data class Yellow(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_gray")
                    val backGray: String,
                    @SerializedName("back_transparent")
                    val backTransparent: String,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_gray")
                    val frontGray: String,
                    @SerializedName("front_transparent")
                    val frontTransparent: String
                )
            }

            data class GenerationIi(
                val crystal: Crystal,
                val gold: Gold,
                val silver: Silver
            ) {
                data class Crystal(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("back_shiny_transparent")
                    val backShinyTransparent: String,
                    @SerializedName("back_transparent")
                    val backTransparent: String,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_shiny_transparent")
                    val frontShinyTransparent: String,
                    @SerializedName("front_transparent")
                    val frontTransparent: String
                )

                data class Gold(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_transparent")
                    val frontTransparent: String
                )

                data class Silver(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_transparent")
                    val frontTransparent: String
                )
            }

            data class GenerationIii(
                val emerald: Emerald,
                @SerializedName("firered_leafgreen")
                val fireredLeafgreen: FireredLeafgreen,
                @SerializedName("ruby_sapphire")
                val rubySapphire: RubySapphire
            ) {
                data class Emerald(
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_shiny")
                    val frontShiny: String
                )

                data class FireredLeafgreen(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_shiny")
                    val frontShiny: String
                )

                data class RubySapphire(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_shiny")
                    val frontShiny: String
                )
            }

            data class GenerationIv(
                @SerializedName("diamond_pearl")
                val diamondPearl: DiamondPearl,
                @SerializedName("heartgold_soulsilver")
                val heartGoldSoulSilver: HeartGoldSoulSilver,
                val platinum: Platinum
            ) {
                data class DiamondPearl(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_female")
                    val backFemale: Any?,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any?,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_female")
                    val frontFemale: Any?,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any?
                )

                data class HeartGoldSoulSilver(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_female")
                    val backFemale: Any?,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any?,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_female")
                    val frontFemale: Any?,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any?
                )

                data class Platinum(
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_female")
                    val backFemale: Any?,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any?,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_female")
                    val frontFemale: Any?,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any?
                )
            }

            data class GenerationV(
                @SerializedName("black_white")
                val blackWhite: BlackWhite
            ) {
                data class BlackWhite(
                    val animated: Animated,
                    @SerializedName("back_default")
                    val backDefault: String,
                    @SerializedName("back_female")
                    val backFemale: Any?,
                    @SerializedName("back_shiny")
                    val backShiny: String,
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any?,
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_female")
                    val frontFemale: Any?,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any?
                ) {
                    data class Animated(
                        @SerializedName("back_default")
                        val backDefault: String,
                        @SerializedName("back_female")
                        val backFemale: Any?,
                        @SerializedName("back_shiny")
                        val backShiny: String,
                        @SerializedName("back_shiny_female")
                        val backShinyFemale: Any?,
                        @SerializedName("front_default")
                        val frontDefault: String,
                        @SerializedName("front_female")
                        val frontFemale: Any?,
                        @SerializedName("front_shiny")
                        val frontShiny: String,
                        @SerializedName("front_shiny_female")
                        val frontShinyFemale: Any?
                    )
                }
            }

            data class GenerationVi(
                @SerializedName("omegaruby_alphasapphire")
                val omegaRubyAlphaSapphire: OmegaRubyAlphaSapphire,
                @SerializedName("x_y")
                val xy: XY
            ) {
                data class OmegaRubyAlphaSapphire(
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_female")
                    val frontFemale: Any?,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any?
                )

                data class XY(
                    @SerializedName("front_female")
                    val frontFemale: Any?,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any?,
                    @SerializedName("front_default")
                    val frontDefault: String
                )
            }

            data class GenerationVii(
                val icons: Icons,
                @SerializedName("ultra_sun_ultra_moon")
                val ultraSunUltraMoon: UltraSunUltraMoon
            ) {
                data class Icons(
                    @SerializedName("frontDefault")
                    val frontDefault: String,
                    @SerializedName("front_female")
                    val frontFemale: Any?
                )

                data class UltraSunUltraMoon(
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_female")
                    val frontFemale: Any?,
                    @SerializedName("front_shiny")
                    val frontShiny: String,
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any?
                )
            }

            data class GenerationViii(
                val icons: Icons
            ) {
                data class Icons(
                    @SerializedName("front_default")
                    val frontDefault: String,
                    @SerializedName("front_female")
                    val frontFemale: Any?
                )
            }
        }
    }

    data class Stat(
        @SerializedName("base_stat")
        val baseStat: Int, // 78
        val effort: Int, // 0
        val stat: Stat
    ) {
        data class Stat(
            val name: String, // hp
            val url: String // https://pokeapi.co/api/v2/stat/1/
        )
    }

    data class Type(
        val slot: Int, // 1
        val type: Type
    ) {
        data class Type(
            val name: String, // fire
            val url: String // https://pokeapi.co/api/v2/type/10/
        )
    }
}