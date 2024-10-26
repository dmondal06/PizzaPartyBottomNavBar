package edu.farmingdale.pizzapartybottomnavbar



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector



sealed class DrawerNavigationItems(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object PizzaScreen : DrawerNavigationItems(
        route = "pizzaScreen",
        title = "Pizza Order",
        icon = Icons.Outlined.ShoppingCart
    )
    object GpaAppScreen : DrawerNavigationItems(
        route = "gpaAppScreen",
        title = "GPA App",
        icon = Icons.Filled.Info
    )
    object Screen3 : DrawerNavigationItems(
        route = "screen3",
        title = "Screen 3",
        icon = Icons.Filled.Person
    )

}
