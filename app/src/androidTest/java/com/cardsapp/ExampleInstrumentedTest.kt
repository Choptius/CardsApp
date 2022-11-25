package com.cardsapp

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cardsapp.data.AppDatabase
import com.cardsapp.data.FolderEntity
import com.cardsapp.data.ModuleEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() = runTest {
        val context = InstrumentationRegistry.getInstrumentation().context
        val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        for (i in 1..10) {
            db.cardsDao().insertFolder(FolderEntity(name = "folder $i"))
            db.cardsDao().insertModule(ModuleEntity(
                name = "module $i",
                ownerFolderName = "folder 1"
            ))
        }

    }
}