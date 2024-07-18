package vku.duongdlt.winktraveller.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import vku.duongdlt.winktraveller.R


@Composable
fun PrimaryButton(title: String, paddingValues: PaddingValues = PaddingValues()){
    Box(
        Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .background(color = colorResource(R.color.primaryColor), shape = RoundedCornerShape(size = 8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = title,
            color = colorResource(R.color.white),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}