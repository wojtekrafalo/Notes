package com.example.notes

class SubnoteImage(override val id: Int, var img: String) : Subnote {
    override val type = 1
}