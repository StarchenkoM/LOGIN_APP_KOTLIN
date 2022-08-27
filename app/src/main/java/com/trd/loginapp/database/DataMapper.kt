package com.trd.loginapp.database

import com.trd.loginapp.api.LoginDataApiResponse
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun mapApiItemToDBEntity(apiItem: LoginDataApiResponse) = UserRoomEntity(
/*        phoneNumber = apiItem.phone_number,
        surname = apiItem.surname,
        name = apiItem.name,*/

        phoneNumber = apiItem.phone_code + apiItem.phone_number,
        surname = "Dou",
        name = "John",
    )

    fun mapDBItemToUserItem(dbItem: UserRoomEntity) = UserItem(
        phoneNumber = dbItem.phoneNumber,
        surname = dbItem.surname,
        name = dbItem.name,
    )

}