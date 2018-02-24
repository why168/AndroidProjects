package com.github.why168.kotlinlearn.bean
import com.google.gson.annotations.SerializedName




data class HomeBean(
		@SerializedName("issueList") val issueList: List<Issue> = listOf(),
		@SerializedName("nextPageUrl") val nextPageUrl: String = "",
		@SerializedName("nextPublishTime") val nextPublishTime: Long = 0,
		@SerializedName("newestIssueType") val newestIssueType: String = ""
)

data class Issue(
		@SerializedName("releaseTime") val releaseTime: Long = 0,
		@SerializedName("type") val type: String = "",
		@SerializedName("date") val date: Long = 0,
		@SerializedName("publishTime") val publishTime: Long = 0,
		@SerializedName("itemList") val itemList: List<Item> = listOf(),
		@SerializedName("count") val count: Int = 0
)

data class Item(
		@SerializedName("type") val type: String = "",
		@SerializedName("data") val data: Data = Data(),
		@SerializedName("id") val id: Int = 0,
		@SerializedName("adIndex") val adIndex: Int = 0
)

data class Data(
		@SerializedName("dataType") val dataType: String = "",
		@SerializedName("id") val id: Int = 0,
		@SerializedName("title") val title: String = "",
		@SerializedName("description") val description: String = "",
		@SerializedName("image") val image: String = "",
		@SerializedName("actionUrl") val actionUrl: String = "",
		@SerializedName("shade") val shade: Boolean = false
)