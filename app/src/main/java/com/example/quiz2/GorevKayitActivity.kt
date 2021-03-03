package com.example.quiz2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_gorev_kayit.*

class GorevKayitActivity : AppCompatActivity() {
    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gorev_kayit)

    toolbarGorevKayitActivity.title = "Save your task"
    //toolbarMainActivity.subtitle = "Alt Başlık"
    setSupportActionBar(toolbarGorevKayitActivity) //arayuzde degisikligi gormek icin

    vt = VeritabaniYardimcisi(this@GorevKayitActivity)

    buttonKaydet.setOnClickListener {
        val gorev_ad = editTextGorevAd.text.toString()

        kayit(gorev_ad)

    }

}

fun kayit(gorev_ad:String){
    Log.e("Görev Kayıt", "$gorev_ad")

    Gorevlerdao().gorevEkle(vt, gorev_ad)

    startActivity(Intent(this@GorevKayitActivity, MainActivity::class.java))
}

}