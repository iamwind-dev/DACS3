package vku.duongdlt.winktraveller

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.component.PrimaryButton
import vku.duongdlt.winktraveller.component.TitleWithReview
import vku.duongdlt.winktraveller.component.destinationDetailHeader
import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.util.ImageItem

@Composable
fun DetailScreen(routeState: MutableState<Route>, destination: Destination) {
    val rememberThumbnail = remember { mutableStateOf(destination.thumbnail) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())

    ) {
        topSection(routeState, destination, rememberThumbnail)
        contentSection(destination) {
            rememberThumbnail.value = it
        }
        PrimaryButton(
            "Book Now",
            PaddingValues(start = 25.dp, top = 36.dp, end = 25.dp, bottom = 36.dp)
        )
    }
}

@Composable
fun topSection(routeState: MutableState<Route>, destination: Destination, thumbnail: MutableState<String>) {
    Box(
        modifier = Modifier.fillMaxWidth().height(350.dp)
    ) {
       ImageItem(thumbnail.value)
        destinationDetailHeader(routeState,destination)
    }
}

@Composable
fun contentSection(destination: Destination, onImageClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = -40.dp)
            .background(
                color = colorResource(id=R.color.white),
                shape = RoundedCornerShape(26.dp, 26.dp, 0.dp, 0.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 25.dp, top = 36.dp, end = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = destination.title,
                    color = colorResource(id=R.color.textColor),
                    style = MaterialTheme.typography.titleLarge
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id=R.drawable.ci_location),
                        contentDescription = null,
                        tint = colorResource(id=R.color.primaryColor)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = destination.location,
                        color = colorResource(id=R.color.primaryColor),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
            Column(
                modifier = Modifier.padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = destination.price,
                    color = colorResource(id=R.color.textColor),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = TextUnit(24f, TextUnitType.Sp)
                )
                Text(
                    text = "/${destination.type}",
                    color = colorResource(id=R.color.secondTextColor),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Text(
            modifier = Modifier.padding(start = 25.dp, top = 18.dp, end = 25.dp),
            text = destination.description,
            color = colorResource(id=R.color.secondTextColor),
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify
        )

        TitleWithReview("Preview", "4.8", R.drawable.star)

        LazyRow(
            modifier = Modifier.padding(start = 25.dp, top = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(items = destination.image) { index, item ->
                ImageItem(
                    data = item,
                    modifier = Modifier.size(90.dp).clickable {
                        onImageClicked.invoke(item)
                    }
                )
            }
        }
    }
}