package com.github.why168.java

/**
 *
 * @author Edwin.Wu
 * @version 2017/8/1 11:18
 * @since  JDK1.8
 */
fun main(args: Array<String>) {


}

class Address {
    var name: String = "..."
    var street: String = "..."
    var city: String = "..."
    var state: String? = "..."
    var zip: String = "..."
}

fun copyAddress(address: Address): Address {
    val result = Address() // there's no 'new' keyword in Kotlin
    result.name = address.name // accessors are called
    result.street = address.street
    // ...
    return result
}

const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

@Deprecated(SUBSYSTEM_DEPRECATED) fun foo() {

}