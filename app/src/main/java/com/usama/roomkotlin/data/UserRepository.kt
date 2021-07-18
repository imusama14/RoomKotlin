package com.usama.roomkotlin.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    //A repository class abstracts access to multiple data source
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

}