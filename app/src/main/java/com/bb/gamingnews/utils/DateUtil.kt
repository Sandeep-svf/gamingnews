package com.bb.gamingnews.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    //MMM dd yyyy hh:mm a
    companion object {
        private val apiDateFormat: DateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.UK)



        private val apiDateFormatHomba: DateFormat =
            SimpleDateFormat("MMM dd, yyyy", Locale.UK)

        private val apiDateFormatDateMonth: DateFormat =
            SimpleDateFormat("MMM dd", Locale.UK)

        private val timesOnly: DateFormat =
            SimpleDateFormat("HH:mm:ss", Locale.UK)

        private val apiDateFormatTime: DateFormat =
            SimpleDateFormat("HH:mm", Locale.UK)//24 hour time

        private val apiDateFormatDayMonthYear: DateFormat =
            SimpleDateFormat("EEEE, dd MMM yyyy", Locale.UK)//MOnday,19 June 2021


        private val apiDateFormatTimeAmPM: DateFormat =
            SimpleDateFormat("hh:mm", Locale.UK)//Am and Pm  time

        private val appDateFormat: DateFormat =
//            SimpleDateFormat("dd MMM,yyyy", Locale.UK)
            SimpleDateFormat("yyyy-MM-dd", Locale.UK)  //2013-11-11

        private val newapidateformate2: DateFormat =
            SimpleDateFormat("dd-MM-yyyy", Locale.UK)

        private val newapidateformatedot: DateFormat =
            SimpleDateFormat("dd.MM.yyyy", Locale.UK)

        fun getAppDateFromDate(date: Date?): String? {
            return appDateFormat.format(date!!)
        }

        fun getApiDateFromDate(date: Date): String {
            return apiDateFormat.format(date)
        }


        fun getApiDateFromCalender(apiDate: String?): String? {
            try {
                if(apiDate!=null)
                {
                    val date = apiDateFormat.parse(apiDate!!)!!
                    return apiDateFormatHomba.format(date)

                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return "N/A"
        }
   fun getApiDateMonthOnly(apiDate: String?): String? {
            try {
                if(apiDate!=null)
                {
                    val date = apiDateFormat.parse(apiDate!!)!!
                    return apiDateFormatDateMonth.format(date)

                }

            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return "N/A"
        }


//        fun getnewapiformate(apiDate: String?): String? {
//            try {
//                val date = apiDateFormat.parse(apiDate!!)!!
//                return apiDateFormatTime.format(date)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//            return "NA"
//        }
//
//        fun getnewapiformateonlyTime(apiDate: String?): String? {
//            try {
//                val date = timesOnly.parse(apiDate!!)!!
//                return apiDateFormatTime.format(date)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//            return "NA"
//        }
//
//        fun getnewapiformateinAmPm(apiDate: String?): String? {
//            try {
//                val date = apiDateFormat.parse(apiDate!!)!!
//                return apiDateFormatTimeAmPM.format(date)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//            return "NA"
//        }
//        fun getnewapiformateMonthDay(apiDate: String?): String? {
//            try {
//                val date = newapidateformatedot.parse(apiDate!!)!!
//                return apiDateFormatDayMonthYear.format(date)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//            return "NA"
//        }
//
//
//        fun getnewapiformatdaymothyear(apiDate: String?): String? {
//            try {
//                val date = apiDateFormatHomba.parse(apiDate!!)!!
//                return appDateFormat.format(date)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//            return "NA"
//        }

    }


}