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
import com.example.ucp2.ui.navigation.DestinasiHomeDsn
import com.example.ucp2.ui.view.Dosen.DestinasiInsert
import com.example.ucp2.ui.view.Dosen.HomeDsnView
import com.example.ucp2.ui.view.Dosen.InsertDsnView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeDsn.route,
        modifier = modifier
    ) {
        composable(
            route = DestinasiHomeDsn.route
        ) {
            HomeDsnView(
                onAddDsn = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsert.route
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

//        composable(
//            DestinasiDetail.routesWithArg,
//            arguments = listOf(
//                navArgument(DestinasiDetail.NIM) {
//                    type = NavType.StringType
//                }
//            )
//        ) {
//            val nim = it.arguments?.getString(DestinasiDetail.NIM)
//            nim?.let { nim ->
//                DetailMhsView(
//                    onBack = {
//                        navController.popBackStack()
//                    },
//                    onEditClick = {
//                        navController.navigate("${DestinasiUpdate.route}/$it")
//                    },
//                    modifier = modifier,
//                    onDeleteClick = {
//                        navController.popBackStack()
//                    }
//                )
//            }
//        }
//
//        composable(
//            DestinasiUpdate.routesWithArg,
//            arguments = listOf(
//                navArgument(DestinasiUpdate.NIM) {
//                    type = NavType.StringType
//                }
//            )
//        ) {
//            UpdateMhsView(
//                onBack = {
//                    navController.popBackStack()
//                },
//                onNavigate = {
//                    navController.popBackStack()
//                },
//                modifier = modifier
//            )
//        }
    }
}