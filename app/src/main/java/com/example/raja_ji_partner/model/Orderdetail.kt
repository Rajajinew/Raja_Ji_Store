package com.example.raja_ji_partner.model

import android.os.Parcel
import android.os.Parcelable

class Orderdetail ():Parcelable{
    var userUid:String ?=null
    var username:String ?=null
    var useradrees:String ?=null
    var useremail:String ?=null
    var companyname:String ?=null
    var companyaddress:String ?=null
    var companyprice:String ?=null
    var companyimage:MutableList<String>?=null
    var usercontactno:String ?=null
    var selectdate:String ?=null
    var itemPushKey: String? = null
    var orderAccepted :Boolean=false
    var paymentrecieve :Boolean=false

    constructor(parcel: Parcel) : this() {
        userUid = parcel.readString()
        username = parcel.readString()
        useradrees = parcel.readString()
        useremail = parcel.readString()
        companyname = parcel.readString()
        companyaddress = parcel.readString()
        companyprice = parcel.readString()
        usercontactno = parcel.readString()
        selectdate = parcel.readString()
        itemPushKey = parcel.readString()
        orderAccepted = parcel.readByte() != 0.toByte()
        paymentrecieve = parcel.readByte() != 0.toByte()
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Orderdetail> {
        override fun createFromParcel(parcel: Parcel): Orderdetail {
            return Orderdetail(parcel)
        }

        override fun newArray(size: Int): Array<Orderdetail?> {
            return arrayOfNulls(size)
        }
    }

}