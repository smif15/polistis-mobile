package com.polstat.polistis.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White

enum class PolistisScreen {
    Welcome,
    Login,
    Register,
    Info,
    Pendaftaran,
    DaftarPengajuan
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    beforeLoginViewModel: BeforeLoginViewModel = viewModel(factory = BeforeLoginViewModel.Factory)
) {
    val loggedInUser = beforeLoginViewModel.userState.collectAsState().value
    val uiState = beforeLoginViewModel.uiState.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val showBottomTopBar = when (navBackStackEntry?.destination?.route) {
        PolistisScreen.Login.name, PolistisScreen.Register.name, PolistisScreen.Welcome.name -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomTopBar) {
                if(loggedInUser.isPasien == true) {
                    BottomBar(navController = navController)
                } else if(loggedInUser.isAdmin == true) {
                    BottomBarAdmin(navController = navController)
                } else {
                    BottomBarDokter(navController = navController)
                }
            }
        },
        topBar = {
            if (showBottomTopBar) {
                CenterAlignedTopAppBar(title = {
                    Text(text = "polistis",
                        color = White,
                        style = MaterialTheme.typography.titleLarge)
                },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Purple
                    )
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination =
                if(loggedInUser.token == "") {
                    PolistisScreen.Welcome.name
                } else {
                    if(loggedInUser.isPasien == true) {
                        BottomBarScreen.Home.route
                    } else if (loggedInUser.isAdmin == true) {
                        BottomBarScreen.Pengajuan.route
                    } else {
                        BottomBarScreen.HomeDokter.route
                    }
                }

            ,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = BottomBarScreen.Home.route) {
                HomeScreen(
                    toFormDaftar = { navController.navigate(PolistisScreen.Pendaftaran.name) }
                )
            }
            composable(route = BottomBarScreen.Profil.route) {
                ProfilScreen(
                    token = loggedInUser.token,
                    onClickEditProfil = { navController.navigate(PolistisScreen.Info.name) },
                    onClickLogout = { navController.navigate(PolistisScreen.Welcome.name) }
                )
            }
            composable(route = BottomBarScreen.Riwayat.route) {
                RiwayatScreen(
                    token = loggedInUser.token
                )
            }
            composable(route = PolistisScreen.Welcome.name) {
                WelcomeScreen(
                    onLoginClick = { navController.navigate(PolistisScreen.Login.name)},
                    onRegisterClick = { navController.navigate(PolistisScreen.Register.name)}
                )
            }
            composable(route = PolistisScreen.Register.name) {
                RegisterScreen(
                    onSuccessRegister = { navController.navigate(PolistisScreen.Login.name) }
                )
            }
            composable(route = PolistisScreen.Login.name) {
                LoginScreen(
                    onLoginSuccess = { navController.navigate(BottomBarScreen.Home.route) }
                )
            }
            composable(route = PolistisScreen.Info.name) {
                FormProfilScreen(
                    token = loggedInUser.token,
                    backToProfil = { navController.navigate(BottomBarScreen.Profil.route)}
                )
            }
            composable(route = PolistisScreen.Pendaftaran.name) {
                FormDaftarScreen(
                    token = loggedInUser.token,
                    backToHome = { navController.navigate(BottomBarScreen.Home.route) }
                )
            }

            //Route Admin
            composable(route = PolistisScreen.DaftarPengajuan.name) {
                DaftarPengajuanScreen()
            }
            composable(route = BottomBarScreen.Pengajuan.route) {
                DaftarPengajuanScreen(
                    token = loggedInUser.token
                )
            }
            composable(route = BottomBarScreen.ProfilAdmin.route) {
                ProfilAdminScreen(
                    onClickLogout = { navController.navigate(PolistisScreen.Welcome.name) }
                )
            }

            //Route Dokter
            composable(route = BottomBarScreen.HomeDokter.route) {
                HomeDokterScreen(
                    token = loggedInUser.token,
                    navigateToCreate = { pengajuanId -> navController.navigate("formlaporan/$pengajuanId") }
                )
            }

            composable(route = "formlaporan/{pengajuanId}"){navBackStackEntry ->
                val pengajuanId = navBackStackEntry.arguments?.getString("pengajuanId")?.toLong()
                pengajuanId?.let { id ->

                    LaporanScr(
                        token = loggedInUser.token,
                        id = pengajuanId,
                        backToHome = { navController.navigate(BottomBarScreen.HomeDokter.route) }
                    )
                }
            }

            composable(route = BottomBarScreen.ProfilDokter.route) {
                ProfilAdminScreen(
                    onClickLogout = { navController.navigate(PolistisScreen.Welcome.name) }
                )
            }
            composable(route = BottomBarScreen.RiwayatDokter.route) {
                RiwayatDokterScreen(
                    token = loggedInUser.token
                )
            }
        }
    }


}