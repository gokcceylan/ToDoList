package com.example.quiz2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_gorev_detay.*

class GorevDetayActivity : AppCompatActivity() {
    private lateinit var gorev: Gorevler
    private lateinit var vt: VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gorev_detay)

        toolbarGorevDetayActivity.title = "Details of the Task"
        //toolbarMainActivity.subtitle = "Alt Başlık"
        setSupportActionBar(toolbarGorevDetayActivity) //arayuzde degisikligi gormek icin

        vt = VeritabaniYardimcisi(this@GorevDetayActivity)

        gorev = intent.getSerializableExtra("nesne") as Gorevler

        editTextGorevAd.setText(gorev.gorev_ad)

        buttonGuncelle.setOnClickListener {
            val gorev_ad = editTextGorevAd.text.toString()

            guncelle(gorev.gorev_id, gorev_ad)

        }
    }

    fun guncelle(gorev_id:Int, gorev_ad:String){
        Log.e("Görev Güncelle", "$gorev_id - $gorev_ad")

        Gorevlerdao().gorevGuncelle(vt, gorev_id, gorev_ad)

        startActivity(Intent(this@GorevDetayActivity, MainActivity::class.java))
    }

}