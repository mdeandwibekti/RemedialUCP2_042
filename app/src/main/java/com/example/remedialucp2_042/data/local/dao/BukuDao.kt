package com.example.remedialucp2_042.data.local.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Dao
import com.example.remedialucp2_042.data.local.entity.Buku
import com.example.remedialucp2_042.data.local.entity.ItemBuku
import com.example.remedialucp2_042.data.local.entity.Kategori

@Dao
interface BukuDao {
    @Query("""
        WITH RECURSIVE sub_kategori AS (
            SELECT id FROM kategori WHERE id = :rootId
            UNION ALL
            SELECT k.id FROM kategori k 
            JOIN sub_kategori sk ON k.parentId = sk.id
        )
        SELECT * FROM buku 
        WHERE kategoriId IN sub_kategori AND isSoftDeleted = 0
    """)
    suspend fun getBukuRecursive(rootId: Long): List<Buku>

    @Query("SELECT * FROM item_buku WHERE bukuId IN (SELECT id FROM buku WHERE kategoriId = :katId) AND status = 'Dipinjam'")
    suspend fun checkBukuSedangDipinjam(katId: Long): List<ItemBuku>

    @Query("UPDATE buku SET isSoftDeleted = 1 WHERE kategoriId = :katId")
    suspend fun softDeleteBukuByKategori(katId: Long)

    @Query("UPDATE buku SET kategoriId = NULL WHERE kategoriId = :katId")
    suspend fun resetKategoriBuku(katId: Long)

    @Delete
    suspend fun deleteKategori(kategori: Kategori)

    @Transaction
    suspend fun safeDeleteKategoriWorkflow(kategori: Kategori, hapusPermanenBuku: Boolean) {
        val dipinjam = checkBukuSedangDipinjam(kategori.id)
        if (dipinjam.isNotEmpty()) {
            throw Exception("Gagal menghapus: Terdapat buku yang masih dipinjam.")
        }

        if (hapusPermanenBuku) {
            softDeleteBukuByKategori(kategori.id)
        } else {
            resetKategoriBuku(kategori.id)
        }
        deleteKategori(kategori)
    }
}