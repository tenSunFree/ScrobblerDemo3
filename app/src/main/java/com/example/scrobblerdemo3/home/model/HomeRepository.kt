package com.example.scrobblerdemo3.home.model

import com.dropbox.android.external.store4.Fetcher
import com.dropbox.android.external.store4.SourceOfTruth
import com.dropbox.android.external.store4.StoreBuilder
import com.example.scrobblerdemo3.common.extension.forLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeDao: HomeDao,
    private val service: HomeService
) {
    @ExperimentalCoroutinesApi
    @FlowPreview
    val dataStore = StoreBuilder.from(
        fetcher = Fetcher.of {
            HomeMapper.forLists()(service.getData())
        },
        sourceOfTruth = SourceOfTruth.of(
            reader = { homeDao.getData() },
            writer = { _: String, entries -> homeDao.insertAll(entries.map { it }) }
        )
    ).build()
}