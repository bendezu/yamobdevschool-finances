package com.bendezu.yandexfinances.injection.base

interface MyComponent<in A> {
    fun inject(activityOrFragment: A)
}