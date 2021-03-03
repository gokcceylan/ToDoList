package com.example.quiz2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class GorevlerAdapter(var mContext: Context, var gorevlerListe:ArrayList<Gorevler>, var vt:VeritabaniYardimcisi)
    : RecyclerView.Adapter<GorevlerAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(tasarim: View): RecyclerView.ViewHolder(tasarim){
        var satir_card: CardView //görünürlüğü init satırı sağladı ve hata ortadan kalktı
        var satir_yazi: TextView
        var satir_resim: ImageView

        init{
            satir_card = tasarim.findViewById(R.id.satir_card)
            satir_yazi = tasarim.findViewById(R.id.satir_yazi)
            satir_resim = tasarim.findViewById(R.id.satir_resim)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.satir_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return gorevlerListe.size
    }
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        //tike basılınca mesaj çıkmasını sağlayan kısım burası
        var gorev = gorevlerListe.get(position)

        //holder ile görsel nesneye erişmemiz gerek
        holder.satir_yazi.text = "${gorev.gorev_ad}"
        holder.satir_resim.setOnClickListener{
                        Toast.makeText(mContext, "${gorev.gorev_ad} has been checked off. Good job!", Toast.LENGTH_SHORT).show()

            Gorevlerdao().gorevSil(vt, gorev.gorev_id)

            gorevlerListe = Gorevlerdao().tumGorevler(vt)
            notifyDataSetChanged()
        }

        holder.satir_card.setOnClickListener{
            val intent = Intent(mContext, GorevDetayActivity::class.java) //mContext is used to say what page we are at
            intent.putExtra("nesne", gorev)
            mContext.startActivity(intent)
        }
    }
}