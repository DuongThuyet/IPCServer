package com.example.duongnguyen.model

import android.os.Parcel
import android.os.Parcelable

class SubModel() : Parcelable {

    var subModelId = 1

    constructor(parcel: Parcel) : this() {
        readToParcel(parcel)
    }

    private fun readToParcel(dest: Parcel) {
        subModelId = dest.readInt()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(subModelId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SubModel> {
        override fun createFromParcel(parcel: Parcel): SubModel {
            return SubModel(parcel)
        }

        override fun newArray(size: Int): Array<SubModel?> {
            return Array(size) {
                SubModel()
            }
        }
    }
}