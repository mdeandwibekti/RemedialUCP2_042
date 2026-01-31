package com.example.remedialucp2_042.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audit_log")

data class AuditLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val action: String,
    val oldValue: String?,
    val newValue: String?,
    val timestamp: Long = System.currentTimeMillis()
)