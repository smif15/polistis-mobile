package com.polstat.polistis.service

import com.polstat.polistis.model.LoginForm
import com.polstat.polistis.model.LoginResponse
import com.polstat.polistis.model.MessageResponse
import com.polstat.polistis.model.Pasien
import com.polstat.polistis.model.PasienDto
import com.polstat.polistis.model.PengajuanDto
import com.polstat.polistis.model.RegisterForm
import com.polstat.polistis.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {

    @POST("/register")
    suspend fun register(@Body user: RegisterForm): MessageResponse

    @POST("/login")
    suspend fun login(@Body form: LoginForm): LoginResponse

    @GET("/profil")
    suspend fun getUser(@Header("Authorization") token: String): User

    @GET("/pasien")
    suspend fun getProfilPasien(@Header("Authorization") token: String) : Pasien

    @PATCH("/pasien")
    suspend fun updateProfilPasien(@Header("Authorization") token: String,
                                   @Body form: PasienDto)

}