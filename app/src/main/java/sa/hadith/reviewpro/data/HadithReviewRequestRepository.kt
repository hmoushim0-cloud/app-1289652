package sa.hadith.reviewpro.data

import sa.hadith.reviewpro.model.HadithReviewRequest
import sa.hadith.reviewpro.model.ReviewPackage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface HadithReviewRequestRepository {
    fun getAvailableCatalog(): Flow<List<ReviewPackage>>
    fun getActiveRequest(): Flow<HadithReviewRequest?>
    suspend fun submitRequest(request: HadithReviewRequest): Result<String>
}

class InMemoryHadithReviewRequestRepository : HadithReviewRequestRepository {
    private val _catalog = MutableStateFlow<List<ReviewPackage>>(listOf(
        ReviewPackage("1", "مراجعة أساسية", "تحليل قواعد الأداء والجرح مع توثيق النتائج", 5000L, "verified"),
        ReviewPackage("2", "مراجعة متقدمة", "تحليل شامل للآثار والجرح مع توصيات تعديل", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<HadithReviewRequest?>(null)

    override fun getAvailableCatalog(): Flow<List<ReviewPackage>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<HadithReviewRequest?> = _active.asStateFlow()

    override suspend fun submitRequest(request: HadithReviewRequest): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
