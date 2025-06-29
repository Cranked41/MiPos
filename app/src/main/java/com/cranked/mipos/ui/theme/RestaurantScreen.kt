package com.cranked.mipos.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cranked.mipos.model.Floor
import com.cranked.mipos.model.Table
import com.cranked.mipos.viewmodel.RestaurantViewModel
import com.cranked.mipos.enums.TableStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RestaurantScreen(viewModel: RestaurantViewModel = viewModel()) {
    val floors by viewModel.floors.collectAsState()
    val selectedFloor by viewModel.selectedFloor.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        FloorTabs(
            floors = floors,
            selectedFloor = selectedFloor,
            onSelectFloor = viewModel::selectFloor
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(selectedFloor.tables) { table ->
                TableCard(table)
            }
        }
    }
}

@Composable
fun FloorTabs(
    floors: List<Floor>,
    selectedFloor: Floor,
    onSelectFloor: (Floor) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        floors.forEach { floor ->
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { onSelectFloor(floor) },
                color = if (floor == selectedFloor) MaterialTheme.colorScheme.primary else Color.LightGray
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = floor.name,
                        color = if (floor == selectedFloor) Color.White else Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun TableCard(table: Table) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val borderColor = when (table.status) {
        TableStatus.OCCUPIED -> Color.Red
        TableStatus.EMPTY -> Color.Gray
        TableStatus.RESERVED -> Color(0xFF9C27B0)
        TableStatus.OTHER -> Color.Blue
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.4f)
            .clickable {
                table.amount?.let {
                    scope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "${table.name} → %.2f ₺".format(it), Toast.LENGTH_SHORT).show()
                    }
                }
            },
        border = BorderStroke(2.dp, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = table.name, fontWeight = FontWeight.SemiBold)
            table.amount?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "%.2f ₺".format(it),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
