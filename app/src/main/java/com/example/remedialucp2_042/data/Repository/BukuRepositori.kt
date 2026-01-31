package com.example.remedialucp2_042.data.Repository

import com.example.remedialucp2_042.data.local.dao.AuditDao
import com.example.remedialucp2_042.data.local.dao.BukuDao
import com.example.remedialucp2_042.data.local.entity.Kategori
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BukuRepository(
    private val bukuDao: BukuDao,
    private val auditDao: AuditDao
) {
    suspend fun getBukuRecursive(katId: Long) = withContext(Dispatchers.IO) {
        bukuDao.getBukuRecursive(katId)
    }

    suspend fun deleteKategoriSecure(kategori: Kategori, hapusPermanenBuku: Boolean) {
        withContext(Dispatchers.IO) {
            try {
                auditDao.logActivity("DELETE_ATTEMPT", "Kategori: ${kategori.nama}", null)

                bukuDao.safeDeleteKategoriWorkflow(kategori, hapusPermanenBuku)

                auditDao.logActivity("DELETE_SUCCESS", null, "Kategori ${kategori.id} Terhapus")
            } catch (e: Exception) {
                auditDao.logActivity("DELETE_FAILED", kategori.nama, e.message)
                throw e
            }
        }
    }
}