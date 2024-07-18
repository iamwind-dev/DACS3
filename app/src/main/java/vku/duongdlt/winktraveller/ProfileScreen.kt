package vku.duongdlt.winktraveller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import vku.duongdlt.winktraveller.component.ChildLayout
import vku.duongdlt.winktraveller.component.InformationCard
import vku.duongdlt.winktraveller.component.VerticalScrollLayout
import vku.duongdlt.winktraveller.component.homeHeader
import vku.duongdlt.winktraveller.component.profileHeader
import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE

import vku.duongdlt.winktraveller.navigation.Route
enum class ProfileScreenContents{
    HEADER_SECTION,
    INFORMATION_USER,
    CATEGORY_SECTION,
    DESTINATION_LARGE_SECTION,
    DESTINATION_VIEW_ALL,
    DESTINATION_SMALL_SECTION,
}
@Composable
fun ProfileScreen(routeState: MutableState<Route>){
    Surface(modifier = Modifier.fillMaxWidth().padding(bottom = BOTTOM_NAV_SPACE)) {
        VerticalScrollLayout(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top=31.dp),
            ChildLayout(
                contentType = ProfileScreenContents.HEADER_SECTION.name,
                content = {
                    profileHeader()
                }
            ),
            ChildLayout(
                contentType = ProfileScreenContents.INFORMATION_USER.name,
                content = {
                    InformationCard()
                }
            )
        )
//        Text(
//            modifier = Modifier.wrapContentSize(Alignment.Center),
//            text = "Profile",
//            color = colorResource(R.color.textColor),
//            style = MaterialTheme.typography.titleLarge
//        )
    }
}