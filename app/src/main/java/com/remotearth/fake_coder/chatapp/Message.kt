package com.remotearth.fake_coder.chatapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    var userId: String? = null,
    var text: String? = null,
    var timestamp: Long? = null,
    var isSeen: Boolean? = null
): Parcelable