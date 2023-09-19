package com.invisia.mocktestinvisia.model

data class Person(
    val name: String,
    val type: String,
    val guest: List<Guest>
)