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
import com.example.ucp2.ui.navigation.DestinasiDetail
import com.example.ucp2.ui.navigation.DestinasiDetail.KODE
import com.example.ucp2.ui.navigation.DestinasiHomeDsn
import com.example.ucp2.ui.navigation.DestinasiHomeMK
import com.example.ucp2.ui.navigation.DestinasiUpdate
import com.example.ucp2.ui.view.Dosen.DestinasiInsert
import com.example.ucp2.ui.view.Dosen.HomeDsnView
import com.example.ucp2.ui.view.Dosen.InsertDsnView
import com.example.ucp2.ui.view.MataKuliah.DetailMataKuliahView
import com.example.ucp2.ui.view.MataKuliah.HomeMKView
import com.example.ucp2.ui.view.MataKuliah.InsertMKView
import com.example.ucp2.ui.view.MataKuliah.UpdateMKView
import com.example.ucp2.ui.viewmodel.matakuliahvm.DetailMataKuliahViewModel

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeMK.route,
        modifier = modifier
    ) {
        composable(
            route = DestinasiHomeMK.route
        ) {
            HomeMKView(
                onDetailClick = {kode ->
                    navController.navigate("${DestinasiDetail.route}/$KODE")
                    println(
                        "PengelolaHalaman: kode = $KODE"
                    )
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
            nim?.let { nidn ->
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