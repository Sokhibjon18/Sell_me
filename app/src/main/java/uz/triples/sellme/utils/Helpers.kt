package uz.triples.sellme.utils

import android.app.Activity
import android.widget.Toast

class Helpers {

    fun toast(activity: Activity, text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }
}