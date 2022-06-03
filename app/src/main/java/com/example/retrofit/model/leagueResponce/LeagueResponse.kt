package com.example.retrofit.model.leagueResponce

data class LeagueResponse(
    val abbr: String,
    val id: String,
    val logos: Logos,
    val name: String,
    val slug: String
) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as LeagueResponse
        if (abbr != other.abbr) return false
        if (name != other.name) return false
        if (logos != other.logos) return false
        if (id != other.id) return false
        if (slug != other.slug) return false

        return true
    }

    override fun hashCode(): Int {
        var result = abbr.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + logos.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + slug.hashCode()
        return result
    }
}