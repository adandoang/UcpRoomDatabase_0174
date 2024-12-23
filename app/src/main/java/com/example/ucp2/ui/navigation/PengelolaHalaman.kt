package com.example.meet9.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Insert
import com.example.ucp2.ui.navigation.DestinasiDetail
import com.example.ucp2.ui.navigation.DestinasiHome
import com.example.ucp2.ui.navigation.DestinasiHomeDsn
import com.example.ucp2.ui.navigation.DestinasiHomeMK
import com.example.ucp2.ui.navigation.DestinasiInsert
import com.example.ucp2.ui.navigation.DestinasiInsertDsn
import com.example.ucp2.ui.navigation.DestinasiUpdate
import com.example.ucp2.ui.view.Dosen.HomeDsnView
import com.example.ucp2.ui.view.Dosen.InsertDsnView
import com.example.ucp2.ui.view.Home
import com.example.ucp2.ui.view.MataKuliah.DetailMataKuliahView
import com.example.ucp2.ui.view.MataKuliah.HomeMKView
import com.example.ucp2.ui.view.MataKuliah.InsertMKView
import com.example.ucp2.ui.view.MataKuliah.UpdateMKView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            Home (onButtonClickDsn = {
                navController.navigate(DestinasiHomeDsn.route)
            },
                onButtonClickMK = {
                    navController.navigate(DestinasiHomeMK.route)
                } )
        }
        composable(
            route = DestinasiHomeDsn.route
        ) {
            HomeDsnView(
                onAddDsn = {
                    navController.navigate(DestinasiInsertDsn.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsertDsn.route
        ) {
            InsertDsnView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiHomeMK.route
        ) {
            HomeMKView(
                onDetailClick = {kode ->
                    navController.navigate("${DestinasiDetail.route}/$kode")
                    println(
                        "PengelolaHalaman: kode = $kode"
                    )
                },
                onBack = {
                    navController.popBackStack()
                },
                onAddMK = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsert.route
        ) {
            InsertMKView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.KODE)
            nim?.let { nim ->
                DetailMataKuliahView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMKView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}