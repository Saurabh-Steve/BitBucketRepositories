package com.bitbucketrepositories.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class RobotoMediumTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context) { setup() }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) { setup() }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context,
        attributeSet, defStyleAttr) { setup() }

    private fun setup() {
        typeface = Typeface.createFromAsset( context.assets, "font/roboto.medium.ttf" );
    }
}