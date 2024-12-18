package com.example.ucp2.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface MataKuliahDao {
    @Insert
    suspend fun insertMataKuliah(mataKuliah: MataKuliah)
    @Update
    suspend fun updateMataKuliah(mataKuliah: MataKuliah)
    @Delete
    suspend fun deleteMataKuliah(mataKuliah: MataKuliah)
    @Query("SELECT * FROM matakuliah ORDER BY nama ASC")
    fun getAllMataKuliah(): Flow<List<MataKuliah>>
    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMataKuliah(kode: String): Flow<MataKuliah>
}