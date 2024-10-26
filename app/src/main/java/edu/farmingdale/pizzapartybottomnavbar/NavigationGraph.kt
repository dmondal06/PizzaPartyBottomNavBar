package edu.farmingdale.pizzapartybottomnavbar



import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.farmingdale.pizzapartybottomnavbar.ui.SplashScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    onBottomBarVisibilityChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationItems.Welcome.route,
        modifier = modifier
    ) {
        composable(BottomNavigationItems.Welcome.route) {
            onBottomBarVisibilityChanged(false)
            SplashScreen(navController = navController)
        }
        composable(BottomNavigationItems.PizzaScreen.route) {
            onBottomBarVisibilityChanged(true)
            PizzaPartyScreen()
        }
        composable(BottomNavigationItems.GpaAppScreen.route) {
            onBottomBarVisibilityChanged(true)
            GpaAppScreen()
        }
        composable(BottomNavigationItems.Screen3.route) {
            onBottomBarVisibilityChanged(true)
            Screen3()
        }
    }
}


// ToDo 8: Done This is the homework:
// add a drawer navigation as described in drawable drawermenu.png
// Improve the design and integration of the app for 5 extra credit points.