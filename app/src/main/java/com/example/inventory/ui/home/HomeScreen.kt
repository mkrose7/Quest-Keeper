/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.DateTimeDisplay
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.item.ItemDetailsViewModel
import com.example.inventory.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

/**
 * Entry route for Home screen
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    //viewModel2: ItemDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToSkillEntry: () -> Unit
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    //val itemUiState by viewModel2.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    //var isDarkTheme by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InventoryTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
            floatingActionButton = {
                Row {
                    FloatingActionButton(
                        onClick = navigateToItemEntry,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            // Icon
                            Box(
                                modifier = Modifier.padding(top = 6.dp) // Adjust the padding as needed
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.shield), //credit https://www.flaticon.com/free-icon/shield_602099
                                    contentDescription = stringResource(R.string.item_entry_title),
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            // Text
                            Text(
                                text = "Side Quest",
                                style = MaterialTheme.typography.bodySmall,
                                //style = MaterialTheme.typography.button,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                    top = 3.dp,
                                    bottom = 6.dp
                                ) // Add padding between icon and text
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = navigateToSkillEntry,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            // Icon
                            Box(
                                modifier = Modifier.padding(top = 6.dp) // Adjust the padding as needed
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.bow_and_arrow), //credit https://www.flaticon.com/free-icon/bow-and-arrow_4439371
                                    contentDescription = stringResource(R.string.item_entry_title),
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            // Text
                            Text(
                                text = "Skill Tree",
                                style = MaterialTheme.typography.bodySmall,
                                //style = MaterialTheme.typography.button,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                    top = 3.dp,
                                    bottom = 6.dp
                                ) // Add padding between icon and text
                            )
                        }
                    }
                }
            },
    ) { innerPadding ->
        HomeBody(
            itemList = homeUiState.itemList,
            onItemClick = { item ->
                navigateToItemUpdate(item) // Pass the selected item to the ItemDetailsScreen
            },
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            //countDao = viewModel.countDao,
            //viewModel = viewModel2
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomeBody(
    itemList: List<Item>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    //countDao: Any,
    //viewModel: ItemDetailsViewModel
)  {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        DateTimeDisplay()
        //ExperiencePoints(countDao = countDao, viewModel = viewModel)
        if (itemList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 30.dp)
            )
        } else {
            InventoryList(
                itemList = itemList,
                onItemClick = { onItemClick(it.id) },
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

/*@Composable
private fun ExperiencePoints(countDao: Any, viewModel: ItemDetailsViewModel) {
    // Retrieve the count data
    val count by viewModel.count().collectAsState()

    // Display the count points in Text
    Text(
        text = stringResource(R.string.experiencePoints) + count?.points.toString(),
        modifier = Modifier.padding(8.dp)
    )
}*/


@Composable
private fun InventoryList(
    itemList: List<Item>, onItemClick: (Item) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = itemList, key = { it.id }) { item ->
            InventoryItem(item = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) })
        }
    }
}

@Composable
private fun InventoryItem(
    item: Item, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.sideQuestName,
                    style = MaterialTheme.typography.displayMedium,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = item.category,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.toBeDone,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = item.difficulty,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

/*@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    InventoryTheme {
        HomeBody(listOf(
            Item(1, "Game", 100.0, 20, "Tech", "N/A"), Item(2, "Pen", 200.0, 30, "Office", "N/A"), Item(3, "TV", 300.0, 50, "Tech","N/A")
        ), onItemClick = {})
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    InventoryTheme {
        HomeBody(listOf(), onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryItemPreview() {
    InventoryTheme {
        InventoryItem(
            Item(1, "Game", 100.0, 20, "Tech", "N/A"),
        )
    }
}
*/