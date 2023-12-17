package com.polstat.polistis

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.polstat.polistis.data.AppContainer
import com.polstat.polistis.data.DefaultAppContainer
import com.polstat.polistis.data.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private const val LOGGED_IN_USER_PREFERENCE_NAME = "logged_in_user_preference"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LOGGED_IN_USER_PREFERENCE_NAME
)

class PolistisApplication : Application() {
    lateinit var container: AppContainer
    lateinit var userPreferenceRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()

        container = DefaultAppContainer()
        userPreferenceRepository = UserPreferencesRepository(dataStore)
    }
}