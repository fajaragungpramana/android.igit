package com.github.fajaragungpramana.igit.core.domain.local

import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.ISqlRepository
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity
import com.github.fajaragungpramana.igit.core.domain.local.model.Setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalInteractor @Inject constructor(private val sqlRepository: ISqlRepository) :
    LocalUseCase {

    override suspend fun saveSetting(settingEntity: SettingEntity): Flow<Boolean> = channelFlow {
        sqlRepository.saveSetting(settingEntity).collectLatest { send(it is AppResult.Success) }
    }.flowOn(Dispatchers.IO)

    override suspend fun getListSetting(): Flow<AppResult<List<Setting>>> =
        channelFlow<AppResult<List<Setting>>> {
            sqlRepository.getListSetting().collectLatest {
                when (it) {
                    is AppResult.Success -> send(AppResult.Success(Setting.mapToList(it.data.orEmpty())))
                    is AppResult.Error -> send(AppResult.Error(it.message))
                }
            }
        }.flowOn(Dispatchers.IO)

}