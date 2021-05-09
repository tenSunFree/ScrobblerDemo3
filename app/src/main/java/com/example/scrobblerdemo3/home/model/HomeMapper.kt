package com.example.scrobblerdemo3.home.model

import com.example.scrobblerdemo3.common.extension.IndexedMapper

object HomeMapper : IndexedMapper<HomeBean, HomeBaseEntity.HomeEntity> {
    override suspend fun map(index: Int, from: HomeBean): HomeBaseEntity.HomeEntity {
        return HomeBaseEntity.HomeEntity(
            albumId = from.albumId,
            thumbnailUrl = from.thumbnailUrl,
            title = from.title,
            id = from.id.toString()
        )
    }
}