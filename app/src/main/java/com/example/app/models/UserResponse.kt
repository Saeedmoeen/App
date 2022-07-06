package com.example.app.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("id")
    @Expose
    private var id: Int? = null,

    @SerializedName("first_name")
    @Expose
    private var firstName: String? = null,

    @SerializedName("last_name")
    @Expose
    private var lastName: String? = null,

    @SerializedName("email")
    @Expose
    private var email: String? = null,

    @SerializedName("username")
    @Expose
    private var username: String? = null,

    @SerializedName("groups")
    @Expose
    private var groups: List<Any>? = null,

    @SerializedName("permissions")
    @Expose
    private var permissions: List<Any>? = null
) {


    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String?) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String?) {
        this.lastName = lastName
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getGroups(): List<Any>? {
        return groups
    }

    fun setGroups(groups: List<Any>?) {
        this.groups = groups
    }

    fun getPermissions(): List<Any>? {
        return permissions
    }

    fun setPermissions(permissions: List<Any>?) {
        this.permissions = permissions
    }
}