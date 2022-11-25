package com.cardsapp.domain


sealed interface Failure {
    object NetworkConnection : Failure
    interface FeatureFailure : Failure
}