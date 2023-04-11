package com.wahidabd.shinigami.data.home

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.WebApi
import com.wahidabd.shinigami.data.home.model.KomikItem
import com.wahidabd.shinigami.utils.Constant
import com.wahidabd.shinigami.utils.Constant.attrSrc
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class HomeDataSource : HomeRepository {

    override val dbService: LocalDb? = null
    override val webService: WebApi? = null

    override fun getPopular(): Single<List<KomikItem>> {
        return Single.create { emitter ->
            val list = ArrayList<KomikItem>()
            try {
                val doc = Jsoup.connect(Constant.baseUrl).get()
                val events = doc.select(Constant.popularEvents)
                val eventSize = events.size

                for (i in 0 until eventSize) {
                    if (i <= 8){
                    val slug = events.select(Constant.homeSlug).eq(i).attr(Constant.attrHref)
                        .replace(Constant.baseUrl, "")
                    val title = events.select(Constant.homeTitle).eq(i).text()
                    val cover = events.select(Constant.homeCover).eq(i).attr(attrSrc)
                    val type = events.select(Constant.homeType).eq(i).text()

                    list.add(KomikItem(slug, title, cover, type))
                    }
                }

                emitter.onSuccess(list)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun getLatest(): Single<List<KomikItem>> {
        return Single.create { emitter ->
            val list = ArrayList<KomikItem>()
            try {
                val doc = Jsoup.connect(Constant.baseUrl).get()
                val events = doc.select(Constant.latestEvents)
                val eventSize = events.size

                for (i in 0 until eventSize) {
                    val slug = events.select(Constant.homeSlug).eq(i).attr(Constant.attrHref)
                        .replace(Constant.baseUrl, "")
                    val title = events.select(Constant.homeTitle).eq(i).text()
                    val cover = events.select(Constant.homeCover).eq(i).attr(attrSrc)
                    val type = events.select(Constant.homeType).eq(i).text()

                    list.add(KomikItem(slug, title, cover, type))
                }

                emitter.onSuccess(list)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun getTrending(): Single<List<KomikItem>> {
        return Single.create { emitter ->
            val list = ArrayList<KomikItem>()
            try {
                val doc = Jsoup.connect(Constant.baseUrl).get()
                val events = doc.select(Constant.trendingEvents)
                val eventSize = events.size

                for (i in 0 until eventSize) {
                    val slug = events.select(Constant.trendingSlug).eq(i).attr(Constant.attrHref)
                        .replace(Constant.baseUrl, "")
                    val title = events.select(Constant.trendingTitle).eq(i).text()
                    val cover = events.select(Constant.trendingCover).eq(i).attr(attrSrc)
                    val rating = events.select(Constant.trendingStar).eq(i).text()

                    list.add(KomikItem(slug, title, cover, rating = rating))
                }

                emitter.onSuccess(list)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun getMirror(): Single<List<KomikItem>> {
        return Single.create { emitter ->
            val list = ArrayList<KomikItem>()
            try {
                val doc = Jsoup.connect(Constant.baseUrl).get()
                val events = doc.select(Constant.mirrorEvents)
                val eventSize = events.size

                for (i in 0 until eventSize) {
                    if (i > 8) {
                        val slug = events.select(Constant.homeSlug).eq(i).attr(Constant.attrHref)
                            .replace(Constant.baseUrl, "")
                        val title = events.select(Constant.homeTitle).eq(i).text()
                        val cover = events.select(Constant.homeCover).eq(i).attr(attrSrc)
                        val type = events.select(Constant.homeType).eq(i).text()

                        list.add(KomikItem(slug, title, cover, type))
                    }
                }

                emitter.onSuccess(list)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}