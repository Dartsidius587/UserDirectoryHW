package com.example.userdirecyoryhw

data class User(val name: String, val age: Int){
    override fun toString() = "Имя: $name\nВозраст: $age"
}