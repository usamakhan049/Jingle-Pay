package com.example.jinglepay.utils

import java.io.IOException

class ApiException(message: String) : IOException(message)

class NoInternetConnectionException(message: String) : IOException(message)