package com.wahidabd.shinigami.utils.pref

import android.content.Context
import com.wahidabd.shinigami.domain.user.User


/**
 * Created by Wahid on 6/1/2023.
 * Github github.com/wahidabd.
 */


class PreferenceManager(context: Context) {

    private val prefs = context.getSharedPreferences(ConstantPref.PREF_NAME, Context.MODE_PRIVATE)

    fun setUser(data: User) = with(data){
        val editor = prefs.edit()
        editor.apply {
            putString(ConstantPref.NAME, name)
            putString(ConstantPref.EMAIL, email)
            putString(ConstantPref.AVATAR, avatar)
        }.apply()
    }

    fun getUser(): User = with(ConstantPref){
        return User(
            name = prefs.getString(NAME, ""),
            email = prefs.getString(EMAIL, ""),
            avatar = prefs.getString(AVATAR, "")
        )
    }

    fun login(state: Boolean) = with(ConstantPref){
        val editor = prefs.edit()
        editor.apply {
            putBoolean(LOGIN, state)
        }.apply()
    }

    fun getLogin(): Boolean = with(ConstantPref) {
        return prefs.getBoolean(LOGIN, false)
    }

    fun logout(){
        prefs.edit().apply {
            clear()
        }.apply()
    }

}