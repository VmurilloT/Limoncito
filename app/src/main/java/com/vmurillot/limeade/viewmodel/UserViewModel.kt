package com.vmurillot.limeade.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmurillot.limeade.model.UserData
import com.vmurillot.limeade.model.UserProvider

class UserViewModel : ViewModel() {
    var userModel = MutableLiveData<UserData>()
    var booleano = MutableLiveData<Boolean>(false)

    enum class Origin {
        Main, Register
    }

    //Prueba 2
    fun verifyNotNull() {
        booleano.postValue(
            (
                    !userModel.value?.firstName.isNullOrBlank() &&
                            !userModel.value?.lasttName.isNullOrBlank() &&
                            (!userModel.value?.Phone.isNullOrBlank() && userModel.value?.Phone.toString().length > 4) &&
                            !userModel.value?.userName.isNullOrEmpty() && !userModel.value?.password.isNullOrEmpty()
                    )
        )
    }

    //Prueba 1
    fun verifyNotNull(origin: Origin, model: UserData) {
        userModel.value = model

        when (origin) {
            Origin.Main -> booleano.value =
                (!userModel.value?.userName.isNullOrEmpty() && !userModel.value?.password.isNullOrEmpty())
            //Se cambio a la funcion de Prueba2... para hacer "Calis"
            Origin.Register -> booleano.value =
                (
                        !userModel.value?.firstName.isNullOrBlank() &&
                                !userModel.value?.lasttName.isNullOrBlank() &&
                                (!userModel.value?.Phone.isNullOrBlank() && userModel.value?.Phone.toString().length > 4) &&
                                !userModel.value?.userName.isNullOrEmpty() && !userModel.value?.password.isNullOrEmpty()
                        )
        }
    }

    fun AddUser(userData: UserData) {
        UserProvider.addUser(userData)
    }

    fun SignIn(): String? {
        return UserProvider.SignIn(userModel.value)
    }
}