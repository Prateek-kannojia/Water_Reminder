package com.example.waterreminder

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val Preference_Name="my_preference"
val  Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = Preference_Name)


class UserDetails(private val context: Context) {

    companion object{
        val User_weight= intPreferencesKey("User_weight")
        val User_age= intPreferencesKey("User_age")
        var Total_water= intPreferencesKey("Total_water")
        val HAS_SEEN_INTRO = booleanPreferencesKey("HAS_SEEN_INTRO")
        val User_selected_glass= intPreferencesKey("User_selected_glass")
        val Intake= intPreferencesKey("Intake")
    }
    suspend fun storedata(weight:Int,age:Int){
        context.dataStore.edit {
            it[User_weight]=weight
            it[User_age]=age
            it[Total_water]=calculateconsumewater(weight)
        }
    }

    private fun calculateconsumewater(weight: Int): Int {
        return (weight * 100) / 3
    }

    suspend fun storeHasSeenIntro(intro: Boolean){
        context.dataStore.edit {data->
            data[HAS_SEEN_INTRO] = intro
        }
    }
    suspend fun storeglasstype(glasstype:Int){
        context.dataStore.edit {data->
            data[User_selected_glass] = glasstype
        }
    }
    suspend fun Intake(intake:Int){
        context.dataStore.edit {data->
            data[Intake] = intake
        }
    }
    val hasSeenIntro : Flow<Boolean> = context.dataStore.data.map {
        it[HAS_SEEN_INTRO] ?: false
    }
    val water : Flow<Int> = context.dataStore.data.map {
        it[Total_water] ?: -1
    }
    val glasstype:Flow<Int> = context.dataStore.data.map{
        it[User_selected_glass]?:125
    }
    val intake:Flow<Int> = context.dataStore.data.map{
        it[Intake]?:0

    }
}