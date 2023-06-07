package com.bb.gamingnews.ui.feed.event.Model


import com.google.gson.annotations.SerializedName

data class AllEventResponceModel(
    @SerializedName("Data")
    val `data`: List<Data>?,
    @SerializedName("Message")
    val message: String?
) {
    data class Data(
        @SerializedName("CreatedBy")
        val createdBy: String?,
        @SerializedName("CreatedDate")
        val createdDate: String?,
        @SerializedName("Description")
        val description: Any?,
        @SerializedName("EndDate")
        val endDate: String?,
        @SerializedName("Game")
        val game: String?,
        @SerializedName("GameId")
        val gameId: Int?,
        @SerializedName("Id")
        val id: Int?,
        @SerializedName("ImageUrl")
        val imageUrl: String?,
        @SerializedName("IsPostedByMe")
        val isPostedByMe: Boolean?,
        @SerializedName("lstSubEvents")
        val lstSubEvents: List<LstSubEvent>,
        @SerializedName("MetaDescription")
        val metaDescription: Any?,
        @SerializedName("MetaKeywords")
        val metaKeywords: Any?,
        @SerializedName("StartDate")
        val startDate: String?,
        @SerializedName("Title")
        val title: String?,
        @SerializedName("Venue")
        val venue: String?
    ) {
        data class LstSubEvent(
            @SerializedName("CreatedBy")
            val createdBy: String?,
            @SerializedName("CreatedDate")
            val createdDate: String?,
            @SerializedName("Description")
            val description: String?,
            @SerializedName("EndDate")
            val endDate: String?,
            @SerializedName("Id")
            val id: Int?,
            @SerializedName("ImageUrl")
            val imageUrl: String?,
            @SerializedName("lstAddchipcounts")
            val lstAddchipcounts: List<Any?>?,
            @SerializedName("lstReportings")
            val lstReportings: List<LstReporting>,
            @SerializedName("lstVideos")
            val lstVideos: List<LstVideo>?,
            @SerializedName("lstgallery")
            val lstgallery: List<Any?>?,
            @SerializedName("lstpayout")
            val lstpayout: List<Any?>?,
            @SerializedName("MainEventId")
            val mainEventId: Int?,
            @SerializedName("PriceMoney")
            val priceMoney: Int?,
            @SerializedName("StartDate")
            val startDate: String?,
            @SerializedName("Title")
            val title: String?,
            @SerializedName("Venue")
            val venue: Any?,
            @SerializedName("WinnerName")
            val winnerName: String?,
            @SerializedName("WinningHand")
            val winningHand: String?
        ) {
            data class LstReporting(
                @SerializedName("CreatedBy")
                val createdBy: String?,
                @SerializedName("CreatedDate")
                val createdDate: String?,
                @SerializedName("Description")
                val description: String?,
                @SerializedName("Details")
                val details: String?,
                @SerializedName("EventId")
                val eventId: Int?,
                @SerializedName("Id")
                val id: Int?,
                @SerializedName("ImageUrl")
                val imageUrl: String?,
                @SerializedName("Title")
                val title: String?
            )

            data class LstVideo(
                @SerializedName("CreatedBy")
                val createdBy: String?,
                @SerializedName("CreatedDate")
                val createdDate: String?,
                @SerializedName("Description")
                val description: String?,
                @SerializedName("EventId")
                val eventId: Int?,
                @SerializedName("Id")
                val id: Int?,
                @SerializedName("LikeCount")
                val likeCount: Int?,
                @SerializedName("thumbnailImageUrl")
                val thumbnailImageUrl: String?,
                @SerializedName("Title")
                val title: String?,
                @SerializedName("VideoLength")
                val videoLength: String?,
                @SerializedName("VideoUrl")
                val videoUrl: String?,
                @SerializedName("ViewCount")
                val viewCount: Int?
            )
        }
    }
}