package com.escodro.shared

import kotlinx.cinterop.ExperimentalForeignApi

import kotlinx.cinterop.IntVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import kotlinx.cinterop.value
import tinyexpr.*

@OptIn(ExperimentalForeignApi::class)
actual fun provideResult(): Double {
    return memScoped {
        val err = alloc<IntVar>() // For error handling
        val cExpression = "3 + 5 * 2".cstr.getPointer(this) // Convert to C string

        val expr = te_compile(cExpression.toKString(), null, 0, err.ptr) // Compile expression

        if (expr == null) {
            error("Failed to compile expression. Error at position: ${err.value}")
        }

        val result = te_eval(expr) // Evaluate the compiled expression
        te_free(expr) // Free memory after use

        result
    }
}