// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.observable.operation.core

import com.intellij.openapi.observable.operation.OperationExecutionId
import com.intellij.openapi.observable.operation.OperationExecutionStatus
import com.intellij.openapi.observable.operation.core.ObservableOperationStatus.*
import com.intellij.openapi.observable.properties.AbstractObservableProperty
import com.intellij.openapi.observable.properties.ObservableProperty
import com.intellij.openapi.progress.ProcessCanceledException
import org.jetbrains.annotations.ApiStatus

/**
 * Checks that operation execution is scheduled.
 */
fun ObservableOperationTrace.isOperationScheduled(): Boolean =
  status == SCHEDULED

/**
 * Checks that operation is in progress.
 */
fun ObservableOperationTrace.isOperationInProgress(): Boolean =
  status == IN_PROGRESS

/**
 * Checks that operation is completed.
 */
fun ObservableOperationTrace.isOperationCompleted(): Boolean =
  status == COMPLETED

/**
 * Returns observable property that changed before and after operation.
 * And result of [ObservableProperty.get] is equal to [ObservableOperationTrace.isOperationInProgress].
 */
fun ObservableOperationTrace.getOperationInProgressProperty(): ObservableProperty<Boolean> {
  return object : AbstractObservableProperty<Boolean>() {
    override fun get() = isOperationInProgress()

    init {
      whenOperationStarted { fireChangeEvent(false) }
      whenOperationFinished { fireChangeEvent(true) }
    }
  }
}

inline fun <R> MutableOperationTrace.traceRun(
  id: OperationExecutionId = OperationExecutionId.NONE,
  execution: () -> R
) = traceRun({ traceStart(id) }, { traceFinish(id) }, execution)

@ApiStatus.Internal
inline fun <R> traceRun(
  start: () -> Unit,
  finish: (OperationExecutionStatus) -> Unit,
  execution: () -> R
): R {
  var status: OperationExecutionStatus = OperationExecutionStatus.Success
  try {
    start()
    return execution()
  }
  catch (exception: ProcessCanceledException) {
    status = OperationExecutionStatus.Cancel
    throw exception
  }
  catch (exception: Throwable) {
    status = OperationExecutionStatus.Failure(exception)
    throw exception
  }
  finally {
    finish(status)
  }
}
