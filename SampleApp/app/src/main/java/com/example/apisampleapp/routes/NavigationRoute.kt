package com.example.apisampleapp.routes

/**
 * Enum class for defining the possible routes within the compose
 * navigation flow.
 */
enum class NavigationRoute(val path: String) {
    HOME("/Home"),
    DETAILS("/Details")
}