package com.open.warehouseandinventory

import android.view.View

interface NavigationService {

    fun navigateListProductsFragment(view: View)

    fun navigateEditProductFragment(view: View)
}