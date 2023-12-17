package com.polstat.polistis.data

import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val TOKEN = stringPreferencesKey("user_token")
        val EMAIL = stringPreferencesKey("user_email")
        val IS_PASIEN = booleanPreferencesKey("user_is_pasien")
        val IS_DOKTER = booleanPreferencesKey("user_is_dokter")
        val IS_ADMIN = booleanPreferencesKey("user_is_admin")
        const val TAG = "UserPreferencesRepo"
    }

    val user: Flow<UserState> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences:", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            UserState(
                preferences[TOKEN] ?: "",
                preferences[EMAIL] ?: "",
                preferences[IS_ADMIN] ?: false,
                preferences[IS_PASIEN] ?: false,
                preferences[IS_DOKTER] ?: false
            )
        }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun saveEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    suspend fun saveIsAdmin(isAdmin: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_ADMIN] = isAdmin
        }
    }

    suspend fun saveIsPasien(isSupervisor: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_PASIEN] = isSupervisor
        }
    }

    suspend fun saveIsDokter(isEnumerator: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_DOKTER] = isEnumerator
        }
    }


}

data class UserState(
    val token: String,
    val email: String,
    val isAdmin: Boolean,
    val isPasien: Boolean,
    val isDokter: Boolean
)