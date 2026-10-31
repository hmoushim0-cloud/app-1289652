package sa.hadith.reviewpro.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.hadith.reviewpro.data.HadithReviewRequestRepository
import sa.hadith.reviewpro.model.HadithReviewRequest
import sa.hadith.reviewpro.model.ReviewPackage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HadithReviewRequestViewModel(
    private val repository: HadithReviewRequestRepository
) : ViewModel() {
    val catalog: StateFlow<List<ReviewPackage>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<HadithReviewRequest?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: HadithReviewRequest) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
