package com.polstat.polistis.ui.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White

@Composable
fun BottomBar(navController: NavController) {
    val screen = listOf(
        BottomBarScreen.Profil,
        BottomBarScreen.Home,
        BottomBarScreen.Riwayat
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Purple,
        modifier = Modifier.height(60.dp)
    ) {
        screen.forEach{ screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun BottomBarAdmin(navController: NavController) {
    val screen = listOf(
        BottomBarScreen.ProfilAdmin,
        BottomBarScreen.Pengajuan
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Purple,
        modifier = Modifier.height(60.dp)
    ) {
        screen.forEach{ screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun BottomBarDokter(navController: NavController) {
    val screen = listOf(
        BottomBarScreen.ProfilDokter,
        BottomBarScreen.HomeDokter,
        BottomBarScreen.RiwayatDokter
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Purple,
        modifier = Modifier.height(60.dp)
    ) {
        screen.forEach{ screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any{it.route == screen.route
        } == true,
        onClick = {
                  navController.navigate(screen.route) {
                      popUpTo(navController.graph.findStartDestination().id)
                      launchSingleTop = true
                  }
        }, icon = {
            Icon(imageVector = screen.icon, contentDescription = "Navigation Icon", tint = White)
        },
        label = {
            Text(text = screen.title,
                style = MaterialTheme.typography.labelSmall,
                color = White)
        })
}