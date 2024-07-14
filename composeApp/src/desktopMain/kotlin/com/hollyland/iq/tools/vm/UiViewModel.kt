package com.hollyland.iq.tools.vm

import com.hollyland.iq.tools.ui.ITab
import com.hollyland.iq.tools.ui.VideoPreviewTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UiViewModel : BaseViewModel<UiIntent>() {
    private val _selectedTab = MutableStateFlow<ITab>(VideoPreviewTab)
    val selectedTab = _selectedTab.asStateFlow()

    override fun handleIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.ChangeTab -> _selectedTab.value = intent.tab
        }
    }
}

sealed interface UiIntent : Intent {
    data class ChangeTab(val tab: ITab) : UiIntent
}
