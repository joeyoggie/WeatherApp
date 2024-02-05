package com.musalasoft.core.utilities

import java.text.DecimalFormat

class DecimalFormat {
    fun format(value: Double, digits: Int): String {
        val decimalFormat = DecimalFormat()
        decimalFormat.isGroupingUsed = false
        decimalFormat.maximumFractionDigits = digits
        decimalFormat.isDecimalSeparatorAlwaysShown = false
        return decimalFormat.format(value)
    }
}
