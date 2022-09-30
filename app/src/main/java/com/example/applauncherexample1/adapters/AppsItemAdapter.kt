package com.example.applauncherexample1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.applauncherexample1.databinding.ItemAppBinding
import com.example.applauncherexample1.domain.models.AppObject


class AppsItemAdapter: RecyclerView.Adapter<AppsItemAdapter.Vh>() {

    private var cellHeight:Int=0
    private var appsObjects: MutableList<AppObject> = mutableListOf()

    class Vh(var view: ItemAppBinding): RecyclerView.ViewHolder(view.root){

        companion object{
            fun from(parent: ViewGroup): Vh
            {
                val myView= ItemAppBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false)
                return Vh(myView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh.from(parent)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val appObject=appsObjects[position]
        val viewitem=holder.view
        var linearLayoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,cellHeight)
        viewitem.layout.layoutParams=linearLayoutParams
        viewitem.tvAppName.text=appObject.name
        viewitem.imageIconApp.setImageDrawable(appObject.image)
        viewitem.root.setOnClickListener {
            onItemClickListener?.let { it1 -> it1(appObject) }
        }

        viewitem.root.setOnLongClickListener {
            onItemLongClickListener?.let { it1 -> it1(appObject) }
            true
        }
    }


    fun setData(apps: MutableList<AppObject>){
        appsObjects=apps
    }

    fun setCellesHeight(h:Int){
        cellHeight=h
    }

    override fun getItemCount(): Int {
        return appsObjects.size
    }

    private var onItemClickListener:((AppObject)->Unit)?=null

    fun onClickItem(listener:(AppObject)->Unit){
        onItemClickListener=listener
    }


    private var onItemLongClickListener:((AppObject)->Unit)?=null

    fun onLongClickItem(listener:(AppObject)->Unit){
        onItemLongClickListener=listener
    }

}
