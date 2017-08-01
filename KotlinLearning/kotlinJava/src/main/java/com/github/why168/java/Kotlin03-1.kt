package com.github.why168.java

/**
 *
 * @author Edwin.Wu
 * @version 2017/8/1 10:40
 * @since  JDK1.8
 */
fun main(args: Array<String>) {
    C().f()
}

open abstract class Base {
    open fun v() {}
    fun nv() {}
    open abstract fun ds()
}

class Derived() : Base() {
    override fun ds() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun v() {
        super.v()
    }
}

open class A {
    open fun f() {
        print("A")
    }

    fun a() {
        print("a")
    }
}

interface B {
    fun f() {
        print("B")
    } // interface members are 'open' by default

    fun b() {
        print("b")
    }
}

class C() : A(), B {
    // The compiler requires f() to be overridden:
    override fun f() {
        super<A>.f() // call to A.f()
        super<B>.f() // call to B.f()
    }
}

open class Base2 {
    open fun f() {}
}

abstract class Derived2 : Base2() {
    override abstract fun f()
}
