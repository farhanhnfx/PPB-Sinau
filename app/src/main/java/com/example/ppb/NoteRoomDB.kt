package com.example.ppb

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDB: RoomDatabase() {
    abstract fun noteDao(): NoteDao?
    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDB? = null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): NoteRoomDB? {
            if (INSTANCE == null) {
                synchronized(NoteRoomDB::class.java) {
                    INSTANCE = databaseBuilder(context.applicationContext, NoteRoomDB::class.java, "note_database").build()
                }
            }
            return INSTANCE
        }
    }
}