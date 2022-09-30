package com.example.applauncherexample1.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Point
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.applauncherexample1.R
import com.example.applauncherexample1.adapters.AppsItemAdapter
import com.example.applauncherexample1.adapters.MyViewPagerAdapter
import com.example.applauncherexample1.databinding.ActivityMainBinding
import com.example.applauncherexample1.domain.models.AppObject
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding?=null
    val binding:ActivityMainBinding get() = _binding!!

    private val mAppsAdapter by lazy { AppsItemAdapter() }
    private val mPagersAdapter by lazy { MyViewPagerAdapter(this) }

    private lateinit var bottomSheetBehavior:BottomSheetBehavior<View>
    private val CELL_NUM=4
    private var mCellHeight=0
    private var mTopBottomSheetHeight=100
    private var mAppsDrags:AppObject?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPermission()
        setupView()
    }

    private fun setupView() {
        binding.topBottomSheet.post {}
        var img=getImage()
        if (img!=null){
            binding.background.setImageURI(Uri.parse(img))
        }
        mTopBottomSheetHeight=binding.topBottomSheet.height
        mCellHeight=(getDisableScreenHeight()-300)/CELL_NUM
        mAppsAdapter.setCellesHeight(mCellHeight)
        setUpBottomSheet()
        setUpViewPager()
        setUpActions()
        getApps()
    }

    private fun setUpActions() {

        mAppsAdapter.onClickItem {
            val intent=applicationContext.packageManager.getLaunchIntentForPackage(it.packageName)
            if (intent!=null){
                startActivity(intent)
            }
        }

        mAppsAdapter.onLongClickItem {
            collapseBottomSheet()
            if (!it.isInPager){
                it.isInPager=true
                mAppsDrags=it
            }
        }

        mPagersAdapter.onClickItem {
            Log.d("moali","on click ${it} mappPager${mAppsDrags}")
            if (mAppsDrags!=null&&!it.isInPager){
                it.image= mAppsDrags!!.image
                it.name= mAppsDrags!!.name
                it.packageName= mAppsDrags!!.packageName
                mAppsDrags=null
                mPagersAdapter.notifyAllAdapters()
            }else{
                val intent=applicationContext.packageManager.getLaunchIntentForPackage(it.packageName)
                if (intent!=null){
                    startActivity(intent)
                }
            }
        }

        mPagersAdapter.onLongClickItem {

            it.isInPager=false
            mAppsDrags= AppObject(
                name = it.name,
                packageName = it.packageName,
                isInPager = it.isInPager,
                image = it.image
            )
            it.image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!
            it.isInPager=false
            it.name=""
            it.packageName=""
        }

        binding.setting.setOnClickListener {
            startActivity(Intent(this,SettingActivity::class.java))
        }

    }

    private fun collapseBottomSheet() {
        binding.viewPager.y= 0f
        bottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun setUpViewPager() {

        val m= mutableListOf<MutableList<AppObject>>()
        m.add(mutableListOf(
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image = applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
        ))
        m.add(mutableListOf(
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
        ))
        m.add(mutableListOf(
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
            AppObject(image=applicationContext.getDrawable(R.drawable.ic_launcher_foreground)!!),
        ))

        mPagersAdapter.setData(m)
        mPagersAdapter.setCellesHeight(mCellHeight)
        binding.viewPager.adapter=mPagersAdapter


    }

    private fun setUpBottomSheet(){
        bottomSheetBehavior=BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.isHideable=false
        bottomSheetBehavior.peekHeight=300

    }


    @SuppressLint("QueryPermissionsNeeded")
    private fun getApps(){
        var apps= mutableListOf<AppObject>()
        var intent=Intent(Intent.ACTION_MAIN,null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val untreatedApps=applicationContext.packageManager.queryIntentActivities(intent,0)
        Toast.makeText(this, "num=${untreatedApps.size}", Toast.LENGTH_SHORT).show()
        untreatedApps.forEach {
            val app=AppObject(
                name = it.activityInfo.loadLabel(packageManager).toString(),
                packageName = it.activityInfo.packageName,
                image =it.activityInfo.loadIcon(packageManager)
            )
            if (!apps.contains(app)){
                apps.add(app)
            }


        }
        mAppsAdapter.setData(apps)
        binding.gvItemApps.apply {
            layoutManager=GridLayoutManager(this@MainActivity,4)
            adapter=mAppsAdapter
        }
    }



    fun getDisableScreenHeight():Int{
        //this will calculate how many pixel in screen
        val point=Point()
        val rectangle = Rect()
        var actionbarHeight=0
        var displayScreenHeight=0
        if (actionBar!=null){
            actionbarHeight= actionBar!!.height
        }
        window.decorView.getWindowVisibleDisplayFrame(rectangle)
        val statusBarHeight= rectangle.top
        val contentTop=(findViewById<View>(android.R.id.content)).top
        windowManager.defaultDisplay.getSize(point)
        displayScreenHeight=point.y
        return displayScreenHeight-contentTop-statusBarHeight-actionbarHeight
    }

    private fun getPermission() {
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            requestPermissions(
                listOf(android.Manifest.permission.READ_EXTERNAL_STORAGE).toTypedArray()
                ,1
            )
            requestPermissions(
                listOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray()
                ,1
            )
        }
    }

    override fun onResume() {
        super.onResume()
        binding.topBottomSheet.post {}
        var img=getImage()
        if (img!=null){
            binding.background.setImageURI(Uri.parse(img))
        }
    }

    fun getImage():String?{
        return getSharedPreferences("sh", MODE_PRIVATE).getString("img","")
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}