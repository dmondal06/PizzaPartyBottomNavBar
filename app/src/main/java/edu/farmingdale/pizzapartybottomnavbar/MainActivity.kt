package edu.farmingdale.pizzapartybottomnavbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.farmingdale.pizzapartybottomnavbar.ui.theme.PizzaPartyBottomNavBarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaPartyBottomNavBarTheme {
                val navController: NavHostController = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = false,
                    drawerContent = {

                        if (drawerState.isOpen) {
                            DrawerNav(
                                navController = navController,
                                drawerState = drawerState,
                                modifier = Modifier
                                    .width(250.dp)
                            )
                        } else {

                            Box(modifier = Modifier.width(0.dp))
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Pizza Party & GPA Calculator App") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Open Drawer")
                                    }
                                }
                            )
                        },
                        bottomBar = {
                            if (buttonsVisible) {
                                BottomBar(
                                    navController = navController,
                                    state = buttonsVisible,
                                    modifier = Modifier
                                )
                            }
                        }
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .padding(paddingValues)
                                .padding(start = 0.dp)
                        ) {
                            NavigationGraph(
                                navController = navController,
                                onBottomBarVisibilityChanged = { isVisible ->
                                    buttonsVisible = isVisible
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
