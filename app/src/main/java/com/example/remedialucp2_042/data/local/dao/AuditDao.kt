package com.example.remedialucp2_042.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.remedialucp2_042.data.local.entity.AuditLog

@Dao
interface AuditDao {
    @Insert
    suspend fun insertLog(log: AuditLog)

    @Query("SELECT * FROM audit_log ORDER BY timestamp DESC")
    suspend fun getAllLogs(): List<AuditLog>


    suspend fun logActivity(aksi: String, dataLama: String?, dataBaru: String?) {
        val log = AuditLog(
            action = aksi,
            oldValue = dataLama,
            newValue = dataBaru,
            timestamp = System.currentTimeMillis()
        )
        insertLog(log)
    }
}