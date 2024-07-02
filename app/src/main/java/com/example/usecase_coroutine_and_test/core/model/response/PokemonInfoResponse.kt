package com.example.usecase_coroutine_and_test.core.model.response

data class PokemonInfoResponse(
    val abilities: List<Ability>,
    val base_experience: Int, // 267
    val cries: Cries,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int, // 17
    val held_items: List<Any>,
    val id: Int, // 6
    val is_default: Boolean, // true
    val location_area_encounters: String, // https://pokeapi.co/api/v2/pokemon/6/encounters
    val moves: List<Move>,
    val name: String, // charizard
    val order: Int, // 7
    val past_abilities: List<Any>,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int // 905
) {
    data class Ability(
        val ability: Ability,
        val is_hidden: Boolean, // false
        val slot: Int // 1
    ) {
        data class Ability(
            val name: String, // blaze
            val url: String // https://pokeapi.co/api/v2/ability/66/
        )
    }

    data class Cries(
        val latest: String, // https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/6.ogg
        val legacy: String // https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/legacy/6.ogg
    )

    data class Form(
        val name: String, // charizard
        val url: String // https://pokeapi.co/api/v2/pokemon_form/6/
    )

    data class GameIndice(
        val game_index: Int, // 180
        val version: Version
    ) {
        data class Version(
            val name: String, // red
            val url: String // https://pokeapi.co/api/v2/version/1/
        )
    }

    data class Move(
        val move: Move,
        val version_group_details: List<VersionGroupDetail>
    ) {
        data class Move(
            val name: String, // mega_punch
            val url: String // https://pokeapi.co/api/v2/move/5/
        )

        data class VersionGroupDetail(
            val level_learned_at: Int, // 0
            val move_learn_method: MoveLearnMethod,
            val version_group: VersionGroup
        ) {
            data class MoveLearnMethod(
                val name: String, // machine
                val url: String // https://pokeapi.co/api/v2/move_learn_method/4/
            )

            data class VersionGroup(
                val name: String, // red_blue
                val url: String // https://pokeapi.co/api/v2/version_group/1/
            )
        }
    }

    data class Species(
        val name: String, // charizard
        val url: String // https://pokeapi.co/api/v2/pokemon_species/6/
    )

    data class Sprites(
        val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png
        val back_female: Any, // null
        val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/6.png
        val back_shiny_female: Any, // null
        val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png
        val front_female: Any, // null
        val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/6.png
        val front_shiny_female: Any, // null
        val other: Other,
        val versions: Versions
    ) {
        data class Other(
            val dream_world: DreamWorld,
            val home: Home,
            val official_artwork: OfficialArtwork,
            val showdown: Showdown
        ) {
            data class DreamWorld(
                val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream_world/6.svg
                val front_female: Any // null
            )

            data class Home(
                val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/6.png
                val front_female: Any, // null
                val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/6.png
                val front_shiny_female: Any // null
            )

            data class OfficialArtwork(
                val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official_artwork/6.png
                val front_shiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official_artwork/shiny/6.png
            )

            data class Showdown(
                val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/back/6.gif
                val back_female: Any, // null
                val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/back/shiny/6.gif
                val back_shiny_female: Any, // null
                val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/6.gif
                val front_female: Any, // null
                val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/shiny/6.gif
                val front_shiny_female: Any // null
            )
        }

        data class Versions(
            val generation_i: GenerationI,
            val generation_ii: GenerationIi,
            val generation_iii: GenerationIii,
            val generation_iv: GenerationIv,
            val generation_v: GenerationV,
            val generation_vi: GenerationVi,
            val generation_vii: GenerationVii,
            val generation_viii: GenerationViii
        ) {
            data class GenerationI(
                val red_blue: RedBlue,
                val yellow: Yellow
            ) {
                data class RedBlue(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/red_blue/back/6.png
                    val back_gray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/red_blue/back/gray/6.png
                    val back_transparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/red_blue/transparent/back/6.png
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/red_blue/6.png
                    val front_gray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/red_blue/gray/6.png
                    val front_transparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/red_blue/transparent/6.png
                )

                data class Yellow(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/yellow/back/6.png
                    val back_gray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/yellow/back/gray/6.png
                    val back_transparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/yellow/transparent/back/6.png
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/yellow/6.png
                    val front_gray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/yellow/gray/6.png
                    val front_transparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_i/yellow/transparent/6.png
                )
            }

            data class GenerationIi(
                val crystal: Crystal,
                val gold: Gold,
                val silver: Silver
            ) {
                data class Crystal(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/crystal/back/6.png
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/crystal/back/shiny/6.png
                    val back_shiny_transparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/crystal/transparent/back/shiny/6.png
                    val back_transparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/crystal/transparent/back/6.png
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/crystal/6.png
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/crystal/shiny/6.png
                    val front_shiny_transparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/crystal/transparent/shiny/6.png
                    val front_transparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/crystal/transparent/6.png
                )

                data class Gold(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/gold/back/6.png
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/gold/back/shiny/6.png
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/gold/6.png
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/gold/shiny/6.png
                    val front_transparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/gold/transparent/6.png
                )

                data class Silver(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/silver/back/6.png
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/silver/back/shiny/6.png
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/silver/6.png
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/silver/shiny/6.png
                    val front_transparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_ii/silver/transparent/6.png
                )
            }

            data class GenerationIii(
                val emerald: Emerald,
                val firered_leafgreen: FireredLeafgreen,
                val ruby_sapphire: RubySapphire
            ) {
                data class Emerald(
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/emerald/6.png
                    val front_shiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/emerald/shiny/6.png
                )

                data class FireredLeafgreen(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/firered_leafgreen/back/6.png
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/firered_leafgreen/back/shiny/6.png
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/firered_leafgreen/6.png
                    val front_shiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/firered_leafgreen/shiny/6.png
                )

                data class RubySapphire(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/ruby_sapphire/back/6.png
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/ruby_sapphire/back/shiny/6.png
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/ruby_sapphire/6.png
                    val front_shiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iii/ruby_sapphire/shiny/6.png
                )
            }

            data class GenerationIv(
                val diamond_pearl: DiamondPearl,
                val heartgold_soulsilver: HeartgoldSoulsilver,
                val platinum: Platinum
            ) {
                data class DiamondPearl(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/diamond_pearl/back/6.png
                    val back_female: Any, // null
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/diamond_pearl/back/shiny/6.png
                    val back_shiny_female: Any, // null
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/diamond_pearl/6.png
                    val front_female: Any, // null
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/diamond_pearl/shiny/6.png
                    val front_shiny_female: Any // null
                )

                data class HeartgoldSoulsilver(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/heartgold_soulsilver/back/6.png
                    val back_female: Any, // null
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/heartgold_soulsilver/back/shiny/6.png
                    val back_shiny_female: Any, // null
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/heartgold_soulsilver/6.png
                    val front_female: Any, // null
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/heartgold_soulsilver/shiny/6.png
                    val front_shiny_female: Any // null
                )

                data class Platinum(
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/platinum/back/6.png
                    val back_female: Any, // null
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/platinum/back/shiny/6.png
                    val back_shiny_female: Any, // null
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/platinum/6.png
                    val front_female: Any, // null
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_iv/platinum/shiny/6.png
                    val front_shiny_female: Any // null
                )
            }

            data class GenerationV(
                val black_white: BlackWhite
            ) {
                data class BlackWhite(
                    val animated: Animated,
                    val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_v/black_white/back/6.png
                    val back_female: Any, // null
                    val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_v/black_white/back/shiny/6.png
                    val back_shiny_female: Any, // null
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_v/black_white/6.png
                    val front_female: Any, // null
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_v/black_white/shiny/6.png
                    val front_shiny_female: Any // null
                ) {
                    data class Animated(
                        val back_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_v/black_white/animated/back/6.gif
                        val back_female: Any, // null
                        val back_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_v/black_white/animated/back/shiny/6.gif
                        val back_shiny_female: Any, // null
                        val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_v/black_white/animated/6.gif
                        val front_female: Any, // null
                        val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_v/black_white/animated/shiny/6.gif
                        val front_shiny_female: Any // null
                    )
                }
            }

            data class GenerationVi(
                val omegaruby_alphasapphire: OmegarubyAlphasapphire,
                val x_y: XY
            ) {
                data class OmegarubyAlphasapphire(
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_vi/omegaruby_alphasapphire/6.png
                    val front_female: Any, // null
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_vi/omegaruby_alphasapphire/shiny/6.png
                    val front_shiny_female: Any // null
                )

                data class XY(
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_vi/x_y/6.png
                    val front_female: Any, // null
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_vi/x_y/shiny/6.png
                    val front_shiny_female: Any // null
                )
            }

            data class GenerationVii(
                val icons: Icons,
                val ultra_sun_ultra_moon: UltraSunUltraMoon
            ) {
                data class Icons(
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_vii/icons/6.png
                    val front_female: Any // null
                )

                data class UltraSunUltraMoon(
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_vii/ultra_sun_ultra_moon/6.png
                    val front_female: Any, // null
                    val front_shiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_vii/ultra_sun_ultra_moon/shiny/6.png
                    val front_shiny_female: Any // null
                )
            }

            data class GenerationViii(
                val icons: Icons
            ) {
                data class Icons(
                    val front_default: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation_viii/icons/6.png
                    val front_female: Any // null
                )
            }
        }
    }

    data class Stat(
        val base_stat: Int, // 78
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