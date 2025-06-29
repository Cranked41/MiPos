package com.cranked.mipos.model

import com.cranked.mipos.enums.TableStatus

data class Table(
    val id: Int,
    val name: String,
    val amount: Double?,
    val status: TableStatus
)