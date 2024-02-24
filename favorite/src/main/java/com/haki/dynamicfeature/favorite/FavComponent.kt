package com.haki.dynamicfeature.favorite

import android.content.Context
import com.haki.dailyastro.di.FavModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavModuleDependencies::class])
interface FavComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favModuleDependencies: FavModuleDependencies): Builder
        fun build(): FavComponent
    }

}