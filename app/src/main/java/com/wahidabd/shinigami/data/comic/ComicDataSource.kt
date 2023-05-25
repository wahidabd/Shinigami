package com.wahidabd.shinigami.data.comic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.WebApi
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.shinigami.data.comic.model.ChapterItem
import com.wahidabd.shinigami.data.comic.model.ComicDetailItem
import com.wahidabd.shinigami.data.comic.model.ContentReaderItem
import com.wahidabd.shinigami.data.comic.model.ReaderItem
import com.wahidabd.shinigami.data.home.model.ComicItem
import com.wahidabd.shinigami.domain.comic.model.ContentReader
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

            var release = emptyString()
            var status = emptyString()
            var altenative = emptyString()
            val genres = ArrayList<String>()
            val chapter = ArrayList<ChapterItem>()

            try {
                val doc = Jsoup.connect(Constant.baseUrl + slug).get()
                val title = doc.select(Constant.detailTitle).text()
                val type = doc.select(Constant.detailType).text()
                val mSlug = doc.select(Constant.detailSlug).attr(Constant.attrHref)
                    .replace(Constant.baseUrl, "")
                val poster = doc.select(Constant.detailPoster).attr(Constant.attrDataSrc)
                val author = doc.select(Constant.detailAuthor).text()
                val synopsis1 = doc.select(Constant.detailSynopsis1).text()
                val synopsis2 = doc.select(Constant.detailSynopsis2).text()
                val synopsis3 = doc.select(Constant.detailSynopsis3).text()
                val synopsis = when {
                    synopsis1.isNotEmpty() -> synopsis1
                    synopsis2.isNotEmpty() -> synopsis2
                    synopsis3.isNotEmpty() -> synopsis3
                    else -> "No Description"
                }
                val genreEvents = doc.select(Constant.detailGenresEvent)
                val genreSize = genreEvents.size
                val chapterEvents = doc.select(Constant.chapterEvents)
                val chapterSize = chapterEvents.size

                doc.select(Constant.detailSummary).forEachIndexed { i, e ->
                    when (i) {
                        2 -> altenative = e.text()
                        8 -> release = e.text()
                        9 -> status = e.text()
                    }
                }

                for (i in 0 until genreSize) genres.add(genreEvents.eq(i).text())

                for (i in 0 until chapterSize) {
                    val chTitle = chapterEvents.select(Constant.chapterTitle).eq(i).text()
                    val chSlug =
                        chapterEvents.select(Constant.chapterSlug).eq(i).attr(Constant.attrHref)
                            .replace(Constant.baseUrl, "")
                    val chImage =
                        chapterEvents.select(Constant.chapterPoster).eq(i).attr(Constant.attrSrc)
                    val chTime = chapterEvents.select(Constant.chapterTime).eq(i).text()

                    chapter.add(
                        ChapterItem(
                            slug = chSlug,
                            title = chTitle,
                            imageCover = chImage,
                            time = chTime
                        )
                    )
                }

                emitter.onSuccess(
                    ComicDetailItem(
                        slug = mSlug,
                        title = title,
                        imagePoster = poster,
                        imageBanner = null,
                        alternative = altenative,
                        source = null,
                        status = status,
                        genres = genres,
                        release = release,
                        type = type,
                        author = author,
                        synopsis = synopsis,
                        chapters = chapter
                    )
                )

            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun reader(chapter: String): Single<ReaderItem> {
        return Single.create { emitter ->
            val readerItems = ArrayList<ContentReaderItem>()
            try {
                debug { "${Constant.baseUrl}$chapter" }
                val doc = Jsoup.connect("${Constant.baseUrl}$chapter").get()
                val events = doc.select(Constant.readerEvents)
                val eventsSize = events.size

                val prevEvent = doc.select(Constant.readerPrev).attr(Constant.attrHref)
                val nextEvent = doc.select(Constant.readerNext).attr(Constant.attrHref)

                val prev: String? = prevEvent ?: null
                val next: String? = nextEvent ?: null

                for (i in 0 until eventsSize) {
                    val id = events.select(Constant.readerData).eq(i).attr("id")
                    val image = events.select(Constant.readerData).eq(i).attr(Constant.attrDataSrc)
                        .replace("\n", "")
                        .replace("\t", "")
                        .replace(" ",  "")

                    readerItems.add(ContentReaderItem(id, image))
                }

                emitter.onSuccess(
                    ReaderItem(
                        prev,
                        next,
                        readerItems
                    )
                )

            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}