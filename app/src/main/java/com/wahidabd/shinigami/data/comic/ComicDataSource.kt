package com.wahidabd.shinigami.data.comic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.WebApi
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.shinigami.data.comic.model.ComicDetailItem
import com.wahidabd.shinigami.data.home.model.ComicItem
import com.wahidabd.shinigami.utils.Constant
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


class ComicDataSource : ComicRepository {

    override val dbService: LocalDb? = null
    override val webService: WebApi? = null

    override fun getPaging(order: String): Observable<PagingData<ComicItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                maxSize = 100,
                enablePlaceholders = false,
            ), pagingSourceFactory = { ComicPagingSource(order) }
        ).observable
    }


    override fun getComic(slug: String): Single<ComicDetailItem> {
        return Single.create { emitter ->

            var result =  ComicDetailItem()
            var release = emptyString()
            var status = emptyString()
            var altenative = emptyString()

            try {
                val doc = Jsoup.connect(Constant.baseUrl + slug).get()
                val title = doc.select(Constant.detailTitle).text()
                val type = doc.select(Constant.detailType).text()
                val mSlug = doc.select(Constant.detailSlug).attr(Constant.attrHref).replace(Constant.baseUrl, "")
                val poster = doc.select(Constant.detailPoster).attr(Constant.attrDataSrc)
                val author = doc.select(Constant.detailAuthor).text()
                val synopsis = doc.select(Constant.detailSynopsis).text()

                doc.select(Constant.detailSummary).forEachIndexed { i, e ->
                    when(i){
                        2 -> altenative = e.text()
                        8 -> release = e.text()
                        9 -> status = e.text()
                    }
                }

                emitter.onSuccess(ComicDetailItem(
                    slug = mSlug, title = title, imagePoster = poster, imageBanner = null,
                    alternative = altenative, source = null, status = status,
                    release = release, type = type, author = author, synopsis = synopsis
                ))

            }catch (e: Exception){
                emitter.onError(e)
            }
        }
    }
}