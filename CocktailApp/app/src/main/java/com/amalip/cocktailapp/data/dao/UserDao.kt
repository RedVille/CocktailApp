package com.amalip.cocktailapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.amalip.cocktailapp.domain.model.User
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE name LIKE :filter")
    fun getUsersByName(filter: String): List<User>

    @Insert(onConflict = IGNORE)
    fun saveUsers(users: List<User>): List<Long>

    @Update
    fun updateUser(user: User): Int

}