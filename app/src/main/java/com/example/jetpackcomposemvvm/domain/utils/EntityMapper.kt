package com.example.jetpackcomposemvvm.domain.utils

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domain: DomainModel): Entity
}