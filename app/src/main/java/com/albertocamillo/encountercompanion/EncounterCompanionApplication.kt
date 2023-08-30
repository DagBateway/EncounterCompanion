package com.albertocamillo.encountercompanion

import android.app.Application
import com.albertocamillo.encountercompanion.data.AppContainer
import com.albertocamillo.encountercompanion.data.DefaultAppContainer

class EncounterCompanionApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}