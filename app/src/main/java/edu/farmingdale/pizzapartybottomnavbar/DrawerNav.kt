package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun DrawerNav(
    navController: NavHostController,
    drawerState: DrawerState,
    modifier: Modifier = Modifier
) {
    val screens = listOf(
        DrawerNavigationItems.PizzaScreen,
        DrawerNavigationItems.GpaAppScreen,
        DrawerNavigationItems.Screen3
    )

    val scope = rememberCoroutineScope()
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    ModalDrawerSheet(
        modifier = modifier
            .fillMaxSize()
            .width(250.dp)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            screens.forEach { screen ->

                val isSelected = currentRoute == screen.route

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSelected) Color.LightGray else Color.Transparent)
                        .clickable {
                            // Navigates to the selected route and close the drawer
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }

                            scope.launch {
                                drawerState.close()
                            }
                        }
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = screen.icon!!,
                        contentDescription = screen.title,
                        tint = if (isSelected) Color.Black else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = screen.title ?: "",
                        color = if (isSelected) Color.Black else Color.DarkGray
                    )
                }
            }
        }
    }
}
