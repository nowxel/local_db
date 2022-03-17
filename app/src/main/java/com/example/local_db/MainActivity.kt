package com.example.local_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.local_db.database.AppDatabase
import com.example.local_db.database.ProductFromDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync{
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().build()

            db.productDao().insertAll(ProductFromDatabase(null, "Socks", 1.99))
            val products = db.productDao().getAll()
            uiThread{
                Log.d("daniel", "product size? ${products.size}")
            }
        }
    }
}