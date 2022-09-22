package com.project.mjsmanagementapp.data

import android.content.Context


object UserToken {
    private const val SESSION_USER = "session"
    private const val ADMIN_NAME = "adminName"
    private const val ADMIN_PHONE = "adminPhone"
    private const val ADMIN_ID = "adminID"
    private const val ADMIN_PHOTO = "adminPhoto"
    private const val ADMIN_EMAIL = "adminEmail"
    private const val ADMIN_ROLES = "adminRoles"

    var adminName: String?
        get() {
            return getValue(ADMIN_NAME)
        }
        set(value) {
            commitValue(ADMIN_NAME, value)
        }
    var adminPhone: String?
        get() {
            return getValue(ADMIN_PHONE)
        }
        set(value) {
            commitValue(ADMIN_PHONE, value)
        }
    var adminID: String?
        get() {
            return getValue(ADMIN_ID)
        }
        set(value) {
            commitValue(ADMIN_ID, value)
        }
    var adminPhoto: String?
        get() {
            return getValue(ADMIN_PHOTO)
        }
        set(value) {
            commitValue(ADMIN_PHOTO, value)
        }
    var adminEmail: String?
        get() {
            return getValue(ADMIN_EMAIL)
        }
        set(value) {
            commitValue(ADMIN_EMAIL, value)
        }
    var adminRoles: String?
        get() {
            return getValue(ADMIN_ROLES)
        }
        set(value) {
            commitValue(ADMIN_ROLES, value)
        }


    fun clearToken() {
        UserApp.run {
            getContext()?.getSharedPreferences(
                SESSION_USER,
                Context.MODE_PRIVATE
            )?.edit()?.clear()?.commit()
        }
    }

    private fun commitValue(tag: String, value: String?) {
        val memory = UserApp.getContext()
            ?.getSharedPreferences(SESSION_USER, Context.MODE_PRIVATE)

        memory
            ?.edit()
            ?.putString(tag, value)
            ?.apply()
    }

    private fun getValue(tag: String): String? {
        val memory = UserApp.getContext()
            ?.getSharedPreferences(SESSION_USER, Context.MODE_PRIVATE)

        return memory?.getString(tag, "")
    }
}