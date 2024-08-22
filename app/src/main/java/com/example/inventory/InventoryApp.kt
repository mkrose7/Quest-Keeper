package com.example.inventory

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.R.string
import com.example.inventory.ui.navigation.InventoryNavHost
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Top level composable that represents screens for the application.
 */
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InventoryApp(
    navController: NavHostController = rememberNavController(),
    onIncrementPoints: () -> Unit
) {
    InventoryNavHost(navController = navController, onIncrementPoints = onIncrementPoints)
}

/**
 * App bar to display title and conditionally display the back navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size_small))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.spell_book), //credit https://www.flaticon.com/free-icon/spell-book_7860331?term=magic+book&page=1&position=2&origin=search&related_id=7860331
                    contentDescription = null
                )
                Text(
                    text = stringResource(string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(top = 15.dp)
                )
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Filled.ArrowBack,
                        contentDescription = stringResource(string.back_button)
                    )
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateTimeDisplay() {
    var currentDate by remember { mutableStateOf("") }
    var currentTime by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            val currentDateTime = LocalDateTime.now()
            val dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH)
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

            currentDate = currentDateTime.format(dateFormatter)
            currentTime = currentDateTime.format(timeFormatter)

            // Update every second (1000 milliseconds)
            delay(1000)
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = currentDate,
            style = MaterialTheme.typography.displayMedium // Adjust the font size for the date
        )
        Text(
            text = currentTime,
            style = MaterialTheme.typography.displaySmall // Adjust the font size for the time
        )
        Text(
            text = "Time won't wait for you.",
            style = MaterialTheme.typography.labelSmall,
        )
    }
}