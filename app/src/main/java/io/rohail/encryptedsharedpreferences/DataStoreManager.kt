package io.rohail.encryptedsharedpreferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(
    context: Context,
    private val prefsName: String? = context.getString(R.string.storage_name)
) {

    companion object {
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
    }

    private val Context.dataStore by preferencesDataStore(
        name = "dataStore_$prefsName",
        produceMigrations = ::sharedPreferencesMigration
    )

    private val dataStore: DataStore<Preferences> = context.dataStore


    private fun sharedPreferencesMigration(context: Context) = listOf(
        SharedPreferencesMigration(context, prefsName ?: ""),
        SharedPreferencesMigration(context, "encrypted_$prefsName")
    )

    suspend fun save(key: Preferences.Key<String>, value: String) {
        dataStore.edit {
            it[key] = value
        }
    }

    suspend fun save(key: Preferences.Key<Int>, value: Int) {
        dataStore.edit {
            it[key] = value
        }
    }

    suspend fun save(key: Preferences.Key<Long>, value: Long) {
        dataStore.edit {
            it[key] = value
        }
    }

    suspend fun save(key: Preferences.Key<Float>, value: Float) {
        dataStore.edit {
            it[key] = value
        }
    }

    suspend fun save(key: Preferences.Key<Double>, value: Double) {
        dataStore.edit {
            it[key] = value
        }
    }

    suspend fun save(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit {
            it[key] = value
        }
    }

    suspend fun save(key: Preferences.Key<Set<*>>, value: Set<*>) {
        dataStore.edit {
            it[key] = value
        }
    }

    fun getValue(key: Preferences.Key<Any>): Flow<Any?> = dataStore.data.map { it[key] }

    private suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    private suspend fun remove(key: Preferences.Key<Any>) {
        dataStore.edit {
            it.remove(key)
        }
    }

}