package com.example.inventory.ui.item

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
//import com.example.inventory.data.Attributes
//import com.example.inventory.data.defaultItemUiState
import com.example.inventory.ui.navigation.NavigationDestination

object SkillEntryDestination : NavigationDestination {
    override val route = "skill_entry"
    override val titleRes = R.string.skill_entry_title
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
) {
    val AppBarHeight = 25.dp

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ItemEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        SkillEntryBody(
            modifier = Modifier
                .padding(innerPadding),
            topBarHeight = AppBarHeight // Specify the height of the top bar here
        )
    }
}

@Composable
fun SkillEntryBody(
    modifier: Modifier = Modifier,
    topBarHeight: Dp,
) {
    Column(
        modifier = modifier
            .padding(
                start = 20.dp,
                end = dimensionResource(id = R.dimen.padding_medium),
                top = topBarHeight + dimensionResource(id = R.dimen.padding_medium), // Add top bar height
                bottom = dimensionResource(id = R.dimen.padding_medium)
            )
            .verticalScroll(rememberScrollState()), // Add vertical scroll
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        horizontalAlignment = Alignment.CenterHorizontally, // Center the content horizontally
    ) {
        Text(
            text = stringResource(R.string.dungeon_reminders),
            style = MaterialTheme.typography.displaySmall
        )
        // Strength
        SkillCard(
            skillName = stringResource(R.string.strength),
            quote = stringResource(R.string.strength_quote),
            imageUrl = R.drawable.strength, //<a href="https://www.flaticon.com/free-icons/strength" title="strength icons">Strength icons created by juicy_fish - Flaticon</a>
            modifier = Modifier.padding(top = 50.dp),
        )

        // Intelligence
        SkillCard(
            skillName = stringResource(R.string.intelligence),
            imageUrl = R.drawable.map,
            quote = stringResource(R.string.int_quote),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )

        // Wisdom
        SkillCard(
            skillName = stringResource(R.string.wisdom),
            imageUrl = R.drawable.book,
            quote = stringResource(R.string.wise_quote),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            ////https://www.flaticon.com/free-icon/book_14370958
        )

        // Dexterity
        SkillCard(
            skillName = stringResource(R.string.dexterity),
            imageUrl = R.drawable.yoga_pose,
            quote = stringResource(R.string.dex_quote),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            //https://www.flaticon.com/free-icon/yoga-pose_4646080?term=pose&page=1&position=65&origin=search&related_id=4646080
            //experiencePoints = defaultItemUiState.dexterity,
            //onIncreaseClick = { onItemValueChange(itemUiState.copy(dexterity = itemUiState.dexterity + 1)) }
        )

        // Constitution
        SkillCard(
            skillName = stringResource(R.string.constitution),
            imageUrl = R.drawable.potion,
            quote = stringResource(R.string.const_quote),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            //<a href="https://www.flaticon.com/free-icons/potion" title="potion icons">Potion icons created by Culmbio - Flaticon</a>
            //experiencePoints = defaultItemUiState.constitution,
            //onIncreaseClick = { onItemValueChange(itemUiState.copy(constitution = itemUiState.constitution + 1)) }
        )

        // Charisma
        SkillCard(
            skillName = stringResource(R.string.charisma),
            imageUrl = R.drawable.network,
            quote = stringResource(R.string.charisma_quote),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            //<a href="https://www.flaticon.com/free-icons/network" title="network icons">Network icons created by prettycons - Flaticon</a>
            //experiencePoints = defaultItemUiState.charisma,
            //onIncreaseClick = { onItemValueChange(itemUiState.copy(charisma = itemUiState.charisma + 1)) }
        )
    }
}
@Composable
fun SkillCard(
    skillName: String,
    quote: String,
    imageUrl: Int, // Resource ID of the image
    modifier: Modifier
) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = imageUrl),
                    contentDescription = skillName,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = skillName,
                        style = MaterialTheme.typography.displaySmall
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = quote,
                        style = MaterialTheme.typography.labelSmall,
                    )
                    /*Button(
                                    //onClick = onAddXP,
                                    shape = MaterialTheme.shapes.small,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = stringResource(R.string.XP))
                                }*/
                    //Text(text = "Experience Points: $experiencePoints")
                    //Button(onClick = onIncreaseClick) {
                    //Text(text = "Increase Experience")
                }
            }
        }
    }
