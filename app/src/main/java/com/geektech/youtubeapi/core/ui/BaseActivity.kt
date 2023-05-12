package com.geektech.youtubeapi.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(): VB
    protected abstract val viewModel: VM
    abstract fun isInternet(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        isConnection()
        initViews()
        initViewModel()
        initListener()
    }

    open fun initViews() {} // Инициализации вью
    open fun initListener() {} // Все наши клики
    open fun initViewModel() {} // Все обзерверы нащего viewModel\'a
    open fun isConnection() {} // Проверка на подключение к интернету

}