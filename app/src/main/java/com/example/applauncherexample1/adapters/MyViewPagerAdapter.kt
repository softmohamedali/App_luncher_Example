package com.example.applauncherexample1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.viewpager.widget.PagerAdapter
import com.example.applauncherexample1.R
import com.example.applauncherexample1.databinding.ItemAppBinding
import com.example.applauncherexample1.databinding.ItemPagerBinding
import com.example.applauncherexample1.domain.models.AppObject


class MyViewPagerAdapter(
    private val context: Context,
):PagerAdapter() {
    private var pagers:MutableList<MutableList<AppObject>> = mutableListOf()
    private var cellHeight:Int=0
    private var pagersList= mutableListOf<AppsItemAdapter>()
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = ItemPagerBinding.inflate(inflater,container,false)
        val gridView = layout.grid
        var appsAdapter=AppsItemAdapter()
        if (!pagersList.contains(appsAdapter)){
            pagersList.add(appsAdapter)
        }
        appsAdapter.setData(pagers[position])
        appsAdapter.setCellesHeight(cellHeight)
        gridView.apply {
            layoutManager = GridLayoutManager(context, 4)
            var appsItemAdapter = appsAdapter
            adapter = appsItemAdapter

        }
        appsAdapter.onClickItem {
            onItemClickListener?.let { it1 -> it1(it) }
        }

        appsAdapter.onLongClickItem {
            onItemLongClickListener?.let { it1 -> it1(it) }
        }
        container.addView(layout.root)
        return layout.root
    }

    fun setData(pagers:MutableList<MutableList<AppObject>>){
        this.pagers=pagers
    }

    fun setCellesHeight(h:Int){
        cellHeight=h
    }

    override fun destroyItem(container: ViewGroup, position: Int, objec: Any) {
        container.removeView(objec as View)
    }

    override fun getCount(): Int {
        return pagers.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }


    private var onItemClickListener:((AppObject)->Unit)?=null

    fun onClickItem(listener:(AppObject)->Unit){
        onItemClickListener=listener
    }

    private var onItemLongClickListener:((AppObject)->Unit)?=null

    fun onLongClickItem(listener:(AppObject)->Unit){
        onItemLongClickListener=listener
    }

    fun notifyAllAdapters(){
        pagersList.forEach {
            it.notifyDataSetChanged()
        }
    }


}