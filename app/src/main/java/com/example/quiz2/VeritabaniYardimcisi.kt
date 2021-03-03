package com.example.quiz2


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context:Context):
    SQLiteOpenHelper(context, "gorevleruygulamasi.sqlite", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS\"gorevler\" (\n" +
                "\t\"gorev_id\"\tINTEGER,\n" +
                "\t\"gorev_ad\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"gorev_id\" AUTOINCREMENT)\n" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS gorevler")
        //drop means delete before updating
        onCreate(db)
    }
}