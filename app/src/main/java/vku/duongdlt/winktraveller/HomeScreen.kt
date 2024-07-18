package vku.duongdlt.winktraveller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.component.ChildLayout
import vku.duongdlt.winktraveller.component.LoadItemAfterSafeCast
import vku.duongdlt.winktraveller.component.TitleWithViewAllItem
import vku.duongdlt.winktraveller.component.VerticalScrollLayout
import vku.duongdlt.winktraveller.component.destinationSmallItem
import vku.duongdlt.winktraveller.component.homeHeader
import vku.duongdlt.winktraveller.component.loadCategoryItems
import vku.duongdlt.winktraveller.component.loadDestinationLargeItems
import vku.duongdlt.winktraveller.data.FakeCategories
import vku.duongdlt.winktraveller.data.FakeDestinations
import vku.duongdlt.winktraveller.data.FakeDestinations.destinations
import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.navigation.Screen

enum class HomeScreenContents{
    HEADER_SECTION,
    CATEGORY_VIEW_ALL,
    CATEGORY_SECTION,
    DESTINATION_LARGE_SECTION,
    DESTINATION_VIEW_ALL,
    DESTINATION_SMALL_SECTION,
}

@Composable
fun HomeScreen(routeState: MutableState<Route>){
    Surface(modifier = Modifier.fillMaxWidth().padding(bottom = BOTTOM_NAV_SPACE)) {
        var destinations by remember { mutableStateOf(FakeDestinations.destinations) }
        VerticalScrollLayout(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top=31.dp),
            ChildLayout(
                contentType = HomeScreenContents.HEADER_SECTION.name,
                content = {
                    homeHeader()
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.CATEGORY_VIEW_ALL.name,
                content = {
                   TitleWithViewAllItem("Category", "View All", R.drawable.arrow_forward)
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.CATEGORY_SECTION.name,
                content = {
                    loadCategoryItems(FakeCategories.categories) { category ->
                        when(category.title)  {
                            "All" -> destinations = FakeDestinations.destinations
                            else -> destinations = arrayListOf<Destination>().apply {
                                addAll(FakeDestinations.destinations.filter { it.category == category })
                            }
                        }
                    }
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.DESTINATION_LARGE_SECTION.name,
                content = {
                    loadDestinationLargeItems(destinations) {
                        routeState.value = Route(
                            screen = Screen.DetailScreen(it),
                            prev = Screen.HomeScreen
                        )
                    }
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.DESTINATION_VIEW_ALL.name,
                content = {
                    TitleWithViewAllItem("Popular Destination", "View All", R.drawable.arrow_forward)
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.DESTINATION_SMALL_SECTION.name,
                items = FakeDestinations.destinations,
                content = { item ->
                    LoadItemAfterSafeCast<Destination>(item) {
                        destinationSmallItem(it) {
                            routeState.value = Route(
                                screen = Screen.DetailScreen(it),
                                prev = Screen.HomeScreen
                            )
                        }
                    }
                }
            )
        )
    }
}