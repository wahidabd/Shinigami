package com.wahidabd.shinigami.utils.constant

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
    const val attrDataSrc = "data-src"

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

    const val detailTitle = "div.post-title > h1"
    const val detailType = "div.post-title > span"
    const val detailSlug = "div.summary_image > a"
    const val detailPoster = "div.summary_image > a > img"
    const val detailBanner = "div.profile-manga"
    const val detailSummary = "div.post-content_item > div.summary-content"
    const val detailGenresEvent = "div.genres-content > a"
    const val detailAuthor = "div.author-content > a"
    const val detailSynopsis1 = "div.description-summary > div.summary__content > p"
    const val detailSynopsis2 = "div.description-summary > div.summary__content > div"
    const val detailSynopsis3 = "div.description-summary > div.summary__content > blockquote > p"

    const val chapterEvents = "li.wp-manga-chapter"
    const val defaultChapterImage = "https://wuz.shinigami.id/wp-content/uploads/2022/03/20192032/THUMBNAIL.jpg"
    const val chapterPoster = "div.chapter-thumbnail > img"
    const val chapterSlug = "div.chapter-link > a"
    const val chapterTitle = "div.chapter-link > a > p"
    const val chapterTime = "div.chapter-link > a > span > i"

    const val readerPrev = "div.nav-previous > a"
    const val readerNext = "div.nav-next > a"
    const val readerTitle = "h1#chapter-heading"
    const val readerEvents = "div.page-break.no-gaps"
    const val readerData = "img"
}