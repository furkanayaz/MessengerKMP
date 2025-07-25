package org.ayaz.messenger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform