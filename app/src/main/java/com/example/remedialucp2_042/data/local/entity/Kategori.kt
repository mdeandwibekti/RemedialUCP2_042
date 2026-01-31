package com.example.remedialucp2_042.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategori")

data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nama: String,
    val parentId: Long? = null
)