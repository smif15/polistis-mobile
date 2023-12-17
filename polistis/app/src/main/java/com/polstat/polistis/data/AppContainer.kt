package com.polstat.polistis.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.polstat.polistis.service.LaporanService
import com.polstat.polistis.service.PengajuanService
import com.polstat.polistis.service.UserService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val userRepository: UserRepository
    val pengajuanRepository: PengajuanRepository
    val laporanRepository: LaporanRepository
}

class DefaultAppContainer() : AppContainer {
    private val baseUrl = "http://10.0.2.2:8081"
    private val retrofit: Retrofit = Retrofit.Builder()
        //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

    private val pengajuanService: PengajuanService by lazy {
        retrofit.create(PengajuanService::class.java)
    }

    private val laporanService: LaporanService by lazy {
        retrofit.create(LaporanService::class.java)
    }

    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userService)
    }

    override val pengajuanRepository: PengajuanRepository by lazy {
        PengajuanRepositoryImpl(pengajuanService)
    }

    override val laporanRepository: LaporanRepository by lazy {
        LaporanRepositoryImpl(laporanService)
    }

}