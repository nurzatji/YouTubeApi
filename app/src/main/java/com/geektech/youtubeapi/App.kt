package com.geektech.youtubeapi

import android.app.Application
import com.geektech.youtubeapi.repository.Repository


class App:Application() {
    companion object {

        val repository: Repository by lazy {
            Repository()

        }

    }
}
