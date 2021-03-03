package com.example.quiz2

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper

class Gorevlerdao {
    //5 farklı metot
    //gösterme, ekleme, silme, update etme, arama
    //sqlite'a işlem yapan sınıf bu
    fun tumGorevler(vt:VeritabaniYardimcisi) : ArrayList<Gorevler>{
        val db = vt.writableDatabase
        val gorevlerListe = ArrayList<Gorevler>()
        val c = db.rawQuery("SELECT * FROM gorevler", null)

        while(c.moveToNext()){
            val gorev = Gorevler(c.getInt(c.getColumnIndex("gorev_id")),
                c.getString(c.getColumnIndex("gorev_ad")))
                gorevlerListe.add(gorev)
        }
        return gorevlerListe
    }
    fun gorevAra(vt:VeritabaniYardimcisi, aramaKelimesi:String) : ArrayList<Gorevler>{
        val db = vt.writableDatabase
        val gorevlerListe = ArrayList<Gorevler>()
        val c = db.rawQuery("SELECT * FROM gorevler WHERE gorev_ad LIKE '%$aramaKelimesi%'", null)

        while(c.moveToNext()){
            val gorev = Gorevler(c.getInt(c.getColumnIndex("gorev_id")),
                c.getString(c.getColumnIndex("gorev_ad")))
            gorevlerListe.add(gorev)
        }
        return gorevlerListe
    }
    fun gorevSil(vt:VeritabaniYardimcisi, gorev_id:Int){
        val db = vt.writableDatabase
        db.delete("gorevler", "gorev_id=?", arrayOf(gorev_id.toString()))
        db.close()
    }
    fun gorevEkle(vt:VeritabaniYardimcisi, gorev_ad:String){
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("gorev_ad", gorev_ad)

        db.insertOrThrow("gorevler", null, values)

        db.close()
    }
    fun gorevGuncelle(vt:VeritabaniYardimcisi, gorev_id:Int, gorev_ad:String){
        val db = vt.writableDatabase

        val values = ContentValues()
        values.put("gorev_ad", gorev_ad)
        db.update("gorevler", values, "gorev_id=?", arrayOf(gorev_id.toString()))

        db.close()
    }
}