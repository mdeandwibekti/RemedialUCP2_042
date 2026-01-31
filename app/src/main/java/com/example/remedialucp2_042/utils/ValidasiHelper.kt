package com.example.remedialucp2_042.utils


import com.example.remedialucp2_042.data.local.entity.Kategori

object ValidationHelper {

    fun isCyclicReference(
        currentCategoryId: Long,
        targetParentId: Long?,
        allCategories: List<Kategori>
    ): Boolean {
        if (targetParentId == null) return false
        if (targetParentId == currentCategoryId) return true

        var tempParentId = targetParentId
        while (tempParentId != null) {
            val parent = allCategories.find { it.id == tempParentId }
            if (parent?.parentId == currentCategoryId) {
                return true
            }
            tempParentId = parent?.parentId
        }
        return false
    }


    fun validateInput(text: String): Boolean {
        return text.isNotBlank() && text.length >= 3
    }
}