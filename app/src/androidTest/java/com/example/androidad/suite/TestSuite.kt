package com.example.androidad.suite

import com.example.androidad.components.CustomButtonTest
import com.example.androidad.components.CustomDatePickerTest
import com.example.androidad.components.CustomTextFieldTest
import com.example.androidad.components.CustomToolTipTest
import com.example.androidad.components.ReadOnlyTextFieldTest
import com.example.androidad.screens.AddScreenTest
import com.example.androidad.screens.EditScreenTest
import com.example.androidad.screens.HomeScreenTest
import com.example.androidad.screens.LoginScreenTest
import com.example.androidad.screens.SignUpScreenTest
import com.example.androidad.screens.ViewReportsScreenTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CustomButtonTest::class,
    CustomDatePickerTest::class,
    CustomTextFieldTest::class,
    CustomToolTipTest::class,
    ReadOnlyTextFieldTest::class,
    LoginScreenTest::class,
    SignUpScreenTest::class,
    HomeScreenTest::class,
    ViewReportsScreenTest::class,
    AddScreenTest::class,
    EditScreenTest::class,
)
class TestSuite {}