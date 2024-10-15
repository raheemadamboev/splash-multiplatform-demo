package xyz.teamgravity.splashmultiplatformdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform