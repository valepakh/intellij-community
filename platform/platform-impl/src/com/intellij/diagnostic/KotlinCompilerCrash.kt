// Copyright 2000-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.diagnostic;

open class KotlinCompilerCrash(message: String, cause: Throwable? = null, val version: String = "Does not set") : Exception(message, cause, false, false)  {
}