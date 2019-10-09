package com.sathish.myapplication.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import com.task.nebenan.R


class TextViewFont(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    init {
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet) {
        var a: TypedArray? = context.obtainStyledAttributes(
            attrs,
            R.styleable.customfont
        )
        var fontFamily: String? = null
        //June-21-2018//Below Method commented for version upgraded into 23
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			setAllCaps(false);
		}*/
        val n = a!!.indexCount

        for (i in 0 until n) {
            val attr = a.getIndex(i)
            if (attr == R.styleable.customfont_android_fontFamily) {
                fontFamily = a.getString(attr)
            }

            // a.recycle();
        }
        if (!isInEditMode) {
            try {
                var tf: Typeface? = Typeface.createFromAsset(
                    getContext().assets, "fonts/" + fontFamily
                            + ".otf"
                )
                typeface = tf
                tf = null
            } catch (e: Exception) {
            }

        }
        a = null
    }


}