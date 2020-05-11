data class BuildConfigFields(val type : String, val name : String, val value : String)

object BuildConfigField {
    val baseUrlField = BuildConfigFields("String", "BASE_URL", "https://quran.com/api/api/v3/")
}