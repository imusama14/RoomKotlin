package com.usama.roomkotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    //Contains the method used for accessing database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}