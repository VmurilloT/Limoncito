package com.vmurillot.limeade.model

class UserProvider {
    companion object {

        private val Users = mutableListOf(
            UserData(
                userName = "VmurilloT",
                password = "Vmurillo.123",
                isEnable = true
            )
        )

        fun returnUser() : MutableList<UserData>{
            return Users
        }

        fun addUser(user: UserData) {
            Users.add(user)
        }

        fun SignIn(userModel: UserData?): String? {
            if (Users.firstOrNull() { userItem ->
                    userItem.userName == userModel?.userName
                            && userItem.password == userModel?.password
                } != null) {
                return null
            }

            if (Users.firstOrNull() { userItem -> userItem.userName == userModel?.userName } == null) {
                return "User doesn't exists"
            }

            if (Users.firstOrNull() { userItem ->
                    userItem.userName == userModel?.userName
                            && userItem.password != userModel?.password
                } != null) {
                return "The password is incorrect"
            }
            return null
        }
    }
}