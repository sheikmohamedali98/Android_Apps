package com.example.miniyoutube.domain


data class VideoModel(
    val id:Int,
    val name: String?,
    val tn_img: String?,
    val video_url: String?
    )

//    :Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(id)
//        parcel.writeString(name)
//        parcel.writeString(tn_img)
//        parcel.writeString(video_url)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<VideoModel> {
//        override fun createFromParcel(parcel: Parcel): VideoModel {
//            return VideoModel(parcel)
//        }
//
//        override fun newArray(size: Int): Array<VideoModel?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
