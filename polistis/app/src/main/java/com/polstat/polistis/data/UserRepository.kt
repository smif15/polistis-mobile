package com.polstat.polistis.data

import com.polstat.polistis.model.LoginForm
import com.polstat.polistis.model.LoginResponse
import com.polstat.polistis.model.MessageResponse
import com.polstat.polistis.model.Pasien
import com.polstat.polistis.model.PasienDto
import com.polstat.polistis.model.PengajuanDto
import com.polstat.polistis.model.RegisterForm
import com.polstat.polistis.model.User
import com.polstat.polistis.service.UserService

interface UserRepository {
    suspend fun register(user: RegisterForm): MessageResponse
    suspend fun login(form: LoginForm): LoginResponse
    suspend fun getProfil(token: String): User
    suspend fun getInfoPasien(token: String): Pasien
    suspend fun updateProfilPasien(token: String, nama: String?, alergi: String?,
                                   riwayatPenyakit: String?)
}

class UserRepositoryImpl(private val userService: UserService) : UserRepository {
    override suspend fun register(user: RegisterForm): MessageResponse = userService.register(user)
    override suspend fun login(form: LoginForm): LoginResponse = userService.login(form)
    override suspend fun getProfil(token: String): User = userService.getUser("Bearer $token")
    override suspend fun getInfoPasien(token: String): Pasien = userService.getProfilPasien("Bearer $token")
    override suspend fun updateProfilPasien(token: String, nama: String?, alergi: String?, riwayatPenyakit: String?
    ) = userService.updateProfilPasien(token = "Bearer $token", PasienDto(nama = nama, alergi = alergi, riwayatPenyakit = riwayatPenyakit))

}