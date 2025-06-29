package com.cranked.mipos.model

data class Floor(
    val id: Int,
    val name: String,
    val tables: List<Table>
)