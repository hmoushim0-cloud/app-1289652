package sa.hadith.reviewpro.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewPackage(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class HadithReviewRequest(
    val requestId: String,
    val items: List<ReviewPackage>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: HadithReviewRequestStatus = HadithReviewRequestStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class HadithReviewRequestStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
