package com.example.weeklinks

import java.lang.ref.WeakReference

class WeekDi {

    private val linksList = mutableSetOf<WeakReference<*>>()

    private inline fun <reified T : Any> getInstance(): T? {
        val weakLink = linksList.firstOrNull { it.get() is T }
        val value = weakLink?.get()
        return value as? T
    }

    internal inline fun <reified T : Any> getInstanceOrCreate(clazz: Class<T>): T {
        val instance = getInstance<T>()
        if (instance != null) {
            return instance
        }
        val newInstance = createInstance(clazz)
        linksList.add(WeakReference(newInstance))

        return newInstance as T
    }

    private var value = 0

    private fun createInstance(clazz: Class<*>): Any {
        return when (clazz) {
            TestClass::class.java -> {
                value++
                TestClass(getInstanceOrCreate(TestValue::class.java))
            }

            TestValue::class.java -> {
                TestValue(value)
            }

            else -> throw IllegalArgumentException("")
        }
    }
}

data class TestClass(var value: TestValue)

data class TestValue(val value: Int)