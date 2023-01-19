package com.example.database.model.dtos

import android.os.Parcel
import android.os.Parcelable


data class Workshop(
    val Title: String?,
    val Image:Int,
    val Description: String?,
    val isEnrolled:Boolean
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Title)
        parcel.writeInt(Image)
        parcel.writeString(Description)
        parcel.writeByte(if (isEnrolled) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Workshop> {
        override fun createFromParcel(parcel: Parcel): Workshop {
            return Workshop(parcel)
        }

        override fun newArray(size: Int): Array<Workshop?> {
            return arrayOfNulls(size)
        }
    }
}
