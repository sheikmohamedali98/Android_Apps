package com.example.movieapppagination.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.movieapppagination.api.MovieNetWork
import java.io.IOException
import com.example.movieapppagination.network.Result
import com.example.movieapppagination.viewmodel.NETWORK_PAGE_SIZE

private const val TMB_PAGE = 1

class MoviePagingSource(val service:MovieNetWork) :
    PagingSource<Int,Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageIndex = params.key ?: TMB_PAGE
        return try {
            val response = service.getMovieFromNetWork(
                language = "en-US",
                page = pageIndex
            )
            val responseList = mutableListOf<Result>()
            val movies = response.body()?.results ?: emptyList()
            responseList.addAll(movies)
            println("\n\n\n\n${movies.toString()}\n\n\n\n")
            val nextKey =
                if (movies == null) {
                    null
                } else {
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == TMB_PAGE) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}