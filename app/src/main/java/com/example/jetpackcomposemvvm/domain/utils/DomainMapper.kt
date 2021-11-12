package com.example.jetpackcomposemvvm.domain.utils

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
    fun mapFromDomainModel(domain: DomainModel): T
}