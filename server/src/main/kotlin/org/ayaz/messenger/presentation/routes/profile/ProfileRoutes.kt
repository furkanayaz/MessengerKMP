package org.ayaz.messenger.presentation.routes.profile

import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.ayaz.messenger.presentation.util.CallUtil.getSingleFile

fun Route.profileRoutes() {
    post(ProfileEndpoints.ADD_IMAGE) {
        val imageFile = call.getSingleFile()
    }
}