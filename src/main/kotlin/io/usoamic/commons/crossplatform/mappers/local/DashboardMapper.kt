package io.usoamic.commons.crossplatform.mappers.local

import io.usoamic.commons.crossplatform.models.repository.dashboard.DashboardEntity
import io.usoamic.commons.crossplatform.models.usecases.dashboard.DashboardModel

fun DashboardEntity.toItem(): DashboardModel {
    return DashboardModel(
        ethBalance = ethBalance,
        usoBalance = usoBalance,
        height = height,
        supply = supply
    )
}