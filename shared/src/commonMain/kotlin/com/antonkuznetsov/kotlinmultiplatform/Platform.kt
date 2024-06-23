package com.antonkuznetsov.kotlinmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform