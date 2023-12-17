package com.polstat.polistis.ui.screen

import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon: ImageVector
) {
    object Profil : BottomBarScreen(
        route = "profil",
        title = "Profil",
        icon = Default.Person
    )

    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Default.Home
    )

    object Riwayat : BottomBarScreen(
        route = "riwayat",
        title = "Riwayat",
        icon = Default.DateRange
    )

    object Pengajuan : BottomBarScreen(
        route = "pengajuan",
        title = "Pengajuan",
        icon = Default.AddCircle
    )

    object ProfilAdmin : BottomBarScreen(
        route = "profiladmin",
        title = "Profil",
        icon = Default.Person
    )

    object HomeDokter : BottomBarScreen(
        route = "homedokter",
        title = "Home",
        icon = Default.Home
    )

    object ProfilDokter : BottomBarScreen(
        route = "profildokter",
        title = "Profil",
        icon = Default.Person
    )

    object RiwayatDokter : BottomBarScreen(
        route = "riwayatDokter",
        title = "Riwayat",
        icon = Default.DateRange
    )

}
