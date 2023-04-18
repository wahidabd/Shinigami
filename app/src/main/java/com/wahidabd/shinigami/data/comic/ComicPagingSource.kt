package com.wahidabd.shinigami.data.comic

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.rx.paging.apiToLoadResult
import com.wahidabd.shinigami.data.home.model.KomikItem
import com.wahidabd.shinigami.utils.Constant
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


class ComicPagingSource(private val order: String? = null) : RxPagingSource<Int, KomikItem>() {

    override fun getRefreshKey(state: PagingState<Int, KomikItem>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, KomikItem>> {
        val position = params.key ?: 1
        val list = ArrayList<KomikItem>()

        val single = Single
            .create { emitter ->
                try {

                    val url = if (order == Constant.orderMirror) Constant.mirrorUrl + "$position"
                        else Constant.pagingUrl + "$position" + "$order"

                    val event = if (order == Constant.orderMirror) Constant.mirrorPagingEvents
                        else Constant.pagingEvents

                    val doc = Jsoup.connect(url).get()
                    val events = doc.select(event)
                    val eventsSize = events.size

                    for (i in 0 until eventsSize) {
                        val slug =
                            events.select(Constant.pagingSlugAndTitle).eq(i).attr(Constant.attrHref)
                                .replace(Constant.baseUrl, "")
                        val title = events.select(Constant.pagingSlugAndTitle).eq(i).text()
                        val cover = events.select(Constant.pagingCover).eq(i).attr("data-src")
                        val type = events.select(Constant.pagingType).eq(i).text()

                        list.add(KomikItem(slug, title, cover, type))
                    }

                    debug { "Data: $list" }
                    emitter.onSuccess(apiToLoadResult(list, position))
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }

        return single
            .subscribeOn(Schedulers.io())
            .map { it }
            .map { apiToLoadResult(list, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }
}