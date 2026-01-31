package com.example.remedialucp2_042.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_buku")
data class ItemBuku(
    @PrimaryKey
    val itemid: String,
    val bukuId: Long,
    val status: String
)