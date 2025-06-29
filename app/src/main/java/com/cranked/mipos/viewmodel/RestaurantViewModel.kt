package com.cranked.mipos.viewmodel

import androidx.lifecycle.ViewModel
import com.cranked.mipos.enums.TableStatus
import com.cranked.mipos.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RestaurantViewModel : ViewModel() {
    private val _floors = MutableStateFlow(
        listOf(
            Floor(1, "Salon", emptyList()),
            Floor(2, "Bahçe", emptyList()),
            Floor(3, "Teras", emptyList())
        )
    )
    val floors: StateFlow<List<Floor>> = _floors

    private val _selectedFloor = MutableStateFlow(_floors.value.first())
    val selectedFloor: StateFlow<Floor> = _selectedFloor

    fun selectFloor(floor: Floor) {
        _selectedFloor.value = floor
    }
}


