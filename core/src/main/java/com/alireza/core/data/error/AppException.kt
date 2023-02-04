package com.alireza.core.data.error

class InternetConnectionException(override val message:String="No Access To Internet"):RuntimeException()
class ConnectionTimeOutException(override val message:String="Connection Time Out"):RuntimeException()
