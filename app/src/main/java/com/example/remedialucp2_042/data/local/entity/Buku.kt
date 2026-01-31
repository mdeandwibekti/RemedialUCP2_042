package com.example.remedialucp2_042.data.local.entity



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buku")

data class Buku(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val judul: String,
    val kategoriId: Long?,
    val isSoftDeleted: Boolean = false
)