package com.amalip.cocktailapp.core.exception

import androidx.annotation.StringRes
import com.amalip.cocktailapp.core.exception.Failure.FeatureFailure

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
// como un enum pero con clases o objetos de diferente manera
sealed class Failure : Throwable() {
    data class ServerError(val code: Int, val serverMessage: String?, val payload: String? = null) :
        Failure()

    object NetworkConnection : Failure()
    object DatabaseError : Failure()
    object Unauthorized : Failure()

    /** * Extend this class for feature specific failures.*/
    // mensaje default manejado de manera más local
    abstract class FeatureFailure(@StringRes val defaultMessage: Int? = null) : Failure()
}