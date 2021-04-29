package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Completable
import io.reactivex.Single
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.PreferencesRepository
import io.usoamic.commons.crossplatform.repositories.api.UserRepository
import javax.inject.Inject

open class UserUseCases @Inject constructor(
    protected open val mUserRepository: UserRepository,
    protected open val mDatabaseRepository: DbRepository,
    protected open val mPreferencesRepository: PreferencesRepository
) {
    fun hasAccount(): Single<Boolean> {
        return mUserRepository.hasAccount()
    }

    fun removePreferences() {
        mPreferencesRepository.removeAll()
    }

    fun removeAccount(): Completable {
        return mUserRepository.removeAccount()
            .ignoreElement()
    }

    fun clearDb(): Completable {
        return Completable.fromCallable {
            mDatabaseRepository.removeAll()
        }
    }
}