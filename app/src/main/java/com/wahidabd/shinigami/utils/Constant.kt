package com.wahidabd.shinigami.utils

import androidx.fragment.app.Fragment
import com.wahidabd.shinigami.presentation.comic.ComicFragment
import com.wahidabd.shinigami.presentation.favorite.FavoriteFragment
import com.wahidabd.shinigami.presentation.history.HistoryFragment
import com.wahidabd.shinigami.presentation.home.HomeFragment
import com.wahidabd.shinigami.presentation.library.LibraryFragment
import com.wahidabd.shinigami.presentation.more.MoreFragment


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


object Constant {

    const val LATEST_COMIC = "Latest Comic"
    const val TRENDING_COMIC = "Trending Comic"
    const val MIRROR_COMIC = "Mirror Comic"

    const val baseUrl = "https://shinigami.id/"
    const val pagingUrl = "https://shinigami.id/semua-series/page/"
    const val mirrorUrl = "https://shinigami.id/mirror/page/"
    const val attrHref = "href"
    const val attrSrc = "src"

    const val orderLatest = "/?m_orderby=latest"
    const val orderAlphabet = "/?m_orderby=alphabet"
    const val orderRating = "/?m_orderby=rating"
    const val orderTrending = "/?m_orderby=trending"
    const val orderMirror = "mirror"

    const val popularEvents = "section.recommendations > div.video-bg > div.container > div.row > div.d-flex > div.col-6.col-sm-6.col-md-4.col-xl-2"
    const val latestEvents = "div.col-6.col-sm-6.col-md-6.col-xl-3"
    const val trendingEvents = "div#nav-contact > ul > li.get-lost-fucker"
    const val mirrorEvents = "section.recommendations2 > div.video-bg > div.container > div.row > div.d-flex > div.col-6.col-sm-6.col-md-4.col-xl-2"
    const val pagingEvents = "div.col-12.col-md-4.badge-pos-2"
    const val mirrorPagingEvents = "div.col-12.col-md-6.badge-pos-2"

    const val homeSlug = "div.series-box > a"
    const val homeTitle = "div.series-box > a > h5"
    const val homeCover = "div.series-card > a > img"
    const val homeType = "div.series-card > a > span"

    const val trendingSlug = "div.title-and-infos > a"
    const val trendingTitle = "div.title-and-infos > a > h2"
    const val trendingCover = "div.fotinhofofa > a > img"
    const val trendingStar = "div.title-and-infos > div.rt > div.rating > div.numscore"

    const val pagingSlugAndTitle = "div.item-summary > div.post-title.font-title > h3 > a"
    const val pagingCover = "div.page-item-detail.manga > div > a > img"
    const val pagingType = "div.page-item-detail.manga > a > span.manga-title-badges"
}