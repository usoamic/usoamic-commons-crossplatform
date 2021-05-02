package io.usoamic.commons.crossplatform.usecases

import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.PreferencesRepository
import io.usoamic.commons.crossplatform.repositories.api.UserRepository
import javax.inject.Inject

@Deprecated("Replace with UserUseCases")
class StartUseCases @Inject constructor(
    override val mUserRepository: UserRepository,
    override val mDatabaseRepository: DbRepository,
    override val mPreferencesRepository: PreferencesRepository
) : UserUseCases(mUserRepository, mDatabaseRepository, mPreferencesRepository)