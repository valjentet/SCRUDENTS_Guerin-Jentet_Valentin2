package com.tumme.scrudstudents.ui.strings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tumme.scrudstudents.R
import com.tumme.scrudstudents.data.local.model.Gender
import com.tumme.scrudstudents.data.local.model.UserRole

@Composable
fun UserRole.localizedLabel(): String = stringResource(
    when (this) {
        UserRole.Student -> R.string.role_student
        UserRole.Teacher -> R.string.role_teacher
    }
)

@Composable
fun Gender.localizedLabel(): String = stringResource(
    when (this) {
        Gender.Male -> R.string.gender_male
        Gender.Female -> R.string.gender_female
        Gender.NotConcerned -> R.string.gender_not_concerned
    }
)

