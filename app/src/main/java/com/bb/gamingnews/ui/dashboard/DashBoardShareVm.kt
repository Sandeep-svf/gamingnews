package com.bb.gamingnews.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.ui.feed.event.Model.AllEventResponceModel

class DashBoardShareVm() :ViewModel() {
    var list = MutableLiveData<List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>>()
    var listEventList = MutableLiveData<List<AllEventResponceModel.Data.LstSubEvent.LstReporting>>()
}