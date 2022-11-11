package com.example.movieapp.adapter

import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.api.MovieApiInstance
import com.example.movieapp.domin.MovieDomine

//class PageSources(private val  movieService:MovieApiInstance):PagingSource<Int,MovieDomine>(){
//    override fun getRefreshKey(state: PagingState<Int, MovieDomine>): Int? {
//        return  state.anchorPosition?.let {
//            val anchor = state.closestPageToPosition(it)
//            anchor?.prevKey?.plus(1)?:anchor?.nextKey?.minus(1)
//
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomine> {
//        val page = params.key?:1
//        return  try{
//            val response = movieService.retrofitService.getMovie()
//            return  if(response.isSuccessful){
//                val result = response.body()?.results
//                return  if(result != null){
//                    LoadResult.Page(
//                        data =  result.asDomineModel(),
//                        prevKey =  if (page == 1) null else page - 1,
//                        nextKey = if (result.isEmpty()) null else page+1
//                    )
//                }else{
//
//                    LoadResult.Page(
//                data = listOf<MovieDomine>(),
//                prevKey = page - 1,
//                nextKey = null
//            )
//                }
//            }else{
//                LoadResult.Error(Exception("Error in response"))
//            }
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//
//    }
//
//}

