package com.example.duongnguyen.model

import android.os.Parcel
import android.os.Parcelable

class ModelDem() : Parcelable {

    var age: Int = 88
    var name: String = "Lấy đc dòi"
    var subModel = SubModel()


    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(age)
        dest?.writeString(name)
        dest?.writeParcelable(subModel, flags)
    }

    private fun readFromParcel(inParcel: Parcel) {
        age = inParcel.readInt()
        name = inParcel.readString()
        subModel = inParcel.readParcelable(ClassLoader.getSystemClassLoader())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelDem> {
        override fun createFromParcel(parcel: Parcel): ModelDem {
            return ModelDem(parcel)
        }

        override fun newArray(size: Int): Array<ModelDem?> {
            return Array(size) { ModelDem() }
        }
    }
}