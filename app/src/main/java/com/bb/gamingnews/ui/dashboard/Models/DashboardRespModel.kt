package com.bb.gamingnews.ui.dashboard.Models


import com.google.gson.annotations.SerializedName

data class DashboardRespModel(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String
) {
    data class Data(
        @SerializedName("lstArticle")
        val lstArticle: List<LstArticle>,
        @SerializedName("lstEvent")
        val lstEvent: List<LstEvent>,
        @SerializedName("lstInterviews")
        val lstInterviews: List<LstInterview>,
        @SerializedName("lstMyPrefrences")
        val lstMyPrefrences: List<LstMyPrefrence>,
        @SerializedName("lstNews")
        val lstNews: List<LstNew>,
        @SerializedName("lstVideos")
        val lstVideos: List<LstVideo>
    ) {
        data class LstArticle(
            @SerializedName("ArticleContent")
            val articleContent: String,
            @SerializedName("AttachmentUrl")
            val attachmentUrl: String,
            @SerializedName("CommentCount")
            val commentCount: Int,
            @SerializedName("CreatedBy")
            val createdBy: String,
            @SerializedName("CreatedDate")
            val createdDate: String,
            @SerializedName("Description")
            val description: String,
            @SerializedName("Id")
            val id: Int,
            @SerializedName("IsApproved")
            val isApproved: Boolean,
            @SerializedName("IsLikedByMe")
            val isLikedByMe: Boolean,
            @SerializedName("IsPostedByMe")
            val isPostedByMe: Boolean,
            @SerializedName("LikeCount")
            val likeCount: Int,
            @SerializedName("Tag")
            val tag: String,
            @SerializedName("TagId")
            val tagId: Int,
            @SerializedName("Title")
            val title: String
        )

        data class LstEvent(
            @SerializedName("CreatedBy")
            val createdBy: String,
            @SerializedName("CreatedDate")
            val createdDate: String,
            @SerializedName("Description")
            val description: Any,
            @SerializedName("EndDate")
            val endDate: String,
            @SerializedName("Game")
            val game: String,
            @SerializedName("GameId")
            val gameId: Int,
            @SerializedName("Id")
            val id: Int,
            @SerializedName("ImageUrl")
            val imageUrl: String,
            @SerializedName("IsPostedByMe")
            val isPostedByMe: Boolean,
            @SerializedName("lstSubEvents")
            val lstSubEvents: List<LstSubEvent>,
            @SerializedName("StartDate")
            val startDate: String,
            @SerializedName("Title")
            val title: String,
            @SerializedName("Venue")
            val venue: String
        ) {
            data class LstSubEvent(
                @SerializedName("CreatedBy")
                val createdBy: String,
                @SerializedName("CreatedDate")
                val createdDate: String,
                @SerializedName("Description")
                val description: String,
                @SerializedName("EndDate")
                val endDate: String,
                @SerializedName("Id")
                val id: Int,
                @SerializedName("ImageUrl")
                val imageUrl: String,
                @SerializedName("lstReportings")
                val lstReportings: List<LstReporting>,
                @SerializedName("lstVideos")
                val lstVideos: List<LstVideo>,
                @SerializedName("MainEventId")
                val mainEventId: Int,
                @SerializedName("PriceMoney")
                val priceMoney: Double,
                @SerializedName("StartDate")
                val startDate: String,
                @SerializedName("Title")
                val title: String,
                @SerializedName("WinnerName")
                val winnerName: String,
                @SerializedName("WinningHand")
                val winningHand: String
            ) {
                data class LstReporting(
                    @SerializedName("CreatedBy")
                    val createdBy: String,
                    @SerializedName("CreatedDate")
                    val createdDate: String,
                    @SerializedName("Description")
                    val description: String,
                    @SerializedName("Details")
                    val details: String,
                    @SerializedName("EventId")
                    val eventId: Int,
                    @SerializedName("Id")
                    val id: Int,
                    @SerializedName("ImageUrl")
                    val imageUrl: String,
                    @SerializedName("Title")
                    val title: String
                )

                data class LstVideo(
                    @SerializedName("CreatedBy")
                    val createdBy: String,
                    @SerializedName("CreatedDate")
                    val createdDate: String,
                    @SerializedName("Description")
                    val description: String,
                    @SerializedName("EventId")
                    val eventId: Int,
                    @SerializedName("Id")
                    val id: Int,
                    @SerializedName("LikeCount")
                    val likeCount: Int,
                    @SerializedName("thumbnailImageUrl")
                    val thumbnailImageUrl: String,
                    @SerializedName("Title")
                    val title: String,
                    @SerializedName("VideoLength")
                    val videoLength: String,
                    @SerializedName("VideoUrl")
                    val videoUrl: String,
                    @SerializedName("ViewCount")
                    val viewCount: Int
                )
            }
        }

        data class LstInterview(
            @SerializedName("CreatedBy")
            val createdBy: String,
            @SerializedName("CreatedDate")
            val createdDate: String,
            @SerializedName("Description")
            val description: String,
            @SerializedName("Game")
            val game: String,
            @SerializedName("GameId")
            val gameId: Int,
            @SerializedName("Id")
            val id: Int,
            @SerializedName("ImageUrl")
            val imageUrl: String,
            @SerializedName("InterviewContent")
            val interviewContent: String,
            @SerializedName("IsPostedByMe")
            val isPostedByMe: Boolean,
            @SerializedName("Title")
            val title: String,
            @SerializedName("VideoThumbnailUrl")
            val videoThumbnailUrl: String,
            @SerializedName("VideoUrl")
            val videoUrl: String
        )

        data class LstMyPrefrence(
            @SerializedName("Desc")
            val desc: String,
            @SerializedName("Id")
            val id: Int,
            @SerializedName("ImageUrl")
            val imageUrl: String,
            @SerializedName("Prefrence")
            val prefrence: String
        )

        data class LstNew(
            @SerializedName("CreatedBy")
            val createdBy: String,
            @SerializedName("CreatedDate")
            val createdDate: String,
            @SerializedName("Game")
            val game: String,
            @SerializedName("GameId")
            val gameId: Int,
            @SerializedName("Headline")
            val headline: String,
            @SerializedName("Id")
            val id: Int,
            @SerializedName("ImageUrl")
            val imageUrl: String,
            @SerializedName("IsPostedByMe")
            val isPostedByMe: Boolean,
            @SerializedName("LoggedInUserId")
            val loggedInUserId: Int,
            @SerializedName("lstTags")
            val lstTags: Any,
            @SerializedName("NewsDescription")
            val newsDescription: String,
            @SerializedName("Tag")
            val tag: String,
            @SerializedName("TagId")
            val tagId: Int
        )

        data class LstVideo(
            @SerializedName("Category")
            val category: String,
            @SerializedName("CategoryId")
            val categoryId: Int,
            @SerializedName("CreatedBy")
            val createdBy: String,
            @SerializedName("CreatedDate")
            val createdDate: String,
            @SerializedName("Desc")
            val desc: String,
            @SerializedName("Game")
            val game: String,
            @SerializedName("GameId")
            val gameId: Int,
            @SerializedName("Id")
            val id: Int,
            @SerializedName("IsPostedByMe")
            val isPostedByMe: Boolean,
            @SerializedName("LikeCount")
            val likeCount: Int,
            @SerializedName("ThumbnailImageUrl")
            val thumbnailImageUrl: String,
            @SerializedName("Title")
            val title: String,
            @SerializedName("VideoLength")
            val videoLength: String,
            @SerializedName("VideoUrl")
            val videoUrl: String,
            @SerializedName("ViewCount")
            val viewCount: Int
        )
    }
}